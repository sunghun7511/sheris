package kr.kshgroup.sheris.resp.parser

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataCategory
import kr.kshgroup.sheris.resp.RespDataType

object RespParser {
    fun parse(stream: RespDataStream): RespData {
        val firstChar = stream.getBySize(1)
        val dataType = RespDataType.get(firstChar[0])

        if (dataType.category == RespDataCategory.SIMPLE) {
            return RespSimpleParser.parse(dataType, stream)
        }

        return when (dataType) {
            RespDataType.BULK_STRINGS -> RespBulkStringsParser.parse(stream)
            RespDataType.ARRAYS -> RespArraysParser.parse(stream)
            RespDataType.BULK_ERRORS -> throw NotImplementedError()
            RespDataType.VERBATIM_STRINGS -> throw NotImplementedError()
            RespDataType.MAPS -> throw NotImplementedError()
            RespDataType.SETS -> throw NotImplementedError()
            RespDataType.PUSHES -> throw NotImplementedError()
            else -> throw NotImplementedError()
        }
    }
}
