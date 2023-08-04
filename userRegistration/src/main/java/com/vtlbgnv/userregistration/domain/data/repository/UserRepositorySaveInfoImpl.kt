package com.vtlbgnv.userregistration.domain.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.vtlbgnv.userregistration.domain.model.UserInfoModel

class UserRepositorySaveInfoImpl : UserRepositorySaveInfo {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var user: DatabaseReference
    override fun saveInfo(userInfo: UserInfoModel){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val newUser = task.result?.user
                    val userId = newUser?.uid
                    db = FirebaseDatabase.getInstance()
                    user = db.getReference("/users")
                    userId?.let {
                        val newUserRef: DatabaseReference = user.child(it)
                        newUserRef.setValue(userInfo)
                    }
                }
            }
    }
}