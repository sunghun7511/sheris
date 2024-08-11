package kr.kshgroup.sheris.resp.data

import kr.kshgroup.sheris.exception.SherisIllegalFormatException
import kr.kshgroup.sheris.resp.RESP_DATA_DELIMITER
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.RespDataType

class RespArrays(
    val data: Array<RespData>,
    val size: Int = data.size,
    private var cursor: Int = 0
) : RespData(RespDataType.ARRAYS), Iterator<RespData> {
    override fun getContentString(): String {
        var content = ""
        content += "${this.data.size}${RESP_DATA_DELIMITER}"
        content += this.data.joinToString(RESP_DATA_DELIMITER, transform = { it.toString() })
        return ""
    }

    operator fun get(index: Int): RespData {
        return this.data[index]
    }

    operator fun set(index: Int, value: RespData) {
        this.data[index] = value
    }

    fun isEmpty(): Boolean {
        return this.data.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return this.data.isNotEmpty()
    }

    fun toStringArray(): Array<String> {
        return this.data.map {
            (it as? RespBulkStrings
                ?: throw SherisIllegalFormatException("invalid string array")).asString()
        }.toTypedArray()
    }

    override fun hasNext(): Boolean {
        return this.cursor < this.size
    }

    override fun next(): RespData {
        return this.data[this.cursor++]
    }

    override fun toString(): String {
        return this.data.toString()
    }
}
