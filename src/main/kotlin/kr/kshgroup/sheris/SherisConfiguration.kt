package kr.kshgroup.sheris

data class SherisConfiguration (
    val port: Int = 6379,
    val replication: ReplicationConfiguration = ReplicationConfiguration(),
) {
    companion object {
        fun of(port: Int, replicaOf: String?): SherisConfiguration {
            val replication = if (replicaOf?.isNotBlank() == true) {
                ReplicationConfiguration(
                    enabled = true,
                    role = ReplicationRole.SLAVE,
                    masterHost = replicaOf.split(" ")[0],
                    masterPort = replicaOf.split(" ")[1].toInt(),
                )
            } else {
                ReplicationConfiguration(enabled = false)
            }

            return SherisConfiguration(port = port, replication = replication)
        }
    }
}

data class ReplicationConfiguration (
    val enabled: Boolean = false,
    val role: ReplicationRole = ReplicationRole.MASTER,
    val masterHost: String? = null,
    val masterPort: Int? = null,
)

enum class ReplicationRole {
    MASTER,
    SLAVE
}
