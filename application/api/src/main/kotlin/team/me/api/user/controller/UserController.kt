package team.me.api.user.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.me.v1App.user.dto.LoginUserDto
import team.me.v1App.user.dto.RegisterUserDto
import team.me.v1App.user.operation.command.RegisterUserCommand
import team.me.v1App.user.operation.query.LoginUserQuery
import team.me.v1App.user.usecase.UserCommandUseCase

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userCommandService: UserCommandUseCase,
) {
    @PostMapping("")
    fun register(
        @RequestBody request: RegisterUserDto.Request,
    ): RegisterUserDto.Response {
        val command =
            with(request) {
                RegisterUserCommand(email, password, nickname, address)
            }

        val responseBody = userCommandService.store(command)

        return responseBody
    }

    fun login(
        @RequestBody request: LoginUserDto.Request,
    ): LoginUserDto.Response {
        val query =
            with(request) {
                LoginUserQuery(email, password)
            }

        return LoginUserDto.Response("qwe")
    }
}
