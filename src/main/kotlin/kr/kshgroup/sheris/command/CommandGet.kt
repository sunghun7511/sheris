package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.data.Storage
import kr.kshgroup.sheris.resp.RespDataType
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings
import kr.kshgroup.sheris.resp.data.RespNulls

object CommandGet : AbstractCommand("GET") {
    override fun validate(args: RespArrays): Boolean {
        return args.data.size == 2 && args.data[1].dataType == RespDataType.BULK_STRINGS
    }

    override fun execute(args: RespArrays): CommandResult {
        val key = (args.data[1] as RespBulkStrings).data

        val value = Storage.get(key)

        val result = if (value != null) RespBulkStrings(value) else RespNulls()
        return CommandResult(result)
    }
}