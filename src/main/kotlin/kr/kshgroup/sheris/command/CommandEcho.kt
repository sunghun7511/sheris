package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.resp.data.RespArrays

object CommandEcho : AbstractCommand("ECHO") {
    override fun validate(args: RespArrays): Boolean {
        return args.data.size == 2
    }

    override fun execute(args: RespArrays): CommandResult {
        return CommandResult(args.data[1])
    }
}
