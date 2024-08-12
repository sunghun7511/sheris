package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer

class CommandSet(sherisServer: SherisServer) : AbstractStringCommand("SET", sherisServer) {
    override fun validate(args: Array<String>): Boolean {
        return args.size >= 3
    }

    override fun execute(args: Array<String>): CommandResult {
        val key = args[1]
        val value = args[2]

        if (args.size > 3) {
            val arg = args[3]
            if (arg.lowercase() != "px") return CommandResult.error("syntax error: $arg")
            if (args.size < 5) return CommandResult.error("syntax error: missing expire time")

            val expire = args[4].toIntOrNull()
                ?: return CommandResult.error("invalid expire time in set")
            this.sherisServer.storage.set(key, value, expire)
            return CommandResult.sStr("OK")
        }

        this.sherisServer.storage.set(key, value)

        return CommandResult.sStr("OK")
    }
}
