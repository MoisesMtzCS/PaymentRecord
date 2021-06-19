package cs.med.mtz.moises.paymentrecord.data.dao

import androidx.room.*
import cs.med.mtz.moises.paymentrecord.data.dto.PaymentDTO

@Dao
interface PaymentDao {

    /** */
    @Query("SELECT * FROM payments_table WHERE id_payment = :idPayment")
    fun getById(idPayment: Int): List<PaymentDTO>


    @Query("SELECT * FROM payments_table WHERE id_customer = :idCustomer")
    suspend fun getByCustomerId(idCustomer: Int): List<PaymentDTO>


    /** */
    @Insert
    suspend fun insertOne(payment: PaymentDTO): Long

    /** */
    @Delete
    fun deletePayment(payment: PaymentDTO)

    /** */
    @Query("DELETE FROM payments_table WHERE id_customer = :idCustomer")
    suspend fun deleteContractsByGoalId(idCustomer: Int)
}