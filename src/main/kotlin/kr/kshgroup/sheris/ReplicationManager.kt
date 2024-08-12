package kr.kshgroup.sheris

import kr.kshgroup.sheris.util.RandomUtil

enum class ReplicationRole(name: String) {
    MASTER("master"),
    SLAVE("slave")
}

class ReplicationManager(sherisServer: SherisServer, replicationConfiguration: ReplicationConfiguration) {
    val role: ReplicationRole

    // For master
    val masterReplicationId: String
    val masterReplicationOffset: Long
    val connectedSlaves: List<String> = emptyList()

    init {
        role = replicationConfiguration.role
        if (replicationConfiguration.role == ReplicationRole.MASTER) {
            masterReplicationId = RandomUtil.generateRandomHex(40)
            masterReplicationOffset = 0
        } else {
            throw IllegalArgumentException("Replication role must be MASTER")
        }
    }
}
