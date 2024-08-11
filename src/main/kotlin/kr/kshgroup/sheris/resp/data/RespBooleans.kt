package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespBooleans(private val value: Boolean) : RespData(RespDataType.BOOLEANS) {
    override fun getContentString(): String {
        return if (value) "t" else "f"
    }

    fun asBoolean(): Boolean {
        return value
    }

    override fun toString(): String {
        return value.toString()
    }
}
