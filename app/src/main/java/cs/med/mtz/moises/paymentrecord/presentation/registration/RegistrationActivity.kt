package cs.med.mtz.moises.paymentrecord.presentation.registration

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import cs.med.mtz.moises.paymentrecord.databinding.ActivityRegistrationBinding
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    /*  */
    private val binding by lazy{ ActivityRegistrationBinding.inflate(layoutInflater)}

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        execute()
    }

    /** */
    private fun execute(){
        showCalendar()
    }

    /** */
    private fun showCalendar() {
        binding.birthdateButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de nacimiento")
                .build()
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            datePicker.addOnPositiveButtonClickListener {
                Toast.makeText(this, Date(it).toString(), Toast.LENGTH_LONG).show()
            }
            datePicker.show(supportFragmentManager, null)
        }
    }
}