package team.me.v1App.exception

import team.me.v1App.error.ErrorLevel

abstract class BusinessException(
    override val message: String,
    open val errorCode: String,
    open val errorLevel: ErrorLevel,
) : RuntimeException(message)
