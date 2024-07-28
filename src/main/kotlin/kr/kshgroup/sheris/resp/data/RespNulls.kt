package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespNulls : RespData(RespDataType.NULLS) {
    override fun getContentString(): String {
        return ""
    }
}