package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespDoubles(private val value: Double) : RespData(RespDataType.DOUBLES) {
    override fun getContentString(): String {
        return value.toString()
    }
}
