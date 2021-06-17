package cs.med.mtz.moises.paymentrecord.data

import androidx.room.Database
import androidx.room.TypeConverters
import cs.med.mtz.moises.paymentrecord.data.converter.DateConverter
import cs.med.mtz.moises.paymentrecord.data.dto.ClientDTO
import cs.med.mtz.moises.paymentrecord.data.dto.PaymentsDTO


@Database(
    entities = [
        ClientDTO::class,
        PaymentsDTO::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class PaymentRecord {
}