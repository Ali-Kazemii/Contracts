package ir.nik.contract.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Contracts_Document")
class ContractsDocument {
    @PrimaryKey(autoGenerate = true)
    var xId: Int = 0
    var xWbsPrId: Long ?= null
    var xJson: String?= null
    var xUpdateDate: String?= null
    var xDocumentType: Int?= 0
}