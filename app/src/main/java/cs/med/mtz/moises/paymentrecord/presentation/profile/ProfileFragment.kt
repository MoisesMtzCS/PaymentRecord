package cs.med.mtz.moises.paymentrecord.presentation.profile

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
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

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
        setupOnDeleteGoalClickListener(args.client.idCustomer)
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
    private fun deleteGoalLiveData(id: Int) {
        profileViewModel.deleteClientAsLiveData(id)
            .observe(viewLifecycleOwner) {
                requireActivity().onBackPressed()
            }
    }

    /** */
    private fun setupOnDeleteGoalClickListener(id: Int) {
        binding.deleteButton.setOnClickListener {
            deleteGoalLiveData(id)
        }
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
//                var sum:Double = 0.0
//                payments.forEach { sum += it.amount }
//                 binding.tvDateOfBirth.text = sum.toString()
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
    private fun createPaymentClickListener() {
        binding.payButton.setOnClickListener {
            if (mountOfPay.isNotBlank()) {
                Toast.makeText(requireContext(), "si paso $mountOfPay", Toast.LENGTH_SHORT).show()
                profileViewModel.newPaymentLiveData(
                    idCustomer = args.client.idCustomer,
                    amount = mountOfPay.toDouble()
                ).observe(viewLifecycleOwner) {
                    loadPaymentsList()
                    binding.newGoalName.visibility = View.GONE
                    binding.payButton.visibility = View.GONE
                }
            } else
                Toast.makeText(requireContext(), "no paso", Toast.LENGTH_SHORT).show()

        }
    }
}