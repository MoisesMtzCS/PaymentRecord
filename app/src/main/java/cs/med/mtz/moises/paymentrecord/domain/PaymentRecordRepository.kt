package cs.med.mtz.moises.paymentrecord.domain

import android.util.Log
import cs.med.mtz.moises.paymentrecord.data.dao.ClientDao
import cs.med.mtz.moises.paymentrecord.data.dao.PaymentDao
import cs.med.mtz.moises.paymentrecord.data.dto.ClientDTO
import cs.med.mtz.moises.paymentrecord.data.dto.PaymentDTO
import java.util.*

/** */
class PaymentRecordRepository(
    private val clientDao: ClientDao,
    private val paymentDao: PaymentDao
) {

    /** */
    suspend fun createClient(
        name: String,
        middleName: String,
        lastName: String,
        birthdate: String,
        gender: String

    ) {
        val clientDTO = ClientDTO(name, middleName, lastName, birthdate, gender)
        clientDao.insert(clientDTO)
    }

    /** */
    suspend fun getClients(): List<Client> {
        val clientDTO = clientDao.getClients()
        return clientDTO.map { it.toClient() }
    }

    /** */
    suspend fun deleteClient(id: Int) {
        val client = clientDao.getById(id)
        paymentDao.deleteContractsByGoalId(id)
        clientDao.deleteClient(client)
    }

    /** */
    suspend fun createPayment(amount: Double, idCustomer: Int) {
        val paymetDTO = PaymentDTO(
            idCustomer = idCustomer,
            amount = amount,
            registerDate = Date())
        val generatedId = paymentDao.insertOne(paymetDTO)
        Log.e("IDDDD", generatedId.toString())
    }

    /** */
    suspend fun getPayments(id: Int): List<Payment> {
        val paymentDTO = paymentDao.getByCustomerId(id)
        return paymentDTO.map {
            it.toPayment()
        }
    }



}