package kr.kshgroup.redis

import java.net.ServerSocket
import java.net.Socket

fun handleClient(sock: Socket) {
    val reader = sock.getInputStream().bufferedReader()
    val writer = sock.getOutputStream().bufferedWriter()

    while (true) {
        if (reader.readLine() == "PING") {
            writer.write("+PONG\r\n")
            writer.flush()
            println("PONG!")
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

        Thread { handleClient(sock) }.start()
    }
}