package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays

data class CommandResult(val data: RespData)

abstract class AbstractCommand(val name: String) {
    abstract fun validate(args: RespArrays): Boolean
    abstract fun execute(args: RespArrays): CommandResult
}

abstract class AbstractStringCommand(name: String) : AbstractCommand(name) {
    abstract fun validate(args: Array<String>): Boolean
    abstract fun execute(args: Array<String>): CommandResult

    override fun validate(args: RespArrays): Boolean {
        return validate(args.toStringArray())
    }

    override fun execute(args: RespArrays): CommandResult {
        return execute(args.toStringArray())
    }
}
