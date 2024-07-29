package kr.kshgroup.sheris.exception

import kr.kshgroup.sheris.resp.RespData

open class SherisCommandException(message: String) : SherisException(message)

class SherisUnknownCommandFormatException(data: RespData) :
    SherisCommandException("Unknown command format: $data")

class SherisUnknownCommandException(command: String) :
    SherisCommandException("Unknown command: $command")
