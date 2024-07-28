package kr.kshgroup.sheris.resp

const val RESP_DATA_DELIMITER = "\r\n"

abstract class RespData(private val dataType: RespDataType) {
    override fun toString(): String {
        return this.dataType.firstByte + this.getContentString() + RESP_DATA_DELIMITER
    }

    abstract fun getContentString(): String
}
