package kr.kshgroup.sheris.resp.model

abstract class RespData<T>(
    open val dataType: RespDataType,
    open val data: T,
    protected val rawCommandString: String,
)

class RespSimpleStrings(data: String, rawCommandString: String) : RespData<String>(RespDataType.SIMPLE_STRING, data, rawCommandString)

class RespSimpleErrors(data: String, rawCommandString: String) : RespData<String>(RespDataType.SIMPLE_ERROR, data, rawCommandString)

class RespIntegers(data: Long, rawCommandString: String) : RespData<Long>(RespDataType.INTEGERS, data, rawCommandString)

//class RespBulkStrings(data: Array<RespData<?>>, rawCommandString: String) : RespData<Array<RespData>>(RespDataType.BULK_STRINGS, data, rawCommandString)
