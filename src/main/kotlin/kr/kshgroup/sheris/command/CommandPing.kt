package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer
import kr.kshgroup.sheris.resp.data.RespBulkStrings

class CommandPing(sherisServer: SherisServer) : AbstractStringCommand("PING", sherisServer) {
    override fun validate(args: Array<String>): Boolean {
        return args.size == 1
    }

    override fun execute(args: Array<String>): CommandResult {
        return CommandResult(RespBulkStrings("PONG"))
    }
}
