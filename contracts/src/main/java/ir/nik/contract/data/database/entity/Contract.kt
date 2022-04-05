package ir.nik.contract.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Contract")
class Contract {
    @PrimaryKey(autoGenerate = true)
    var xId: Int = 0
    var xJson: String?= null
    var xUpdateDate: String?= null
}