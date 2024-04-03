package team.me.v1App.component

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.spec.SecretKeySpec

/**
 * @author Doyeop Kim
 * @since 2024/04/03
 */
@Component
class JwtUtils {
    private var secretString = "secret"

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userDetails.username)
    }

    private fun createToken(
        claims: Map<String, Any?>,
        subject: String,
    ): String {
        val secretBytes: ByteArray = secretString.toByteArray()
        val secretKey = SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.jcaName)

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 유효
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }
}
