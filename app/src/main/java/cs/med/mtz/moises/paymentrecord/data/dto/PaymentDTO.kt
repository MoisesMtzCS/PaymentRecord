package cs.med.mtz.moises.paymentrecord.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cs.med.mtz.moises.paymentrecord.domain.Payment
import java.util.*


@Entity(tableName = "payments_table")
data class PaymentDTO(
    @ColumnInfo(name = "id_customer") val idCustomer: Int,
    val registerDate: Date,
    val amount : Double
){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_payment") var idPayment:Int? = null

    fun toPayment(): Payment = Payment(
        idPayment = idPayment!!,
        registerDate = registerDate,
        amount = amount
    )
}