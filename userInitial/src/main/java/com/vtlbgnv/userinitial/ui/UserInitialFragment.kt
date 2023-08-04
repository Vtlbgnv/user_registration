package com.vtlbgnv.userinitial.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vtlbgnv.userinitial.R
import com.vtlbgnv.userinitial.databinding.FragmentUserInitialBinding
import com.vtlbgnv.userprofilepage.ui.UserProfileFragment


class UserInitialFragment : Fragment(R.layout.fragment_user_initial) {

    private lateinit var binding: FragmentUserInitialBinding
    private lateinit var viewModel: UserInitialViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[UserInitialViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserInitialBinding.bind(view)

        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_userInitialFragment_to_userRegistrationFragment)
        }
        viewModel.informationValidityInitialLiveData.observe(activity as LifecycleOwner) {
            findNavController().navigate(
                R.id.action_userProfileFragment_to_profilePageNavigationGraph,
                bundleOf(UserProfileFragment.USER_ID to it)
            )
        }

        viewModel.initialExceptionLiveData.observe(activity as LifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        binding.buttonLogIn.setOnClickListener {
            viewModel.userInitial()
        }

        binding.editTextEmailAddress.doOnTextChanged { text, _, _, _ ->
            viewModel.changeEmailText(text.toString())
        }
        binding.editTextTextPassword2.doOnTextChanged { text, _, _, _ ->
            viewModel.changePassText(text.toString())
        }
    }


}