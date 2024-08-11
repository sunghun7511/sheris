package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespIntegers(private val data: Int) : RespData(RespDataType.INTEGERS) {
    override fun getContentString(): String {
        return data.toString()
    }

    fun asInt(): Int {
        return data
    }

    override fun toString(): String {
        return data.toString()
    }
}
