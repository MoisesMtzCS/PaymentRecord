package cs.med.mtz.moises.paymentrecord.domain

import java.util.*

data class Client(
    val idCustomer: Int,
    val name: String,
    val middleName: String,
    val last_name: String,
    val birthdate: Date,
    val gender: Boolean
)