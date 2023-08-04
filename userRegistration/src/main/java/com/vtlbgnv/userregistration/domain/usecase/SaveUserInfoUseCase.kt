package com.vtlbgnv.userregistration.domain.usecase

import com.vtlbgnv.userregistration.domain.data.repository.UserRepositorySaveInfo
import com.vtlbgnv.userregistration.domain.model.UserInfoModel

class SaveUserInfoUseCase(private val repository: UserRepositorySaveInfo) {
    fun execute(userInfo: UserInfoModel) {
        return repository.saveInfo(userInfo)
    }
}