package kr.kshgroup.sheris

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
