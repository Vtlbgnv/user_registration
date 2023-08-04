package com.vtlbgnv.userregistration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vtlbgnv.userregistration.domain.data.repository.UserRepositorySaveInfoImpl
import com.vtlbgnv.userregistration.domain.usecase.SaveUserInfoUseCase

class UserRegistrationFactory : ViewModelProvider.Factory {
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) { UserRepositorySaveInfoImpl() }
    private val saveUserInfoUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserInfoUseCase(
            repository = userRepository
        )
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserRegistrationViewModel(
            saveUserInfoUseCase = saveUserInfoUseCase,
        ) as T
    }
}