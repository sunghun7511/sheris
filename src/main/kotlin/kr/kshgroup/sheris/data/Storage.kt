package kr.kshgroup.sheris.data


open class Data(open val value: String)

class ExpirableData(override val value: String, private val expireAt: Long) : Data(value) {
    fun isExpired(): Boolean {
        return System.currentTimeMillis() > expireAt
    }
}

class Storage(private val data: MutableMap<String, Data> = mutableMapOf()) {
    fun get(key: String): String? {
        expireCheck(key)
        return data[key]?.value
    }

    fun set(key: String, value: String) {
        data[key] = Data(value)
    }

    fun set(key: String, value: String, expire: Int) {
        data[key] = ExpirableData(value, System.currentTimeMillis() + expire)
    }

    fun remove(key: String): Boolean {
        return data.remove(key) != null
    }

    private fun expireCheck(key: String) {
        if (!data.containsKey(key)) return

        val v = data[key]
        if (v !is ExpirableData) return
        if (!v.isExpired()) return

        remove(key)
    }
}
