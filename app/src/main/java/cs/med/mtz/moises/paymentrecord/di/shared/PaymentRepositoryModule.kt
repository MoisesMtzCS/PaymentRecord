package cs.med.mtz.moises.paymentrecord.di.shared

import cs.med.mtz.moises.paymentrecord.domain.PaymentRecordRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val paymentRepositoryModule: Module = module {

    single {
        PaymentRecordRepository(
            clientDao = get(),
            paymentDao = get()
        )
    }
}