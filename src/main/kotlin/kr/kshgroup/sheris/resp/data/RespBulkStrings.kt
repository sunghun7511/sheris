package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.resp.RESP_DATA_DELIMITER
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespBulkStrings(val data: String?) : RespData(RespDataType.BULK_STRINGS) {
    override fun getContentString(): String {
        if (this.data == null) return "-1"

        var content = ""
        content += "${this.data.toByteArray().size}${RESP_DATA_DELIMITER}"
        content += this.data
        return content
    }

    fun getDataOrEmpty(): String {
        return this.data ?: ""
    }
}
