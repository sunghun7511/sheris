package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.data.Storage
import kr.kshgroup.sheris.resp.RespDataType
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings
import kr.kshgroup.sheris.resp.data.RespSimpleStrings

object CommandSet : AbstractCommand("SET") {
    override fun validate(args: RespArrays): Boolean {
        return args.data.size == 3 && args.data[1].dataType == RespDataType.BULK_STRINGS && args.data[2].dataType == RespDataType.BULK_STRINGS
    }

    override fun execute(args: RespArrays): CommandResult {
        val key = (args.data[1] as RespBulkStrings).data
        val value = (args.data[2] as RespBulkStrings).data

        Storage.set(key, value)

        return CommandResult(RespSimpleStrings("OK"))
    }
}
