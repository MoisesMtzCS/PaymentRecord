package cs.med.mtz.moises.paymentrecord.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cs.med.mtz.moises.paymentrecord.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*  */
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}