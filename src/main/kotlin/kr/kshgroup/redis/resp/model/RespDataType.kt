package kr.kshgroup.redis.resp.model

enum class RespDataType (name: String, minimumVersion: RespVersion, category: RespDataCategory, firstByte: String) {
    SIMPLE_STRING("Simple strings", RespVersion.RESP2, RespDataCategory.SIMPLE, "+"),
    SIMPLE_ERROR("Simple Errors", RespVersion.RESP2, RespDataCategory.SIMPLE, "-"),
    INTEGERS("Integers", RespVersion.RESP2, RespDataCategory.SIMPLE, ":"),
    BULK_STRINGS("Bulk strings", RespVersion.RESP2, RespDataCategory.AGGREGATE, "$"),
    ARRAYS("Arrays", RespVersion.RESP2, RespDataCategory.AGGREGATE, "*"),
    NULLS("Nulls", RespVersion.RESP3, RespDataCategory.SIMPLE, "_"),
    BOOLEANS("Booleans", RespVersion.RESP3, RespDataCategory.SIMPLE, "#"),
    DOUBLES("Doubles", RespVersion.RESP3, RespDataCategory.SIMPLE, ","),
    BIG_NUMBERS("Big numbers", RespVersion.RESP3, RespDataCategory.SIMPLE, "("),
    BULK_ERRORS("Bulk errors", RespVersion.RESP3, RespDataCategory.AGGREGATE, "!"),
    VERBATIM_STRINGS("Verbatim strings", RespVersion.RESP3, RespDataCategory.AGGREGATE, "="),
    MAPS("Maps", RespVersion.RESP3, RespDataCategory.AGGREGATE, "%"),
    SETS("Sets", RespVersion.RESP3, RespDataCategory.AGGREGATE, "~"),
    PUSHES("Pushes", RespVersion.RESP3, RespDataCategory.AGGREGATE, ">")
}
