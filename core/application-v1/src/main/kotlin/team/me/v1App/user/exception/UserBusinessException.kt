package team.me.v1App.user.exception

import team.me.v1App.error.ErrorLevel
import team.me.v1App.exception.BusinessException

abstract class UserBusinessException(
    override val message: String,
    override val errorCode: String,
    override val errorLevel: ErrorLevel,
) : BusinessException(message, errorCode, errorLevel)
