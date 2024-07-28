package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RESP_DATA_DELIMITER
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespArrays(val data: Array<RespData>) : RespData(RespDataType.ARRAYS) {
    override fun getContentString(): String {
        var content = ""
        content += "${this.data.size}${RESP_DATA_DELIMITER}"
        content += this.data.joinToString(RESP_DATA_DELIMITER, transform = { it.toString() })
        return ""
    }
}
