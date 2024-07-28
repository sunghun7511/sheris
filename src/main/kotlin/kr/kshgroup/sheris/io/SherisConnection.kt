package kr.kshgroup.sheris.io

import kr.kshgroup.sheris.resp.RespData
import java.net.Socket

class SherisConnection(socket: Socket) {
    private val reader = socket.getInputStream().getSherisReader()
    private val writer = socket.getOutputStream().bufferedWriter()

    fun readData(): RespData {
        return reader.readData()
    }

    fun writeData(data: RespData) {
        writer.write(data.toString())
        writer.flush()
    }
}