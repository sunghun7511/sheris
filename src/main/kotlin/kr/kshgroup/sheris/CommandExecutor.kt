package kr.kshgroup.sheris

import kr.kshgroup.sheris.command.AbstractCommand
import kr.kshgroup.sheris.command.CommandEcho
import kr.kshgroup.sheris.command.CommandPing
import kr.kshgroup.sheris.exception.SherisUnknownCommandException
import kr.kshgroup.sheris.exception.SherisUnknownCommandFormatException
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings

object CommandExecutor {
    // TODO: Implement command locator
    private val commands = listOf(
        CommandEcho,
        CommandPing,
    )

    fun execute(data: RespData): RespData {
        if (data !is RespArrays || data.data.isEmpty()) {
            throw SherisUnknownCommandFormatException(data)
        }

        val commandName = data.data[0]
        if (commandName !is RespBulkStrings) {
            throw SherisUnknownCommandFormatException(data)
        }

        val command = getCommandByName(commandName.data)
        return command?.execute(data)?.data ?: throw SherisUnknownCommandException(commandName.data)
    }

    private fun getCommandByName(name: String): AbstractCommand? {
        return commands.find { it.name.equals(name, ignoreCase = true) }
    }
}