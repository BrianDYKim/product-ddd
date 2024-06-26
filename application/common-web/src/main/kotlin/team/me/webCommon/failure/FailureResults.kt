package team.me.webCommon.failure

import org.springframework.validation.BindingResult

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
sealed class FailureResults {
    /** Error에 대한 Response를 담당하는 data class
     * @param errorCode 에러코드
     * @param fieldErrorList 필드 에러 목록
     */
    data class Response(val errorCode: String, val defaultMessage: String, val fieldErrorList: List<FieldError>?) {
        companion object {
            // field에서 에러가 터지지 않았을 경우
            fun of(
                errorCode: String,
                defaultMessage: String,
            ): Response = Response(errorCode, defaultMessage, null)

            // field에서 에러가 터져나간경우 (bindingResult가 없는 exception에 대해서 사용)
            fun of(
                errorCode: String,
                defaultMessage: String,
                fieldErrorList: List<FieldError>,
            ) = Response(errorCode, defaultMessage, fieldErrorList)

            // field에서 에러가 터져나갔으며, exception에 bindingResult를 포함하는 경우 사용
            fun of(
                errorCode: String,
                defaultMessage: String,
                bindingResult: BindingResult,
            ) = Response(errorCode, defaultMessage, FieldError.of(bindingResult))
        }
    }

    /** Field에 대한 에러를 담아주는 클래스. FieldError에 대한 직접 생성자 참조는 불가능하다.
     * @param field 에러가 발생한 필드명
     * @param value 에러가 발생한 필드가 가지고 있는 값
     * @param reason 에러가 발생한 이유
     */
    class FieldError private constructor(val field: String, val value: String, val reason: String) {
        companion object {
            // 에러를 일으키는 필드가 하나만 존재하는 경우
            fun of(
                field: String,
                value: String,
                reason: String,
            ): FieldError = FieldError(field, value, reason)

            // bindingResult를 가진 exception에 대해서 bindingResult에 담긴 모든 에러를 반환해주는 메소드
            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrorList = bindingResult.fieldErrors

                return fieldErrorList.map { error ->
                    FieldError(error.field, error.rejectedValue.toString(), error.defaultMessage!!)
                }.toList()
            }
        }
    }
}
