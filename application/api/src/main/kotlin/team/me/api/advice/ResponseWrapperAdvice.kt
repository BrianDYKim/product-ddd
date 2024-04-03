package team.me.api.advice

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import team.me.webCommon.ResultFactory

/**
 * @author Doyeop Kim
 * @since 2024/04/03
 */
@ControllerAdvice
class ResponseWrapperAdvice(private val resultFactory: ResultFactory) : ResponseBodyAdvice<Any> {
    override fun supports(
        returnType: MethodParameter,
        converterType: Class<out HttpMessageConverter<*>>,
    ): Boolean {
        return true
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse,
    ): Any? {
        return when (body) {
            is List<*> -> resultFactory.getMultipleResult(body)
            is Any -> resultFactory.getSingleResult(body)
            else -> resultFactory.getSuccessResult()
        }
    }
}
