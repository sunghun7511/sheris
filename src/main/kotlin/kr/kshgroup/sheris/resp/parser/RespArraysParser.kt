package kr.kshgroup.sheris.resp.parser

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays

object RespArraysParser {
    fun parse(stream: RespDataStream): RespData {
        val size = stream.getUntilCRLF().toInt()
        val data = Array(size) {
            RespParser.parse(stream)
        }
        return RespArrays(data)
    }
}
