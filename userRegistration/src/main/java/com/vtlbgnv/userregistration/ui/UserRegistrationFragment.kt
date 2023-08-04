package com.vtlbgnv.userregistration.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vtlbgnv.userregistration.R
import com.vtlbgnv.userregistration.databinding.FragmentUserRegistrationBinding
import java.util.Calendar


class UserRegistrationFragment : Fragment(R.layout.fragment_user_registration) {

    private lateinit var binding: FragmentUserRegistrationBinding
    private lateinit var viewModel: UserRegistrationViewModel

    private val c = Calendar.getInstance()
    private val day = c.get(Calendar.DAY_OF_MONTH)
    private val month = c.get(Calendar.MONTH)
    private val year = c.get(Calendar.YEAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(
                requireActivity(),
                UserRegistrationFactory()
            )[UserRegistrationViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserRegistrationBinding.bind(view)

        viewModel.buttonStateLiveData.observe(activity as LifecycleOwner) {
            binding.buttonRegistration.isEnabled = it
        }

        binding.buttonRegistration.setOnClickListener {
            viewModel.userRegistration()
        }

        binding.editTextPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.changePasswordText(text.toString())
            viewModel.buttonState()
        }

        binding.editTextEmailAddress.doOnTextChanged { text, _, _, _ ->
            viewModel.changeEmailText(text.toString())
            viewModel.buttonState()
        }

        binding.editTextUserName.doOnTextChanged { text, _, _, _ ->
            viewModel.changeFirstNameText(text.toString())
            viewModel.buttonState()
        }

        binding.editTextUserSecondName.doOnTextChanged { text, _, _, _ ->
            viewModel.changeSecondNameText(text.toString())
            viewModel.buttonState()
        }

        binding.editTextDate.doOnTextChanged { text, _, _, _ ->
            viewModel.changeDateOfBirthText(text.toString())
            viewModel.buttonState()
        }

        binding.buttonOpenCalendar.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    binding.editTextDate.setText("${day}/${month + 1}/${year}")
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        binding.editTextPasswordRepeat.doOnTextChanged { text, _, _, _ ->
            viewModel.changeRepeatedPasswordText(text.toString())
            viewModel.buttonState()
        }

        viewModel.userIsRegisterLiveData.observe(activity as LifecycleOwner) {
            Toast.makeText(activity, it.description, Toast.LENGTH_SHORT).show()
        }

        viewModel.informationValidityPasswordLiveData.observe(activity as LifecycleOwner) {
            binding.editTextPassword.error = it.description
            Handler().postDelayed({
                binding.editTextPassword.error = null
            }, 3000)
        }

        viewModel.informationValidityLoginLiveData.observe(activity as LifecycleOwner) {
            binding.editTextEmailAddress.error = it.description
            Handler().postDelayed({
                binding.editTextEmailAddress.error = null
            }, 3000)
        }

        viewModel.informationValidityFirstNameLiveData.observe(activity as LifecycleOwner) {
            binding.editTextUserName.error = it.description
            Handler().postDelayed({
                binding.editTextUserName.error = null
            }, 3000)
        }

        viewModel.informationValiditySecondNameLiveData.observe(activity as LifecycleOwner) {
            binding.editTextUserSecondName.error = it.description
            Handler().postDelayed({
                binding.editTextUserSecondName.error = null
            }, 3000)
        }

        viewModel.isPasswordMatchLiveData.observe(activity as LifecycleOwner) {
            binding.editTextPasswordRepeat.error = it.description
            Handler().postDelayed({
                binding.editTextPasswordRepeat.error = null
            }, 3000)
        }

        viewModel.informationValidityDataOfBirthLiveData.observe(activity as LifecycleOwner) {
            binding.editTextDate.error = it.description
            Handler().postDelayed({
                binding.editTextDate.error = null
            }, 3000)
        }


    }
}