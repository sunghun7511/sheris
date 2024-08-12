package kr.kshgroup.sheris.command

import kr.kshgroup.sheris.SherisServer

class CommandInfo(sherisServer: SherisServer) : AbstractStringCommand("INFO", sherisServer) {
    override fun validate(args: Array<String>): Boolean {
        return true
    }

    override fun execute(args: Array<String>): CommandResult {
        if (args.size < 2) {
            return CommandResult.error("wrong number of arguments for 'info' command")
        }

        val section = args[1]
        return when (section.lowercase()) {
            "replication" -> {
                val info = getReplicationInfo()
                CommandResult.bStr(info.map { "${it.key}:${it.value}" }.joinToString("\r\n"))
            }
            else -> CommandResult.error("unknown section $section")
        }
    }

    private fun getReplicationInfo(): Map<String, String> {
        return mapOf(
            "role" to sherisServer.getConfiguration().replication.role.name.lowercase(),
            "connected_slaves" to "0",
            "master_replid" to "",
            "master_repl_offset" to "",
        )
    }
}
