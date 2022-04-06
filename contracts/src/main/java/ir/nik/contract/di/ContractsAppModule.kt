package ir.nik.contract.di

import android.app.Application
import androidx.room.Room
import ir.awlrhm.modules.utils.calendar.PersianCalendar
import ir.nik.contract.data.database.ContractsDatabase
import ir.nik.contract.data.database.ContractsLocalRepository
import ir.nik.contract.data.local.ContractPreferenceConfiguration
import ir.nik.contract.data.network.api.ContractApiClient
import ir.nik.contract.data.network.api.ContractRemoteRepository
import ir.nik.contract.utils.DATABASE_NAME
import ir.nik.contract.view.attachment.ContractsAttachmentViewModel
import ir.nik.contract.view.base.ContractPrivateViewModel
import ir.nik.contract.view.contracts.ContractsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val appModule = module {
    single { PersianCalendar() }
    single {
        ContractPreferenceConfiguration(
            androidContext()
        )
    }
}

val viewModelModules = module {
    viewModel { ContractPrivateViewModel(get()) }
    viewModel { ContractsViewModel(get(), get(), get()) }
    viewModel { ContractsAttachmentViewModel(get(), get(), get(), get()) }

}


val networkModules = module {
    factory { ContractApiClient(get()).getInterface() }
    single { ContractRemoteRepository(androidContext(), get(), get()) }
}

val databaseModule = module {

    fun provideContractsDatabase(application: Application): ContractsDatabase {
        /*val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tbl_Contracts_Document ADD COLUMN xDocumentType INTEGER")
            }
        }*/
        return Room.databaseBuilder(application, ContractsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
//            .addMigrations(MIGRATION_2_3)
            .build()
    }

    single { provideContractsDatabase(androidApplication()) }
    single { ContractsLocalRepository(get()) }
}



val listModule = arrayListOf(appModule, viewModelModules, networkModules, databaseModule)


private val koinContractsModules by lazy {
    loadKoinModules(listModule)
}

fun injectContractsKoin() = koinContractsModules