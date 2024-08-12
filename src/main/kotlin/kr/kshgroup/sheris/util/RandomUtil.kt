package kr.kshgroup.sheris.util

object RandomUtil {
    fun generateRandomHex(length: Int): String {
        val source = "abcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { source.random() }
            .joinToString("")
    }
}