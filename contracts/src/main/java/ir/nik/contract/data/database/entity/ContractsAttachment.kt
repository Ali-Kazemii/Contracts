package ir.nik.contract.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Attachment")
class ContractsAttachment {
    @PrimaryKey(autoGenerate = true)
    var xId: Int = 0
    var xRelatedTableId: Long ?= null
    var xDcId: Long ?= null
    var xJson: String?= null
    var xUpdateDate: String?= null
}