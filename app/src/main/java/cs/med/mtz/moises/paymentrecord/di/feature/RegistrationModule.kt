package cs.med.mtz.moises.paymentrecord.di.feature

import cs.med.mtz.moises.paymentrecord.domain.PaymentRecordRepository
import cs.med.mtz.moises.paymentrecord.presentation.registration.RegistrationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val registrationModule:Module = module {
    viewModel {
        RegistrationViewModel(
            paymentRecordRepository = get()
        )
    }
}