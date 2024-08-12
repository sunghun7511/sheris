package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer

class CommandPing(sherisServer: SherisServer) : AbstractStringCommand("PING", sherisServer) {
    override fun validate(args: Array<String>): Boolean {
        return args.size == 1
    }

    override fun execute(args: Array<String>): CommandResult {
        return CommandResult.bStr("PONG")
    }
}
