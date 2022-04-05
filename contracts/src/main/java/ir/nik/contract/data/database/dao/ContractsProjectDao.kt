package ir.nik.contract.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nik.contract.data.database.entity.*

@Dao
interface ContractsProjectDao {

    /** #Begin Project ==========================================================================**/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: ContractsProject)

    @Query("SELECT * FROM tbl_Project")
    fun getProjects(): LiveData<ContractsProject>

    @Query("DELETE FROM tbl_Project")
    fun deleteProjects()

    /** #End Project ============================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Project Report ===================================================================**/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjectReports(project: ContractsProjectReport)

    @Query("SELECT * FROM tbl_ProjectReport WHERE xProjectId= :projectId")
    fun getProjectReports(projectId: Long): LiveData<ContractsProjectReport>

    @Query("DELETE FROM tbl_ProjectReport WHERE xProjectId= :projectId")
    fun deleteProjectReport(projectId: Long)

    /** #End Project Report =====================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Design Goods ==================================================================== **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDesignGoods(designGoods: ContractsDesignGoods)

    @Query("SELECT * FROM tbl_DesignGoods WHERE xProjectId= :projectId")
    fun getDesignGoods(projectId: Long): LiveData<ContractsDesignGoods>

    @Query("DELETE FROM tbl_DesignGoods WHERE xProjectId= :projectId")
    fun deleteDesignGoods(projectId: Long)

    /** #End Design Goods =======================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Project History ================================================================= **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjectHistories(projectHistory: ContractsProjectHistory)

    @Query("SELECT * FROM tbl_ProjectHistory WHERE xProjectId= :projectId")
    fun getProjectHistories(projectId: Long): LiveData<ContractsProjectHistory>

    @Query("DELETE FROM tbl_ProjectHistory WHERE xProjectId= :projectId")
    fun deleteProjectHistories(projectId: Long)

    /** #End Project History ====================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Project Design Status =========================================================== **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjectDesignStatus(projectDesignStatus: ContractsProjectDesignStatus)

    @Query("SELECT * FROM tbl_ProjectDesignStatus WHERE xProjectId= :projectId")
    fun getProjectDesignStatus(projectId: Long): LiveData<ContractsProjectDesignStatus>

    @Query("DELETE FROM tbl_ProjectDesignStatus WHERE xProjectId= :projectId")
    fun deleteProjectDesignStatus(projectId: Long)

    /** #End Project History ====================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Project Attachment ============================================================== **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjectAttachment(projectAttachment: ContractsProjectAttachment)

    @Query("SELECT * FROM tbl_ProjectAttachment WHERE xProjectId= :projectId")
    fun getProjectAttachment(projectId: Long): LiveData<ContractsProjectAttachment>

    @Query("DELETE FROM tbl_ProjectAttachment WHERE xProjectId= :projectId")
    fun deleteProjectAttachment(projectId: Long)

    /** #End Project Attachment =================================================================**/

}