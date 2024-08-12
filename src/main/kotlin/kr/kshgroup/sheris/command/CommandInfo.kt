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
        val replicationManager = sherisServer.replicationManager
        return mapOf(
            "role" to replicationManager.role.name,
            "connected_slaves" to replicationManager.connectedSlaves.size.toString(),
            "master_replid" to replicationManager.masterReplicationId,
            "master_repl_offset" to replicationManager.masterReplicationOffset.toString(),
        )
    }
}
