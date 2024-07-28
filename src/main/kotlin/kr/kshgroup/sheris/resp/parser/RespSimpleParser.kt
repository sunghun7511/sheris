package kr.kshgroup.sheris.resp.parser

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType
import kr.kshgroup.sheris.resp.data.*

object RespSimpleParser {
    fun parse(dataType: RespDataType, stream: RespDataStream): RespData {
        val data = stream.getUntilCRLF()
        return when (dataType) {
            RespDataType.SIMPLE_STRING -> RespSimpleStrings(data)
            RespDataType.SIMPLE_ERROR -> RespSimpleErrors(data)
            RespDataType.INTEGERS -> RespIntegers(data.toInt())
            RespDataType.NULLS -> RespNulls()
            RespDataType.BOOLEANS -> RespBooleans("t".equals(data, ignoreCase = true))
            RespDataType.DOUBLES -> RespDoubles(parseDouble(data))
            RespDataType.BIG_NUMBERS -> RespBigNumbers(data.toLong())
            else -> throw IllegalArgumentException("Invalid data type: $dataType")
        }
    }

    private fun parseDouble(data: String): Double {
        return when (data.lowercase()) {
            "nan" -> Double.NaN
            "inf" -> Double.POSITIVE_INFINITY
            "-inf" -> Double.NEGATIVE_INFINITY
            else -> data.toDouble()
        }
    }
}
