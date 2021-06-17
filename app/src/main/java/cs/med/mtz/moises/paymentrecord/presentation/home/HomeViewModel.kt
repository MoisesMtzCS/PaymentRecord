package cs.med.mtz.moises.paymentrecord.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cs.med.mtz.moises.paymentrecord.domain.Client
import cs.med.mtz.moises.paymentrecord.domain.PaymentRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class HomeViewModel(
    private val paymentRecordRepository: PaymentRecordRepository
) : ViewModel() {

    /** */
    fun getClientsLiveData(): LiveData<List<Client>> = flow {
        val clients: List<Client> = paymentRecordRepository.getClients()
        emit(clients)
    }.asLiveData(Dispatchers.IO)

}