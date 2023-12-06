package com.example.authlogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authlogin.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    // TODO: val oneTapClient: SignInClient
): ViewModel() {

    val currentUser = getAuthState()

    init {
        getAuthState()
    }

    private fun getAuthState() = repository.getAuthState(viewModelScope)


    fun signInAnonymously() = CoroutineScope(Dispatchers.IO).launch {
        repository.signInAnonymously()
    }

    fun signOut() = CoroutineScope(Dispatchers.IO).launch {
        repository.signOut()
    }
}
