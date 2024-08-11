package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays

data class CommandResult(val data: RespData)

abstract class AbstractCommand(protected val name: String, protected val sherisServer: SherisServer) {
    abstract fun validate(args: RespArrays): Boolean
    abstract fun execute(args: RespArrays): CommandResult

    fun getName(): String {
        return name
    }
}

abstract class AbstractStringCommand(name: String, sherisServer: SherisServer) : AbstractCommand(name, sherisServer) {
    abstract fun validate(args: Array<String>): Boolean
    abstract fun execute(args: Array<String>): CommandResult

    override fun validate(args: RespArrays): Boolean {
        return validate(args.toStringArray())
    }

    override fun execute(args: RespArrays): CommandResult {
        return execute(args.toStringArray())
    }
}
