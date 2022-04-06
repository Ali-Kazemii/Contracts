package ir.nik.contract.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nik.contract.data.database.entity.ContractsDefect
import ir.nik.contract.data.database.entity.ContractsDocument
import ir.nik.contract.data.database.entity.ContractsLessonLearned
import ir.nik.contract.data.database.entity.ContractsProjectReport

@Dao
interface ContractsProgressDao {

    /** #Begin Document ======================================================================== **/
    @Query("SELECT * FROM tbl_Contracts_Document WHERE xWbsPrId= :wbsPrId AND xDocumentType= :documentType")
    fun getDocuments(wbsPrId: Long, documentType: Int): LiveData<ContractsProjectReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocument(document: ContractsDocument)

    @Query("DELETE FROM tbl_Contracts_Document WHERE xWbsPrId= :wbsPrId")
    fun deleteDocuments(wbsPrId: Long)

    /** #End Document ===========================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Defects ========================================================================= **/
    @Query("SELECT * FROM tbl_Defect WHERE xWbsPrId= :wbsPrId")
    fun getDefects(wbsPrId: Long): LiveData<ContractsProjectReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefect(defect: ContractsDefect)

    @Query("DELETE FROM tbl_Defect WHERE xWbsPrId= :wbsPrId")
    fun deleteDefects(wbsPrId: Long)

    /** #End Defects ============================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Lesson Learned ================================================================== **/
    @Query("SELECT * FROM tbl_LessonLearned WHERE xWbsPrId= :wbsPrId")
    fun getLessonsLearned(wbsPrId: Long): LiveData<ContractsProjectReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessonLearned(lesson: ContractsLessonLearned)

    @Query("DELETE FROM tbl_LessonLearned WHERE xWbsPrId= :wbsPrId")
    fun deleteLessonsLearned(wbsPrId: Long)

    /** #End Lesson learned =====================================================================**/

}