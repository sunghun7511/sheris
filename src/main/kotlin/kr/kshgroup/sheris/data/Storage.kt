package kr.kshgroup.sheris.data

object Storage {
    private val data: MutableMap<String, String> = mutableMapOf()

    fun get(key: String): String? {
        return data[key]
    }

    fun set(key: String, value: String) {
        data[key] = value
    }

    fun remove(key: String): Boolean {
        return data.remove(key) != null
    }

    fun clear() {
        data.clear()
    }
}
