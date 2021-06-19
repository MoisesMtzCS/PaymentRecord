package cs.med.mtz.moises.paymentrecord.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cs.med.mtz.moises.paymentrecord.domain.Client

@Entity(tableName = "client_table")
data class ClientDTO(
    val name: String,
    val middleName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val birthdate: String,
    val gender: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_customer")
    var idCustomer: Int? = null


    fun toClient(): Client = Client(
        idCustomer = idCustomer!!,
        name = name,
        middleName = middleName,
        last_name = lastName,
        birthdate = birthdate,
        gender = gender

    )

}