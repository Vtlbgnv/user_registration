package com.vtlbgnv.userregistration.domain.enumPuck

enum class RegistrationExemptions(val description: String) {

    PASSWORD_LEN_ERROR("Пароль слишком короткий"),
    NO_ONE_DIGIT("Пароль должен содержать как минимум одну цифру"),
    NO_APPERCASE_LETTERS("Пароль должен содержать как минимум одну заглавную букву"),
    NO_LOWERCASE_LETTERS("Пароль должен содержать как минимум одну строчную букву"),
    NO_SPECIAL_SYMBOL("Пароль должен содержать как минимум один специальный символ(!,?,@,#)"),
    PASSWORDS_IS_NOT_MATCH("Пароли не совпадпют"),

    INCORRECT_LOGIN("Введите корректный логин"),

    INCORRECT_FIRSTNAME("Введите корректное имя"),
    INCORRECT_SECONDNAME("Введите корректную фамилию"),

    EMPTY_DATE_OF_BIRTH("Заполните дату раждения"),

    USER_IS_REGISTER("Пользователь успешно зарегистрирован")
}