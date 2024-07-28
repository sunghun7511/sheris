package kr.kshgroup.sheris.resp.parser

interface RespDataStream {
    fun getBySize(size: Int): String
    fun getUntilCRLF(include: Boolean = false): String
}
