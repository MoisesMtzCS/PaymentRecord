package cs.med.mtz.moises.paymentrecord.di

import cs.med.mtz.moises.paymentrecord.di.shared.databaseModule
import org.koin.core.module.Module

fun getApplicationModules(): List<Module> {
    val featureModules: List<Module> = listOf()
    val sharedModules: List<Module> = listOf(databaseModule)
    return featureModules + sharedModules
}