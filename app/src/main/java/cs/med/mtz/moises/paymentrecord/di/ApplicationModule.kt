package cs.med.mtz.moises.paymentrecord.di

import cs.med.mtz.moises.paymentrecord.di.feature.homeModule
import cs.med.mtz.moises.paymentrecord.di.feature.profileModule
import cs.med.mtz.moises.paymentrecord.di.feature.registrationModule
import cs.med.mtz.moises.paymentrecord.di.shared.databaseModule
import cs.med.mtz.moises.paymentrecord.di.shared.paymentRepositoryModule
import org.koin.core.module.Module

fun getApplicationModules(): List<Module> {
    val featureModules: List<Module> = listOf(registrationModule, homeModule, profileModule)
    val sharedModules: List<Module> = listOf(databaseModule, paymentRepositoryModule)
    return featureModules + sharedModules
}