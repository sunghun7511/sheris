package kr.kshgroup.sheris

import kr.kshgroup.sheris.util.RandomUtil

const val HARD_CODED_REPLICATION_ID = "8371b4fb1155b71f4a04d3e1bc3e18c4a990aeeb"

enum class ReplicationRole {
    MASTER,
    SLAVE,
    ;
}

class ReplicationManager(sherisServer: SherisServer, replicationConfiguration: ReplicationConfiguration) {
    val role: ReplicationRole = replicationConfiguration.role

    // For master
    var masterReplicationId: String? = null
    var masterReplicationOffset: Long? = null
    val connectedSlaves: List<String> = emptyList()

    init {
        if (replicationConfiguration.role == ReplicationRole.MASTER) {
            masterReplicationId = HARD_CODED_REPLICATION_ID
//            masterReplicationId = RandomUtil.generateRandomHex(40)
            masterReplicationOffset = 0
        }
    }
}
