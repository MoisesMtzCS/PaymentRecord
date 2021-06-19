package cs.med.mtz.moises.paymentrecord.presentation.profile.profile_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import cs.med.mtz.moises.paymentrecord.R
import cs.med.mtz.moises.paymentrecord.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    /*  */
    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

    // private val args: ProfileActivityArgs by navArgs()

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavigationGraph()
    }

    private fun setupNavigationGraph() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.profile_navigation)
        navController.setGraph(navGraph, intent.extras)
    }

}