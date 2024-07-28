package kr.kshgroup.sheris.io

import kr.kshgroup.sheris.exception.SherisConnectionClosedException
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.parser.RespDataStream
import kr.kshgroup.sheris.resp.parser.RespParser
import java.io.InputStream

class SherisReader(private val inputStream: InputStream) {
    fun readData(): RespData {
        return RespParser.parse(SherisReaderStream(inputStream))
    }

    private class SherisReaderStream(private val inputStream: InputStream) : RespDataStream {
        companion object {
            const val BUFFER_SIZE = 4 * 1024
            const val CARRIAGE_RETURN: Byte = 0x0D
            const val LINE_FEED: Byte = 0x0A
        }

        override fun getBySize(size: Int): String {
            val data = ByteArray(size)
            val readSize = inputStream.read(data)
            if (readSize == -1) {
                throw SherisConnectionClosedException()
            }

            val result = data.toString(Charsets.UTF_8)
            println("Read (by size): $result")
            return result
        }

        override fun getUntilCRLF(include: Boolean): String {
            val data = ByteArray(BUFFER_SIZE)
            var current = 0
            while (true) {
                val read = inputStream.read()
                if (read == -1) {
                    throw SherisConnectionClosedException()
                }

                data[current++] = read.toByte()
                if (current >= 2 && data[current - 2] == CARRIAGE_RETURN && data[current - 1] == LINE_FEED) {
                    break
                }
            }

            val result = String(data, 0, current - if (include) 0 else 2, Charsets.UTF_8)
            println("Read (until CRLF): $result")
            return result
        }
    }
}

fun InputStream.getSherisReader(): SherisReader = SherisReader(this)
