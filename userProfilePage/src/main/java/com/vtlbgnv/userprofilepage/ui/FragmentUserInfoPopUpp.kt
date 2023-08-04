package com.vtlbgnv.userprofilepage.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vtlbgnv.userprofilepage.R
import com.vtlbgnv.userprofilepage.databinding.FragmentUserInfoPopUpBinding

class FragmentUserInfoPopUpp : Fragment(R.layout.fragment_user_info_pop_up) {
    private lateinit var binding: FragmentUserInfoPopUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserInfoPopUpBinding.bind(view)

        val firstName = arguments?.getString(USER_FIRST_NAME)
        val secondName = arguments?.getString(USER_SECOND_NAME)
        val dateOfBirth = arguments?.getString(USER_DATE_OF_BIRTH)

        binding.userInfo.text =
            getString(R.string.welcome_message, firstName, secondName, dateOfBirth)

        binding.buttonClose.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentUserInfoPopUpp_to_userProfileFragment2)
        }
    }

    companion object {
        const val USER_FIRST_NAME = "user_first_name"
        const val USER_SECOND_NAME = "user_second_name"
        const val USER_DATE_OF_BIRTH = "user_date_of_birth"
    }
}