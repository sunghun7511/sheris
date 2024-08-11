package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespDoubles(private val value: Double) : RespData(RespDataType.DOUBLES) {
    override fun toString(): String {
        return value.toString()
    }

    fun asDouble(): Double {
        return value
    }

    override fun getContentString(): String {
        return value.toString()
    }
}
