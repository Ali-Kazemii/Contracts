package ir.nik.contract.di

import android.app.Application
import androidx.room.Room
import ir.awlrhm.modules.utils.calendar.PersianCalendar
import ir.nik.contract.data.database.ContractDatabase
import ir.nik.contract.data.database.ContractLocalRepository
import ir.nik.contract.data.local.ContractPreferenceConfiguration
import ir.nik.contract.data.network.api.ContractApiClient
import ir.nik.contract.data.network.api.ContractRemoteRepository
import ir.nik.contract.utils.DATABASE_NAME
import ir.nik.contract.view.attachment.ContractsAttachmentViewModel
import ir.nik.contract.view.base.ContractPrivateViewModel
import ir.nik.contract.view.contracts.ContractViewModel
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
    viewModel { ContractViewModel(get(), get(), get()) }
    viewModel { ContractsAttachmentViewModel(get(), get(), get(), get()) }

}


val networkModules = module {
    factory { ContractApiClient(get()).getInterface() }
    single { ContractRemoteRepository(androidContext(), get(), get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): ContractDatabase {
        /*val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tbl_Document ADD COLUMN xDocumentType INTEGER")
            }
        }*/
        return Room.databaseBuilder(application, ContractDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
//            .addMigrations(MIGRATION_2_3)
            .build()
    }

    single { provideDatabase(androidApplication()) }
    single { ContractLocalRepository(get()) }
}



val listModule = arrayListOf(appModule, viewModelModules, networkModules, databaseModule)


private val koinModules by lazy {
    loadKoinModules(listModule)
}

fun injectKoin() = koinModules