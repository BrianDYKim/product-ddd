package team.me.v1App.user.exception

import team.me.v1App.error.ErrorLevel

class UserAuthenticationException(
    override val message: String = "User authentication failed",
    override val errorCode: String,
    override val errorLevel: ErrorLevel,
) : UserBusinessException(message, errorCode, errorLevel)
