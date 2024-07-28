package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespSimpleStrings(private val data: String) : RespData(RespDataType.SIMPLE_STRING) {
    override fun getContentString(): String {
        return data
    }
}
