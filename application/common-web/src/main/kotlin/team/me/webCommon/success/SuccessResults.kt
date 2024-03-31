package team.me.webCommon.success

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
sealed class SuccessResults {
    open class CommonResult(
        open var success: Boolean,
    )

    data class SingleResult<T>(
        override var success: Boolean,
        var data: T,
    ) : CommonResult(success)

    data class MultipleResult<T>(
        override var success: Boolean,
        var data: List<T>,
    ) : CommonResult(success)
}
