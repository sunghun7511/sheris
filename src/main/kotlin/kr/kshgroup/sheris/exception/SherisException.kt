package kr.kshgroup.sheris.exception

open class SherisException(message: String) : RuntimeException(message)

open class SherisIllegalFormatException(message: String) : SherisException(message)
