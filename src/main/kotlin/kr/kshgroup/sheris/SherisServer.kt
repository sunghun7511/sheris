package kr.kshgroup.sheris

import kotlinx.cli.*
import kr.kshgroup.sheris.command.CommandResult
import kr.kshgroup.sheris.data.Storage
import kr.kshgroup.sheris.exception.SherisConnectionClosedException
import kr.kshgroup.sheris.exception.SherisUnknownCommandException
import kr.kshgroup.sheris.io.SherisConnection
import java.net.ServerSocket
import java.net.Socket

class SherisServer(
    private val configuration: SherisConfiguration,
) {
    val executor: CommandExecutor = CommandExecutor(this)
    val replicationManager: ReplicationManager = ReplicationManager(this, configuration.replication)
    val storage: Storage = Storage()

    fun run() {
        val serverSocket = ServerSocket(this.configuration.port)
        serverSocket.reuseAddress = true

        var id = 0
        while (true) {
            val sock = serverSocket.accept()

            // TODO: implement concurrent system with event loop
            Thread { handleClient(sock, id++) }.start()
        }
    }

    private fun handleClient(sock: Socket, id: Int) {
        println("[$id] accepted new connection")

        val connection = SherisConnection(sock)
        while (true) {
            try {
                handleCommand(connection)
            } catch (e: SherisConnectionClosedException) {
                println("[$id] Connection closed: ${e.message}")
                break
            } catch (e: Exception) {
                println("[$id] Error in handling command: ${e.message}")
            }
        }
    }

    private fun handleCommand(connection: SherisConnection) {
        val command = connection.readData()

        val result: CommandResult = try {
            executor.execute(command)
        } catch (e: SherisUnknownCommandException) {
            CommandResult.error(e.message ?: "Error in command execute: $command")
        }
        connection.writeData(result.data)
    }
}

fun main(args: Array<String>) {
    val parser = ArgParser("Sheris - Redis server by SHGroup")
    val port by parser.option(
        ArgType.Int,
        shortName = "p",
        description = "Port to listen on"
    ).default(6379)
    val replicaOf by parser.option(
        ArgType.String,
        fullName = "replicaof",
        description = "Make the server a replica of another instance",
    )
    parser.parse(args)

    val server = SherisServer(SherisConfiguration(
        port = port,
        replication = ReplicationConfiguration.of(replicaOf)
    ))
    server.run()
}
