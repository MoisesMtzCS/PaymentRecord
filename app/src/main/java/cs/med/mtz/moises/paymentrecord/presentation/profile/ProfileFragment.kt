package cs.med.mtz.moises.paymentrecord.presentation.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.med.mtz.moises.paymentrecord.R
import cs.med.mtz.moises.paymentrecord.databinding.FragmentProfileBinding
import cs.med.mtz.moises.paymentrecord.domain.Payment
import cs.med.mtz.moises.paymentrecord.presentation.profile.adapter.PaymentAdapter
import cs.med.mtz.moises.paymentrecord.presentation.util.clearInput
import org.koin.android.viewmodel.ext.android.viewModel

/** */
class ProfileFragment : Fragment() {

    /*  */
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    /* */
    private val args: ProfileFragmentArgs by navArgs()

    /*  */
    private val profileViewModel: ProfileViewModel by viewModel()

    /* */
    private val mountOfPay: String
        get() = binding.etPay.text.toString()


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
    private fun execute() {
        setupOnDeleteGoalClickListener()
        makeVisibleUpdateClickListener()
        loadPaymentsList()
        createPaymentClickListener()
    }

    /* */
    private val dateFormat: String = "dd/MMMM/yy"


    /** */
    private fun setupViews() {
        binding.tvName.text = args.client.name
        binding.tvMiddleName.text = args.client.middleName
        binding.tvLastName.text = args.client.last_name
        binding.tvGender.text = args.client.gender
        binding.tvDateOfBirth.text = getString(R.string.birthdate_profile, args.client.birthdate)
    }


    /** */
    private fun deleteGoalLiveData() {
        profileViewModel.deleteClientAsLiveData(args.client.idCustomer)
            .observe(viewLifecycleOwner) {
                requireActivity().onBackPressed()
            }
    }

    /** */
    private fun setupOnDeleteGoalClickListener() {
        binding.deleteButton.setOnClickListener {
            alertConfirmDelete()
        }
    }

    /** */
    private fun alertConfirmDelete() {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.confirm_delete))
            .setPositiveButton("aceptar") { dialog, _ ->
                deleteGoalLiveData()
                dialog.dismiss()
            }
            .setNegativeButton("cancelar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /** */
    private fun makeVisibleUpdateClickListener() {
        binding.showPayButton.setOnClickListener {
            binding.newGoalName.visibility = View.VISIBLE
            binding.payButton.visibility = View.VISIBLE
        }
    }

    /** */
    private fun loadPaymentsList() {
        profileViewModel.getPaymentsAsLiveData(args.client.idCustomer)
            .observe(viewLifecycleOwner) { payments ->
                fillRecyclerView(payments)
                var sum: Double = 0.0
                payments.forEach { sum += it.amount }
                binding.tvTotalPay.text =
                    getString(R.string.total_pay, sum.toString())
            }
    }

    /** */
    private fun fillRecyclerView(payments: List<Payment>) {
        val paymetAdapter = PaymentAdapter(payments)
        binding.rvPayment.adapter = paymetAdapter
        binding.rvPayment.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    /** */
    private fun createPayment() {
        if (mountOfPay.isNotBlank()) {
            profileViewModel.newPaymentLiveData(
                idCustomer = args.client.idCustomer,
                amount = mountOfPay.toDouble()
            ).observe(viewLifecycleOwner) {
                loadPaymentsList()
                binding.newGoalName.visibility = View.GONE
                binding.payButton.visibility = View.GONE
                binding.etPay.clearInput()
            }
        } else
            alert(getString(R.string.error_pay))
    }

    /** */
    private fun createPaymentClickListener() {
        binding.payButton.setOnClickListener {
            alert(getString(R.string.confirm_pay, mountOfPay))
        }
    }

    /** */
    private fun alert(message: String) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(message)
            .setPositiveButton("aceptar") { dialog, _ ->
                createPayment()
                dialog.dismiss()
            }
            .setNegativeButton("cancelar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}