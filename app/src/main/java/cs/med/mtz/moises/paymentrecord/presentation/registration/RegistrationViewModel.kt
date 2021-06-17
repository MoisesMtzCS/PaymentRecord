package cs.med.mtz.moises.paymentrecord.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cs.med.mtz.moises.paymentrecord.domain.PaymentRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.*

class RegistrationViewModel(private val paymentRecordRepository: PaymentRecordRepository) :
    ViewModel() {
    /** */


    fun createClientLiveData(
        name: String,
        middleName: String,
        lastName: String,
        birthdate: Date,
        gender: String
    ): LiveData<Unit> =
        flow {
            try {
            val client = paymentRecordRepository.createClient(name, middleName, lastName,birthdate,gender)
            emit(client)
            }catch (exception:Exception){
            }

        }.asLiveData(Dispatchers.IO)

}