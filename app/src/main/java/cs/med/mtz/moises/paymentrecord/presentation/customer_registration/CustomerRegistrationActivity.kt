package cs.med.mtz.moises.paymentrecord.presentation.customer_registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cs.med.mtz.moises.paymentrecord.databinding.ActivityCustomerRegistrationBinding

class CustomerRegistrationActivity : AppCompatActivity() {

    /*  */
    private val binding by lazy{ ActivityCustomerRegistrationBinding.inflate(layoutInflater)}

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}