package kr.kshgroup.sheris

import kr.kshgroup.sheris.io.SherisConnection
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings
import kr.kshgroup.sheris.resp.data.RespSimpleStrings
import java.net.ServerSocket
import java.net.Socket

fun handleClient(sock: Socket) {
    val connection = SherisConnection(sock)
    while (true) {
        val line = connection.readData()

        if (line !is RespArrays) {
            println("Warning: Received non-array data. Ignoring.")
            continue
        }

        for (command in line.data) {
            if (command !is RespBulkStrings) {
                println("Warning: Received non-bulk string data. Ignoring.")
                continue
            }

            if (command.data == "PING") {
                connection.writeData(RespSimpleStrings("PONG"))
                println("PONG!")
            }
        }
    }
}

fun main(args: Array<String>) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!")

    // Uncomment this block to pass the first stage
    val serverSocket = ServerSocket(6379)

    // Since the tester restarts your program quite often, setting SO_REUSEADDR
    // ensures that we don't run into 'Address already in use' errors
    serverSocket.reuseAddress = true

    while (true) {
        val sock = serverSocket.accept() // Wait for connection from client.
        println("accepted new connection")

        // TODO: implement concurrent system with event loop
        Thread { handleClient(sock) }.start()
    }
}
