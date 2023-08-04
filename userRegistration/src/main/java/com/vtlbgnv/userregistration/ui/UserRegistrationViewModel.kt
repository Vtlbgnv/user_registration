package com.vtlbgnv.userregistration.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vtlbgnv.userregistration.domain.enumPuck.RegistrationExemptions
import com.vtlbgnv.userregistration.domain.model.UserInfoModel
import com.vtlbgnv.userregistration.domain.usecase.SaveUserInfoUseCase

class UserRegistrationViewModel(private val saveUserInfoUseCase: SaveUserInfoUseCase) :
    ViewModel() {
    private val passwordMutable = MutableLiveData<String>()
    private val loginMutable = MutableLiveData<String>()
    private val firstNameMutable = MutableLiveData<String>()
    private val secondNameMutable = MutableLiveData<String>()
    private val dateOfBirthMutable = MutableLiveData<String>()
    private val repeatPasswordMutable = MutableLiveData<String>()

    private val informationValidityPasswordMutable = MutableLiveData<RegistrationExemptions>()
    val informationValidityPasswordLiveData = informationValidityPasswordMutable

    private val userIsRegisterMutable = MutableLiveData<RegistrationExemptions>()
    val userIsRegisterLiveData = userIsRegisterMutable

    private val isPasswordMatchMutable = MutableLiveData<RegistrationExemptions>()
    val isPasswordMatchLiveData = isPasswordMatchMutable

    private val informationValidityLoginMutable = MutableLiveData<RegistrationExemptions>()
    val informationValidityLoginLiveData = informationValidityLoginMutable

    private val informationValidityFirstNameMutable = MutableLiveData<RegistrationExemptions>()
    val informationValidityFirstNameLiveData = informationValidityFirstNameMutable

    private val informationValiditySecondNameMutable = MutableLiveData<RegistrationExemptions>()
    val informationValiditySecondNameLiveData = informationValiditySecondNameMutable

    private val informationValidityDataOfBirthMutable = MutableLiveData<RegistrationExemptions>()
    val informationValidityDataOfBirthLiveData = informationValidityDataOfBirthMutable

    private val buttonStateMutable = MutableLiveData(false)
    val buttonStateLiveData = buttonStateMutable


    fun changePasswordText(password: String) {
        passwordMutable.value = password
    }

    fun changeRepeatedPasswordText(repPassword: String) {
        repeatPasswordMutable.value = repPassword
    }

    fun changeEmailText(login: String) {
        loginMutable.value = login
    }

    fun changeFirstNameText(firstName: String) {
        firstNameMutable.value = firstName
    }

    fun changeSecondNameText(secondName: String) {
        secondNameMutable.value = secondName
    }

    fun changeDateOfBirthText(dateOfBirth: String) {
        dateOfBirthMutable.value = dateOfBirth
    }


    private fun isPasswordValid(password: String): Boolean {

        if (password.length < 8) {
            informationValidityPasswordMutable.value = RegistrationExemptions.PASSWORD_LEN_ERROR
            return false
        }

        if (!password.any { it.isDigit() }) {
            informationValidityPasswordMutable.value = RegistrationExemptions.NO_ONE_DIGIT
            return false
        }

        if (!password.any { it.isUpperCase() }) {
            informationValidityPasswordMutable.value = RegistrationExemptions.NO_APPERCASE_LETTERS
            return false
        }

        if (!password.any { it.isLowerCase() }) {
            informationValidityPasswordMutable.value = RegistrationExemptions.NO_LOWERCASE_LETTERS
            return false
        }

        if (!password.any { it.isLetterOrDigit().not() }) {
            informationValidityPasswordMutable.value = RegistrationExemptions.NO_SPECIAL_SYMBOL
            return false
        }
        return true
    }

    private fun isLoginValid(login: String): Boolean {
        if (login.length < 8) {
            informationValidityLoginMutable.value = RegistrationExemptions.INCORRECT_LOGIN
            return false
        }
        return true
    }

    private fun isFirstNameValid(firstName: String): Boolean {
        if (firstName.length < 2) {
            informationValidityFirstNameMutable.value = RegistrationExemptions.INCORRECT_FIRSTNAME
            return false
        }
        return true
    }

    private fun isSecondNameValid(secondName: String): Boolean {
        if (secondName.length < 2) {
            informationValiditySecondNameMutable.value = RegistrationExemptions.INCORRECT_SECONDNAME
            return false
        }
        return true
    }

    private fun isPasswordsMatch(password: String, repPassword: String): Boolean {
        if (password != repPassword) {
            isPasswordMatchMutable.value =
                RegistrationExemptions.PASSWORDS_IS_NOT_MATCH
            return false
        }
        return true
    }


    private fun isDateOfBirthValid(data: String): Boolean {
        if (data.isNotEmpty()) {
            return true
        }
        informationValidityDataOfBirthMutable.value = RegistrationExemptions.EMPTY_DATE_OF_BIRTH
        return false
    }

    fun buttonState() {
        passwordMutable.value?.let {
            loginMutable.value?.let {
                firstNameMutable.value?.let {
                    secondNameMutable.value?.let {
                        dateOfBirthMutable.value?.let {
                            repeatPasswordMutable.value?.let {
                                buttonStateMutable.value = true
                            }
                        }
                    }
                }

            }
        }
    }

    fun userRegistration() {
        if (isLoginValid(loginMutable.value.toString()) &&
            isPasswordValid(passwordMutable.value.toString()) &&
            isFirstNameValid(firstNameMutable.value.toString()) &&
            isSecondNameValid(secondNameMutable.value.toString()) &&
            isDateOfBirthValid(dateOfBirthMutable.value.toString())
        ) {
            if (isPasswordsMatch(
                    passwordMutable.value.toString(),
                    repeatPasswordMutable.value.toString()
                )
            ) {
                saveUserInfoUseCase.execute(
                    userInfo = UserInfoModel(
                        firstName = firstNameMutable.value.toString(),
                        secondName = secondNameMutable.value.toString(),
                        email = loginMutable.value.toString(),
                        password = passwordMutable.value.toString(),
                        dateOfBirth = dateOfBirthMutable.value.toString()
                    )
                )
                userIsRegisterMutable.value =
                    RegistrationExemptions.USER_IS_REGISTER
            }

        }
    }
}