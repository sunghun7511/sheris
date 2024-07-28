package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespSimpleErrors(private val message: String) : RespData(RespDataType.SIMPLE_ERROR) {
    override fun getContentString(): String {
        return message
    }
}
