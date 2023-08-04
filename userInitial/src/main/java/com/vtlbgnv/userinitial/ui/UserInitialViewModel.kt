package com.vtlbgnv.userinitial.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class UserInitialViewModel : ViewModel() {

    private val passMutable = MutableLiveData<String>()
    private val emailMutable = MutableLiveData<String>()

    private val informationValidityInitialMutable = MutableLiveData<String?>()
    val informationValidityInitialLiveData = informationValidityInitialMutable

    private val initialExceptionMutable = MutableLiveData<String>()
    val initialExceptionLiveData = initialExceptionMutable

    private lateinit var auth: FirebaseAuth

    private fun isEmailValid(email: String): Boolean {
        if (email.contains(" ")) {
            return false
        }
        return true
    }

    private fun isPassValid(pass: String): Boolean {
        if (pass.contains(" ")) {
            return false
        }
        return true
    }

    fun userInitial() {
        auth = FirebaseAuth.getInstance()
        if (isEmailValid(emailMutable.value.toString()) && isPassValid(passMutable.value.toString())) {
            auth.signInWithEmailAndPassword(
                emailMutable.value.toString(),
                passMutable.value.toString()
            )
                .addOnSuccessListener {
                    val user = it.user
                    val userId = user?.uid
                    if (userId != null) {
                        informationValidityInitialMutable.value =userId
                    }
                }.addOnFailureListener {
                    initialExceptionMutable.value = it.message
                    Log.e("AAA", "userInitial: ${initialExceptionMutable.value}")
                }
        } else {
            initialExceptionMutable.value = "Password or login contains extra spaces"
        }

    }

    fun changePassText(pass: String) {
        passMutable.value = pass
    }

    fun changeEmailText(email: String) {
        emailMutable.value = email
    }
}