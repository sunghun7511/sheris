package kr.kshgroup.sheris

import kr.kshgroup.sheris.command.*
import kr.kshgroup.sheris.exception.SherisUnknownCommandFormatException
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings

object CommandExecutor {
    // TODO: Implement command locator
    private val commands = listOf(
        CommandEcho,
        CommandGet,
        CommandPing,
        CommandSet,
    )

    fun execute(data: RespData): RespData {
        if (data !is RespArrays || data.isEmpty()) {
            throw SherisUnknownCommandFormatException(data)
        }

        val command = getCommandByName(
            (data[0] as? RespBulkStrings ?: throw SherisUnknownCommandFormatException(data)).asString()
        )
        if (command?.validate(data) != true) {
            throw SherisUnknownCommandFormatException(data)
        }

        val result = command.execute(data)
        return result.data
    }

    private fun getCommandByName(name: String): AbstractCommand? {
        return commands.find { it.name.equals(name, ignoreCase = true) }
    }
}
