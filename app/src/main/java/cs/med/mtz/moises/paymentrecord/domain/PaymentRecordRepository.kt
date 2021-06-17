package cs.med.mtz.moises.paymentrecord.domain

import android.app.AlertDialog
import cs.med.mtz.moises.paymentrecord.data.dao.ClientDao
import cs.med.mtz.moises.paymentrecord.data.dao.PaymentDao
import cs.med.mtz.moises.paymentrecord.data.dto.ClientDTO
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
        birthdate: Date,
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
        clientDao.deleteClient(client)
    }



}