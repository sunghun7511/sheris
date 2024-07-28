package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespBigNumbers(private val value: Long) : RespData(RespDataType.BIG_NUMBERS) {
    override fun getContentString(): String {
        return value.toString()
    }
}