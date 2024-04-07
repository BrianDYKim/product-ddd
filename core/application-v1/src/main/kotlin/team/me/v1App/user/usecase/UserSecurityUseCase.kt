package team.me.v1App.user.usecase

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface UserSecurityUseCase : UserDetailsService {
    fun generateJwtToken(userDetails: UserDetails): String
}
