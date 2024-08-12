package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer

class CommandGet(sherisServer: SherisServer) : AbstractStringCommand("GET", sherisServer) {
    override fun validate(args: Array<String>): Boolean {
        return args.size == 2
    }

    override fun execute(args: Array<String>): CommandResult {
        val key = args[1]

        val value = this.sherisServer.getStorage().get(key)
        return CommandResult.bStr(value)
    }
}
