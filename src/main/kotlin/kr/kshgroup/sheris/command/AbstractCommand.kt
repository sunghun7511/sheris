package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays

data class CommandResult(val data: RespData)

abstract class AbstractCommand(val name: String) {
    abstract fun validate(args: RespArrays): Boolean
    abstract fun execute(args: RespArrays): CommandResult
}
