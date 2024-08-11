package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.data.Storage
import kr.kshgroup.sheris.resp.data.RespBulkStrings

object CommandGet : AbstractStringCommand("GET") {
    override fun validate(args: Array<String>): Boolean {
        return args.size == 2
    }

    override fun execute(args: Array<String>): CommandResult {
        val key = args[1]

        val value = Storage.get(key)
        return CommandResult(RespBulkStrings(value))
    }
}
