package kr.kshgroup.sheris

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kr.kshgroup.sheris.io.SherisConnection
import java.net.ServerSocket
import java.net.Socket

fun handleClient(sock: Socket) {
    val connection = SherisConnection(sock)
    while (true) {
        val command = connection.readData()
        val result = CommandExecutor.execute(command)
        connection.writeData(result)
    }
}

fun main(args: Array<String>) {
    val parser = ArgParser("Sheris - Redis client by SHGroup")
    val port by parser.option(
        ArgType.Int,
        shortName = "p",
        description = "Port to listen on"
    ).default(6379)
    parser.parse(args)

    val serverSocket = ServerSocket(port)
    serverSocket.reuseAddress = true

    while (true) {
        val sock = serverSocket.accept() // Wait for connection from client.
        println("accepted new connection")

        // TODO: implement concurrent system with event loop
        Thread { handleClient(sock) }.start()
    }
}
