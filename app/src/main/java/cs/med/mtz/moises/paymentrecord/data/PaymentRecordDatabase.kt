package cs.med.mtz.moises.paymentrecord.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import cs.med.mtz.moises.paymentrecord.data.converter.DateConverter
import cs.med.mtz.moises.paymentrecord.data.dao.ClientDao
import cs.med.mtz.moises.paymentrecord.data.dao.PaymentDao
import cs.med.mtz.moises.paymentrecord.data.dto.ClientDTO
import cs.med.mtz.moises.paymentrecord.data.dto.PaymentDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@Database(
    entities = [
        ClientDTO::class,
        PaymentDTO::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class PaymentRecordDatabase:RoomDatabase() {

    /** */
    abstract fun clientDao(): ClientDao

    /** */
    abstract fun paymentDao(): PaymentDao

    /** */
    companion object {

        @Volatile
        private var INSTANCE: PaymentRecordDatabase? = null


        private class PaymentRecordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback()

        /** */
        fun getDatabase(context: Context, scope: CoroutineScope): PaymentRecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PaymentRecordDatabase::class.java,
                    "contract_database"
                )
                    .addCallback(PaymentRecordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}