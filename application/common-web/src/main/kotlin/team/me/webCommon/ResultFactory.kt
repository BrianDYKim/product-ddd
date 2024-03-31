package team.me.webCommon

import team.me.webCommon.failure.FailureResults
import team.me.webCommon.success.SuccessResults

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
class ResultFactory {
    fun getSuccessResult(): SuccessResults.CommonResult = SuccessResults.CommonResult(true)

    fun <T> getSingleResult(data: T): SuccessResults.SingleResult<T> =
        SuccessResults.SingleResult(
            success = true,
            data = data,
        )

    fun <T> getMultipleResult(data: List<T>): SuccessResults.MultipleResult<T> =
        SuccessResults.MultipleResult(
            success = true,
            data = data,
        )

    fun getSimpleErrorResult(
        errorCode: String,
        defaultMessage: String,
    ): FailureResults.Response = FailureResults.Response.of(errorCode, defaultMessage)

    fun getErrorResultWithFieldError(
        errorCode: String,
        defaultMessage: String,
        fieldErrorList: List<FailureResults.FieldError>,
    ) = FailureResults.Response.of(errorCode, defaultMessage, fieldErrorList)
}
