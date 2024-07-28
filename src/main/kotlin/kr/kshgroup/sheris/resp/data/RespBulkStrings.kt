package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RESP_DATA_DELIMITER
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespBulkStrings(private val data: Array<String>) : RespData(RespDataType.BULK_STRINGS) {
    override fun getContentString(): String {
        var content = ""
        content += "${this.data.size}${RESP_DATA_DELIMITER}"
        content += this.data.joinToString(RESP_DATA_DELIMITER)
        return content
    }
}
