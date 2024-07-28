package kr.kshgroup.sheris.exception

open class SherisIOException(message: String) : SherisException(message)

class SherisConnectionClosedException : SherisIOException("Connection closed by client")
