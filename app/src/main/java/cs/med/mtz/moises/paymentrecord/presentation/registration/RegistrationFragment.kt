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
import org.koin.android.viewmodel.ext.android.viewModel
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
            if (nameUser.isNotBlank() && middleName.isNotBlank() && lastName.isNotBlank() && gender != null && birthdate!=null) {
                registrationViewModel.createClientLiveData(
                    nameUser,
                    middleName,
                    lastName,
                    birthdate!!,
                    gender!!
                ).observe(viewLifecycleOwner) {
                    navigateToHome()
                }
            } else alert()
        }
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
    private fun alert() {
        AlertDialog.Builder(this.context)
            .setTitle(getString(R.string.empty))
            .setPositiveButton("aceptar", null)
            .create()
            .show()
    }

    /** */
    private fun setupGenderRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            gender = when (checkedId) {
                R.id.rb_male -> getString(R.string.gender_male)
                R.id.rb_feminine -> getString(R.string.gender_feminine)
                else -> null
            }
            Toast.makeText(requireContext(), gender.toString(), Toast.LENGTH_SHORT).show()
        }
    }


}