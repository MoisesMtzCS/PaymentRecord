package cs.med.mtz.moises.paymentrecord.di.shared

import cs.med.mtz.moises.paymentrecord.data.PaymentRecordDatabase
import cs.med.mtz.moises.paymentrecord.data.dao.ClientDao
import cs.med.mtz.moises.paymentrecord.data.dao.PaymentDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {
    single<PaymentRecordDatabase> {
        PaymentRecordDatabase.getDatabase(
            androidContext(),
            CoroutineScope(SupervisorJob())
        )
    }

    single<ClientDao> {
        get<PaymentRecordDatabase>().clientDao()
    }

    single<PaymentDao> {
        get<PaymentRecordDatabase>().paymentDao()
    }

}