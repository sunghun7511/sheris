package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.data.Storage
import kr.kshgroup.sheris.resp.data.RespSimpleErrors
import kr.kshgroup.sheris.resp.data.RespSimpleStrings

object CommandSet : AbstractStringCommand("SET") {
    override fun validate(args: Array<String>): Boolean {
        return args.size >= 3
    }

    override fun execute(args: Array<String>): CommandResult {
        val key = args[1]
        val value = args[2]

        if (args.size > 3) {
            val arg = args[3]
            if (arg.lowercase() != "px") return CommandResult(RespSimpleErrors("ERR syntax error: $arg"))
            if (args.size < 5) return CommandResult(RespSimpleErrors("ERR syntax error: missing expire time"))

            val expire = args[4].toIntOrNull()
                ?: return CommandResult(RespSimpleErrors("ERR invalid expire time in set"))
            Storage.set(key, value, expire)
            return CommandResult(RespSimpleStrings("OK"))
        }

        Storage.set(key, value)

        return CommandResult(RespSimpleStrings("OK"))
    }
}
