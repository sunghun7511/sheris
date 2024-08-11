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
        CommandPing::class,
        CommandSet::class,
    ).map { it.primaryConstructor!!.call(instance) }

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
        return commands.find { it.getName().equals(name, ignoreCase = true) }
    }
}
