package team.me.api.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.me.v1App.user.dto.RegisterUserDto
import team.me.v1App.user.operation.command.RegisterUserCommand
import team.me.v1App.user.usecase.UserCommandUseCase
import team.me.webCommon.ResultFactory
import team.me.webCommon.success.SuccessResults

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userCommandService: UserCommandUseCase,
    private val resultFactory: ResultFactory,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("")
    fun register(
        @RequestBody request: RegisterUserDto.Request,
    ): SuccessResults.SingleResult<RegisterUserDto.Response> {
        val command =
            with(request) {
                RegisterUserCommand(email, password, nickname, address)
            }

        val responseBody = userCommandService.store(command)

        return resultFactory.getSingleResult(responseBody)
    }
}
