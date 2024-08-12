package kr.kshgroup.sheris

import kr.kshgroup.sheris.command.*
import kr.kshgroup.sheris.exception.SherisUnknownCommandFormatException
import kr.kshgroup.sheris.resp.RespData
import kr.kshgroup.sheris.resp.data.RespArrays
import kr.kshgroup.sheris.resp.data.RespBulkStrings
import kotlin.reflect.full.primaryConstructor

class CommandExecutor(private val instance: SherisServer) {
    // TODO: Implement command locator
    private val commands: List<AbstractCommand> = listOf(
        CommandEcho::class,
        CommandGet::class,
        CommandInfo::class,
        CommandPing::class,
        CommandSet::class,
    ).map { it.primaryConstructor!!.call(instance) }

    fun execute(data: RespData): CommandResult {
        if (data !is RespArrays || data.isEmpty()) {
            throw SherisUnknownCommandFormatException(data)
        }

        val command = getCommandByName(
            (data[0] as? RespBulkStrings)?.asString() ?: throw SherisUnknownCommandFormatException(data)
        )
        if (command?.validate(data) != true) {
            throw SherisUnknownCommandFormatException(data)
        }

        return command.execute(data)
    }

    private fun getCommandByName(name: String): AbstractCommand? {
        return commands.find { it.getName().equals(name, ignoreCase = true) }
    }
}
