package ir.nik.contract.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ali_Kazemi on 07/09/2021.
 */

@Entity(tableName = "tbl_DesignGoods")
class ContractsDesignGoods {
    @PrimaryKey(autoGenerate = true)
    var xId: Int = 0
    var xProjectId: Long?= null
    var xJson: String?= null
    var xUpdateDate: String?= null
}