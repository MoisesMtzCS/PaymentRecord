package cs.med.mtz.moises.paymentrecord.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cs.med.mtz.moises.paymentrecord.domain.Payment
import cs.med.mtz.moises.paymentrecord.domain.PaymentRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class ProfileViewModel(private val paymentRecordRepository: PaymentRecordRepository) : ViewModel() {

    /** */
    fun deleteClientAsLiveData(id: Int): LiveData<Unit> = flow {
        val clientDelete = paymentRecordRepository.deleteClient(id)
        emit(clientDelete)
    }.asLiveData(Dispatchers.IO)

    /** */
    fun newPaymentLiveData(idCustomer: Int, amount: Double): LiveData<Unit> = flow {
            paymentRecordRepository.createPayment(
                idCustomer = idCustomer,
                amount = amount
            )
            emit(Unit)
    }.asLiveData(Dispatchers.IO)

    /** */
    fun getPaymentsAsLiveData(id: Int): LiveData<List<Payment>> = flow {
        val payments = paymentRecordRepository.getPayments(id)
        emit(payments)
    }.asLiveData(Dispatchers.IO)
}