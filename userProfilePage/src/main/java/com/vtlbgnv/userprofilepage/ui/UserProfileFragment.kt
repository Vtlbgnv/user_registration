package com.vtlbgnv.userprofilepage.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vtlbgnv.userprofilepage.R
import com.vtlbgnv.userprofilepage.databinding.FragmentUserProfileBinding
import com.vtlbgnv.userprofilepage.ui.FragmentUserInfoPopUpp.Companion.USER_DATE_OF_BIRTH
import com.vtlbgnv.userprofilepage.ui.FragmentUserInfoPopUpp.Companion.USER_FIRST_NAME
import com.vtlbgnv.userprofilepage.ui.FragmentUserInfoPopUpp.Companion.USER_SECOND_NAME


class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {
    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var firstName: String
    private lateinit var secondName: String
    private lateinit var dateOfBirth: String

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference = database.getReference("users")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserProfileBinding.bind(view)

        val id = arguments?.getString(USER_ID)
        binding.buttonLogIn.setOnClickListener {
            if (id != null) reference.child(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Здесь вы можете обработать полученные данные
                            firstName =
                                dataSnapshot.child("firstName").getValue(String::class.java) ?: ""

                            secondName =
                                dataSnapshot.child("secondName").getValue(String::class.java) ?: ""

                            dateOfBirth =
                                dataSnapshot.child("dateOfBirth").getValue(String::class.java) ?: ""

                            if (firstName != "" && secondName != "" && dateOfBirth != "") {
                                findNavController().navigate(
                                    R.id.action_userProfileFragment_to_fragmentUserInfoPopUpp,
                                    bundleOf(
                                        USER_FIRST_NAME to firstName,
                                        USER_SECOND_NAME to secondName,
                                        USER_DATE_OF_BIRTH to dateOfBirth
                                    )
                                )
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(activity, databaseError.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                })

        }

    }

    companion object {
        const val USER_ID = "id"
    }
}