package cs.med.mtz.moises.paymentrecord.di.feature

import cs.med.mtz.moises.paymentrecord.presentation.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val profileModule: Module = module {
    viewModel { ProfileViewModel(paymentRecordRepository = get()) }
}