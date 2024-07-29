package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.data.Storage
import kr.kshgroup.sheris.resp.RespDataType
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings
import kr.kshgroup.sheris.resp.data.RespSimpleErrors
import kr.kshgroup.sheris.resp.data.RespSimpleStrings

object CommandSet : AbstractCommand("SET") {
    override fun validate(args: RespArrays): Boolean {
        if (args.data.size < 3) return false
        if (args.data[1].dataType != RespDataType.BULK_STRINGS) return false
        if (args.data[2].dataType != RespDataType.BULK_STRINGS) return false

        if (args.data.size > 3) {
            if (args.data[3] !is RespBulkStrings) return false
        }
        return true
    }

    override fun execute(args: RespArrays): CommandResult {
        val key = (args.data[1] as RespBulkStrings).getDataOrEmpty()
        val value = (args.data[2] as RespBulkStrings).getDataOrEmpty()

        if (args.data.size > 3) {
            val arg = (args.data[3] as RespBulkStrings).getDataOrEmpty()
            if (arg.lowercase() != "px") return CommandResult(RespSimpleErrors("ERR syntax error: $arg"))
            if (args.data.size < 5) return CommandResult(RespSimpleErrors("ERR syntax error: missing expire time"))

            val expire = (args.data[4] as RespBulkStrings).data!!.toIntOrNull()
                ?: return CommandResult(RespSimpleErrors("ERR invalid expire time in set"))
            Storage.set(key, value, expire)
            return CommandResult(RespSimpleStrings("OK"))
        }

        Storage.set(key, value)

        return CommandResult(RespSimpleStrings("OK"))
    }
}
