package kr.kshgroup.redis.resp.model

enum class RespVersion (val version: Int) {
    RESP2(2),
    RESP3(3),
}