package ir.nik.contract.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.nik.contract.data.database.dao.ContractsAttachmentDao
import ir.nik.contract.data.database.dao.ContractDao
import ir.nik.contract.data.database.dao.ContractsProgressDao
import ir.nik.contract.data.database.dao.ContractsProjectDao
import ir.nik.contract.data.database.entity.*

@Database(
    entities = [
        (GeneralContractsAttachment::class),
        (ContractsDefect::class),
        (ContractsDesignGoods::class),
        (ContractsDocument::class),
        (ContractsLessonLearned::class),
        (ContractsProject::class),
        (ContractsProjectHistory::class),
        (ContractsProjectReport::class),
        (ContractsProjectAttachment::class),
        (ContractsProjectDesignStatus::class),
        (Contracts::class),
        (ContractsDelay::class),
        (ContractsExecutive::class),
        (ContractsExtend::class),
        (ContractsAttachment::class),
        (ContractsGoods::class)
    ],
    version = 1,
    exportSchema = false
)

abstract class ContractsDatabase : RoomDatabase() {
    abstract fun projectDao(): ContractsProjectDao
    abstract fun contractDao(): ContractDao
    abstract fun progressDao(): ContractsProgressDao
    abstract fun attachmentDao(): ContractsAttachmentDao
}