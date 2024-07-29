package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings

object CommandPing : AbstractCommand("PING") {
    override fun validate(args: RespArrays): Boolean {
        return args.data.size == 1
    }

    override fun execute(args: RespArrays): CommandResult {
        return CommandResult(RespBulkStrings("PONG"))
    }
}