package cs.med.mtz.moises.paymentrecord.presentation.profile

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import cs.med.mtz.moises.paymentrecord.R
import cs.med.mtz.moises.paymentrecord.databinding.ActivityProfileBinding
import cs.med.mtz.moises.paymentrecord.presentation.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {

    /*  */
    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

    /* */
    private val args: ProfileActivityArgs by navArgs()

    /*  */
    private val profileViewModel: ProfileViewModel by viewModel()


    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        execute()
    }

    /** */
    private fun execute() {
      //  deleteGoalLiveData(args.client.idCustomer)
        setupOnDeleteGoalClickListener(args.client.idCustomer)
    }

    /** */
    private fun setupViews() {
        binding.tvName.text = args.client.name
        binding.tvMiddleName.text = args.client.middleName
        binding.tvLastName.text = args.client.last_name
        binding.tvGender.text = args.client.gender
    }


    /** */


    /** */
    private fun deleteGoalLiveData(id: Int) {
        profileViewModel.deleteClientAsLiveData(id)
            .observe(this) {}
        finish()
    }

    /** */
    private fun setupOnDeleteGoalClickListener(id: Int) {
        binding.deleteButton.setOnClickListener {
            deleteGoalLiveData(id)
        }
    }
}