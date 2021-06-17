package cs.med.mtz.moises.paymentrecord.di.feature

import cs.med.mtz.moises.paymentrecord.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val homeModule:Module = module {
    viewModel {
        HomeViewModel(
            paymentRecordRepository = get()
        )
    }
}