package com.ahmadshubita.moviesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.remote.utils.NetworkException
import com.ahmadshubita.moviesapp.data.remote.utils.NewsHiveException
import com.ahmadshubita.moviesapp.data.remote.utils.NullDataException
import com.ahmadshubita.moviesapp.data.remote.utils.RateLimitExceededException
import com.ahmadshubita.moviesapp.data.remote.utils.UnAuthorizedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE>(initState: STATE): ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initState) }
    val state = _state.asStateFlow()

    fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (e: NetworkException) {
                onError(e)
            } catch (e: UnAuthorizedException) {
                onError(e)
            } catch (e: NullDataException) {
                onError(e)
            } catch (e: RateLimitExceededException) {
                onError(e)
            } catch (e: NewsHiveException) {
                onError(e)
            }
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        updateState: STATE.(T) -> STATE
    ) {
        viewModelScope.launch {
            flow.collect { value ->
                _state.update { it.updateState(value) }
            }
        }
    }
}