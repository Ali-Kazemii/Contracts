package ir.nik.contract.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "tbl_ProjectDesignStatus")
class ContractsProjectDesignStatus {
    @PrimaryKey(autoGenerate = true)
    var xId: Int = 0
    var xProjectId: Long?= null
    var xJson: String?= null
    var xUpdateDate: String?= null
}