package kr.kshgroup.sheris

data class SherisConfiguration (
    val port: Int = 6379,
    val replication: ReplicationConfiguration = ReplicationConfiguration(),
)

data class ReplicationConfiguration (
    val enabled: Boolean = false,
    val role: ReplicationRole = ReplicationRole.MASTER,
)

enum class ReplicationRole {
    MASTER,
    SLAVE
}
