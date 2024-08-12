package kr.kshgroup.sheris

import kr.kshgroup.sheris.util.RandomUtil

const val HARD_CODED_REPLICATION_ID = "8371b4fb1155b71f4a04d3e1bc3e18c4a990aeeb"

enum class ReplicationRole(name: String) {
    MASTER("master"),
    SLAVE("slave")
}

class ReplicationManager(sherisServer: SherisServer, replicationConfiguration: ReplicationConfiguration) {
    val role: ReplicationRole = replicationConfiguration.role

    // For master
    val masterReplicationId: String
    val masterReplicationOffset: Long
    val connectedSlaves: List<String> = emptyList()

    init {
        if (replicationConfiguration.role == ReplicationRole.MASTER) {
            masterReplicationId = HARD_CODED_REPLICATION_ID
//            masterReplicationId = RandomUtil.generateRandomHex(40)
            masterReplicationOffset = 0
        } else {
            throw IllegalArgumentException("Replication role must be MASTER")
        }
    }
}
