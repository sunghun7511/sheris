package kr.kshgroup.sheris.resp

const val RESP_DATA_DELIMITER = "\r\n"

abstract class RespData(val dataType: RespDataType) {
    fun toRespString(): String {
        return this.dataType.firstByte + this.getContentString() + RESP_DATA_DELIMITER
    }

    abstract override fun toString(): String

    abstract fun getContentString(): String
}
