package com.vtlbgnv.userregistration.domain.data.repository

import com.vtlbgnv.userregistration.domain.model.UserInfoModel

interface UserRepositorySaveInfo {
    fun saveInfo(userInfo: UserInfoModel)
}