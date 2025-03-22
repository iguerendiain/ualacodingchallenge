package ignacio.guerendiain.uala.core.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.network.APICallResult
import java.io.IOException

@Composable
fun <T>APIResultErrorDialog(
    baseError: String? = null,
    baseErrorResId: Int? = null,
    result: APICallResult<T>,
    onRetry: () -> Unit,
    onCancel: () -> Unit
){
    WarningDialog(
        description = buildErrorDescription(result, baseError, baseErrorResId),
        retryButtonRes = R.string.common_retry,
        retryButtonCb = onRetry,
        cancelButtonRes = R.string.common_cancel,
        cancelButtonCb = onCancel,
        onDismissRequest = onCancel,
    )
}

@Composable
private fun <T>buildErrorDescription(
    result: APICallResult<T>,
    baseError: String? = null,
    baseErrorResId: Int? = null
): String{
    val solvedBaseError = baseError?:baseErrorResId?.let { stringResource(it) }
    var errorText = ""

    if (result.exception!=null){
        errorText = stringResource(when (result.exception){
            is IllegalArgumentException -> R.string.error_illegalargumentexception
            is IOException -> R.string.error_ioexception
            is RuntimeException -> R.string.error_runtimeexception
            else -> R.string.error_unknown
        })
    }else {
        result.errorBody?.let { errorText = stringResource(R.string.error_serverresponse).format(it) }
    }

    return solvedBaseError
        ?.let { "$solvedBaseError\n\n$errorText" }
        ?:errorText
}