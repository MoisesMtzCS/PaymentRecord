package cs.med.mtz.moises.paymentrecord.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cs.med.mtz.moises.paymentrecord.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    /*  */
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}