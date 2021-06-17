package cs.med.mtz.moises.paymentrecord.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cs.med.mtz.moises.paymentrecord.R
import cs.med.mtz.moises.paymentrecord.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    /*  */
    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToRegistration()
    }

    /** */
    private fun navigateToRegistration(){
        CoroutineScope(Dispatchers.Main).launch {
            delay(1_200)
            findNavController().navigate(R.id.action_splashFragment_to_registration)
        }
    }
}