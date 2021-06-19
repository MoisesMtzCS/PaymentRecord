package cs.med.mtz.moises.paymentrecord.presentation.registration

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import cs.med.mtz.moises.paymentrecord.R
import cs.med.mtz.moises.paymentrecord.databinding.FragmentRegistrationBinding
import cs.med.mtz.moises.paymentrecord.presentation.util.clearInput
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.net.UnknownHostException
import java.util.*

//
class RegistrationFragment : Fragment() {

    /*  */
    private val binding by lazy { FragmentRegistrationBinding.inflate(layoutInflater) }

    /*  */
    private val registrationViewModel: RegistrationViewModel by viewModel()

    /** */
    private val nameUser: String
        get() = binding.nameUser.text.toString()

    private val middleName: String
        get() = binding.middleName.text.toString()

    private val lastName: String
        get() = binding.lastName.text.toString()

    private var birthdate: String? = null

    private var gender: String? = null


    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        execute()
    }

    /** */
    private fun setupViews() {
        setupGenderRadioGroup()
    }

    /** */
    private fun execute() {
        showCalendar()
        createClientClickListener()
        navigateToHomeClickListener()
    }

    /** */
    private fun createClientClickListener() {
        binding.saveButton.setOnClickListener {
            if (nameUser.isNotBlank() && middleName.isNotBlank() && lastName.isNotBlank() && gender != null && birthdate != null) {
                registrationViewModel.createClientLiveData(
                    nameUser,
                    middleName,
                    lastName,
                    birthdate!!,
                    gender!!
                ).observe(viewLifecycleOwner) {
                    navigateToHome()
                    cleanRegistry()
                }
            } else mesageForAlert()
        }
    }

    /** */
    private fun cleanRegistry() {
        binding.nameUser.clearInput()
        binding.middleName.clearInput()
        binding.lastName.clearInput()
        binding.birthdateButton.text = getString(R.string.birthdate)
        gender = null
    }

    /** */
    private fun showCalendar() {
        binding.birthdateButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de nacimiento")
                .build()
            datePicker.show(childFragmentManager, null)
            datePicker.addOnPositiveButtonClickListener {
                binding.birthdateButton.text = datePicker.headerText
                birthdate = datePicker.headerText
            }
        }
    }

    /** */
    private fun navigateToHomeClickListener() {
        binding.toListClientButton.setOnClickListener {
            navigateToHome()
        }
    }

    /** */
    private fun navigateToHome() {
        findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
    }

    /** */
    private fun setupGenderRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            gender = when (checkedId) {
                R.id.rb_male -> getString(R.string.gender_male)
                R.id.rb_feminine -> getString(R.string.gender_feminine)
                else -> null
            }
        }
    }

    /** */
    private fun mesageForAlert() {
        val errorMessage = when {
            nameUser.isBlank() ->
                getString(R.string.name_user_is_blank)
            middleName.isBlank() ->
                getString(R.string.middlename_is_blank)
            lastName.isBlank() ->
                getString(R.string.lastname_is_blank)
            gender == null ->
                getString(R.string.gender_is_null)
            birthdate == null ->
                getString(R.string.birthdate_is_null)

            else -> getString(R.string.error_when_registering)
        }
        alert(errorMessage)
    }


    /** */
    private fun alert(message: String) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}