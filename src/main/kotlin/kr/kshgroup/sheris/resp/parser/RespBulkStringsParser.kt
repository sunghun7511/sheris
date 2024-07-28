package kr.kshgroup.sheris.resp.parser

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespBulkStrings

object RespBulkStringsParser {
    fun parse(stream: RespDataStream): RespData {
        val data = stream.getBySize(stream.getUntilCRLF().toInt())
        stream.getBySize(2) // Skip CRLF

        return RespBulkStrings(data)
    }
}
