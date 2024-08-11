package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer
import kr.kshgroup.sheris.resp.data.RespBulkStrings

class CommandEcho(sherisServer: SherisServer) : AbstractStringCommand("ECHO", sherisServer) {
    override fun validate(args: Array<String>): Boolean {
        return args.size == 2
    }

    override fun execute(args: Array<String>): CommandResult {
        return CommandResult(RespBulkStrings(args[1]))
    }
}
