package cs.med.mtz.moises.paymentrecord.presentation.profile.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cs.med.mtz.moises.paymentrecord.R
import cs.med.mtz.moises.paymentrecord.databinding.ViewHolderPaymetBinding
import cs.med.mtz.moises.paymentrecord.domain.Payment
import java.text.SimpleDateFormat
import java.util.*

class PaymentAdapter(
    private val paymets: List<Payment>,
 //   private val onClientActionClick: (Payment) -> Unit,
) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    /** */
    class PaymentViewHolder(var binding: ViewHolderPaymetBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding =
            ViewHolderPaymetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    /* */
    private val dateFormat: String = "dd/MMMM/yy HH:mm"

    /** */
    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment: Payment = paymets[position]
        holder.binding.tvAmountPaid.text = payment.amount.toString()
        holder.binding.tvDateOfPay.text = SimpleDateFormat(dateFormat, Locale.getDefault()).format(payment.registerDate)

        holder.binding.tvNumberOfPay.text = payment.idPayment.toString()
    }

    /** */
    override fun getItemCount(): Int {
        return paymets.size
    }

}