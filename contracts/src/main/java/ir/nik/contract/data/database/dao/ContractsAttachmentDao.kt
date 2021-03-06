package ir.nik.contract.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nik.contract.data.database.entity.GeneralContractsAttachment
import ir.nik.contract.data.database.entity.ContractsProjectReport


@Dao
interface ContractsAttachmentDao {

    /** #Begin Attachments ===================================================================== **/
    @Query("SELECT * FROM tbl_General_Contracts_Attachment WHERE xRelatedTableId= :relatedTableId AND xDcId= :dcId")
    fun getAttachments(relatedTableId: Long, dcId: Long): LiveData<ContractsProjectReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttachment(attachment: GeneralContractsAttachment)

    @Query("DELETE FROM tbl_General_Contracts_Attachment WHERE xRelatedTableId= :relatedTableId AND xDcId= :dcId")
    fun deleteAttachments(relatedTableId: Long, dcId: Long)

    /** #End Attachments ======================================================================= **/

}