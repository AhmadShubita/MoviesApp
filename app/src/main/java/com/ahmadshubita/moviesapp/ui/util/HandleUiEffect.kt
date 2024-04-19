package com.ahmadshubita.moviesapp.ui.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ahmadshubita.moviesapp.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun HandleUiEffect(
    effect: SharedFlow<BaseViewModel.BaseUiEffect>,
    effectHandler: (BaseViewModel.BaseUiEffect) -> Unit
) {
    // Using `chokeBack` to throttle the effects, suppressing the lint warning because [reason].
    val throttleEffect = effect.chokeBack(300)

    // LaunchedEffect with 'Unit' is used here to ensure this effect runs once per composition
    // unless explicitly recomposed by the parent.
    LaunchedEffect(Unit) {
        try {
            throttleEffect.collectLatest { uiEffect ->
                effectHandler(uiEffect)
            }
        } catch (e: Exception) {
            Log.e("HandleUiEffect", "Error handling UI effect", e)
        }
    }
}


/**
 * Throttles the emission of the latest value from the flow by a specified delay.
 * Only the most recent value is emitted after the specified delay period.
 *
 * @param delayMillis The time in milliseconds to wait before emitting the latest value.
 * @return A new Flow where the emissions are delayed by the given period.
 * @throws IllegalArgumentException if the delayMillis is negative.
 */
fun <T> Flow<T>.chokeBack(delayMillis: Long): Flow<T> {
    require(delayMillis >= 0) { "Delay must not be negative" }

    return flow {
        conflate().collect { value ->
            emit(value)
            delay(delayMillis)
        }
    }
}