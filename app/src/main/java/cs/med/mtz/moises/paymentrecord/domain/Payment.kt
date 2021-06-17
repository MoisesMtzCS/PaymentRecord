package cs.med.mtz.moises.paymentrecord.domain

import java.util.*

data class Payment(
    val idPayment: Int,
    val registerDate: Date,
    val currentDate: Date,
    val amount : Double
)