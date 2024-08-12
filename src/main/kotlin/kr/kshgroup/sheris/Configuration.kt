package kr.kshgroup.sheris

data class SherisConfiguration (
    val port: Int = 6379,
    val replication: ReplicationConfiguration,
)

data class ReplicationConfiguration (
    val enabled: Boolean = false,
    val role: ReplicationRole = ReplicationRole.MASTER,
    val masterHost: String? = null,
    val masterPort: Int? = null,
) {
    companion object {
        fun of(replicaOf: String?): ReplicationConfiguration {
            return if (replicaOf?.isNotBlank() == true) {
                ReplicationConfiguration(
                    enabled = true,
                    role = ReplicationRole.SLAVE,
                    masterHost = replicaOf.split(" ")[0],
                    masterPort = replicaOf.split(" ")[1].toInt(),
                )
            } else {
                ReplicationConfiguration(enabled = false)
            }
        }
    }
}
