package cs.med.mtz.moises.paymentrecord.domain

import java.io.Serializable

data class Client(
    val idCustomer: Int,
    val name: String,
    val middleName: String,
    val last_name: String,
    val birthdate: String,
    val gender: String
) : Serializable