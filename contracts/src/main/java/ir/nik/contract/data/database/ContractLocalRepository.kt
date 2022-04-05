package ir.nik.contract.data.database

import ir.nik.contract.data.database.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ali_Kazemi on 19/09/2021.
 */
class ContractLocalRepository(
  private val database: ContractDatabase
) {

  /** #Begin Projects ================================================== **/

  fun getProjects() = database.projectDao().getProjects()

  suspend fun insertProject(project: ContractsProject){
    withContext(Dispatchers.IO){
      database.projectDao().insertProject(project)
    }
  }

  suspend fun deleteProjects(){
    withContext(Dispatchers.IO){
      database.projectDao().deleteProjects()
    }
  }
  /** #End Projects ======================================================== **/


  /** #Begin Project Report ================================================= **/
  fun getProjectReports(projectId: Long) = database.projectDao().getProjectReports(projectId)

  suspend fun insertProjectReports(projectReport: ContractsProjectReport){
    withContext(Dispatchers.IO){
      database.projectDao().insertProjectReports(projectReport)
    }
  }

  suspend fun deleteProjectReports(projectId: Long){
    withContext(Dispatchers.IO){
      database.projectDao().deleteProjectReport(projectId)
    }
  }
  /** #End Project Report =====================================================**/



  /** #Begin Document ========================================================= **/
  fun getDocuments(wbsPrId: Long, documentType: Int)
          = database.progressDao().getDocuments(wbsPrId, documentType)

  suspend fun insertDocument(document: ContractsDocument){
    withContext(Dispatchers.IO){
      database.progressDao().insertDocument(document)
    }
  }

  suspend fun deleteDocuments(wbsPrId: Long){
    withContext(Dispatchers.IO){
      database.progressDao().deleteDocuments(wbsPrId)
    }
  }
  /** #End Document =======================================================**/


  /** #Begin Defects ===================================================== **/
  fun getDefects(wbsPrId: Long)
          = database.progressDao().getDefects(wbsPrId)

  suspend fun insertDefect(defect: ContractsDefect){
    withContext(Dispatchers.IO){
      database.progressDao().insertDefect(defect)
    }
  }

  suspend fun deleteDefects(wbsPrId: Long){
    withContext(Dispatchers.IO){
      database.progressDao().deleteDefects(wbsPrId)
    }
  }
  /** #End Defects =======================================================**/



  /** #Begin Lesson Learned ============================================== **/
  fun getLessonsLearned(wbsPrId: Long)
          = database.progressDao().getLessonsLearned(wbsPrId)

  suspend fun insertLessonLearned(lesson: ContractsLessonLearned){
    withContext(Dispatchers.IO){
      database.progressDao().insertLessonLearned(lesson)
    }
  }

  suspend fun deleteLessonsLearned( wbsPrId: Long){
    withContext(Dispatchers.IO){
      database.progressDao().deleteLessonsLearned(wbsPrId)
    }
  }
  /** #End Lesson learned ==================================================**/




  /** #Begin Design Goods ================================================= **/
  fun getDesignGoods(projectId: Long)
          = database.projectDao().getDesignGoods(projectId)

  suspend fun insertDesignGoods(goods: ContractsDesignGoods){
    withContext(Dispatchers.IO){
      database.projectDao().insertDesignGoods(goods)
    }
  }

  suspend fun deleteDesignGoods(projectId: Long){
    withContext(Dispatchers.IO){
      database.projectDao().deleteDesignGoods(projectId)
    }
  }
  /** #End Design Goods ================================================= **/



  /** #Begin Project History ============================================== **/
  fun getProjectHistories(projectId: Long)
          = database.projectDao().getProjectHistories(projectId)

  suspend fun insertProjectHistories(projectHistory: ContractsProjectHistory){
    withContext(Dispatchers.IO){
      database.projectDao().insertProjectHistories(projectHistory)
    }
  }

  suspend fun deleteProjectHistories(projectId: Long){
    withContext(Dispatchers.IO){
      database.projectDao().deleteProjectHistories(projectId)
    }
  }
  /** #End Project History ================================================= **/



  /** #Begin Project Design Status ========================================== **/
  fun getProjectDesignStatus(projectId: Long)
          = database.projectDao().getProjectDesignStatus(projectId)

  suspend fun insertProjectDesignStatus(designStatus: ContractsProjectDesignStatus){
    withContext(Dispatchers.IO){
      database.projectDao().insertProjectDesignStatus(designStatus)
    }
  }

  suspend fun deleteProjectDesignStatus(projectId: Long){
    withContext(Dispatchers.IO){
      database.projectDao().deleteProjectDesignStatus(projectId)
    }
  }
  /** #End Project Design Status  ============================================== **/



  /** #Begin Project Attachment ================================================= **/
  fun getProjectAttachment(projectId: Long)
          = database.projectDao().getProjectAttachment(projectId)

  suspend fun insertProjectAttachment(projectAttachment: ContractsProjectAttachment){
    withContext(Dispatchers.IO){
      database.projectDao().insertProjectAttachment(projectAttachment)
    }
  }

  suspend fun deleteProjectAttachment(projectId: Long){
    withContext(Dispatchers.IO){
      database.projectDao().deleteProjectAttachment(projectId)
    }
  }
  /** #End Project Design Status  ============================================== **/




  /** #Begin Contract =========================================================== **/

  fun getContracts() = database.contractDao().getContract()

  suspend fun insertContract(contract: Contract){
    withContext(Dispatchers.IO){
      database.contractDao().insertContract(contract)
    }
  }

  suspend fun deleteContract(){
    withContext(Dispatchers.IO){
      database.contractDao().deleteContract()
    }
  }
  /** #End Contract ================================================================ **/




  /** #Begin Contract Attachment =================================================== **/
  fun getContractAttachment(contractId: Long)
          = database.contractDao().getContractAttachment(contractId)

  suspend fun insertContractAttachment(contractAttachment: ContractAttachment){
    withContext(Dispatchers.IO){
      database.contractDao().insertContractAttachment(contractAttachment)
    }
  }

  suspend fun deleteContractAttachment(contractId: Long){
    withContext(Dispatchers.IO){
      database.contractDao().deleteContractAttachment(contractId)
    }
  }
  /** #End Contract Design Status  ================================================= **/




  /** #Begin Contract Delay ======================================================== **/
  fun getContractDelay(contractId: Long)
          = database.contractDao().getContractDelay(contractId)

  suspend fun insertContractDelay(contractDelay: ContractDelay){
    withContext(Dispatchers.IO){
      database.contractDao().insertContractDelay(contractDelay)
    }
  }

  suspend fun deleteContractDelay(contractId: Long){
    withContext(Dispatchers.IO){
      database.contractDao().deleteContractDelay(contractId)
    }
  }
  /** #End Contract Design Status  ================================================= **/




  /** #Begin Contract Executive ======================================================== **/
  fun getContractExecutive(contractId: Long)
          = database.contractDao().getContractExecutive(contractId)

  suspend fun insertContractExecutive(contractExecutive: ContractExecutive){
    withContext(Dispatchers.IO){
      database.contractDao().insertContractExecutive(contractExecutive)
    }
  }

  suspend fun deleteContractExecutive(contractId: Long){
    withContext(Dispatchers.IO){
      database.contractDao().deleteContractExecutive(contractId)
    }
  }
  /** #End Contract Executive  ======================================================== **/



  /** #Begin Contract Extend ========================================================== **/
  fun getContractExtend(contractId: Long)
          = database.contractDao().getContractExtend(contractId)

  suspend fun insertContractExtend(contractExtend: ContractExtend){
    withContext(Dispatchers.IO){
      database.contractDao().insertContractExtend(contractExtend)
    }
  }

  suspend fun deleteContractExtend(contractId: Long){
    withContext(Dispatchers.IO){
      database.contractDao().deleteContractExtend(contractId)
    }
  }
  /** #End Contract Extend  =========================================================== **/



  /** #Begin Contract Goods ========================================================== **/
  fun getContractGoods(contractId: Long)
          = database.contractDao().getContractGoods(contractId)

  suspend fun insertContractGoods(contractGoods: ContractGoods){
    withContext(Dispatchers.IO){
      database.contractDao().insertContractGoods(contractGoods)
    }
  }

  suspend fun deleteContractGoods(contractId: Long){
    withContext(Dispatchers.IO){
      database.contractDao().deleteContractGoods(contractId)
    }
  }
  /** #End Contract Goods  =========================================================== **/



  /** #Begin Attachments ============================================================= **/
  fun getAttachments(relatedId: Long, dcId: Long)
          = database.attachmentDao().getAttachments(relatedId, dcId)

  suspend fun insertAttachment(attachment: ContractsAttachment){
    withContext(Dispatchers.IO){
      database.attachmentDao().insertAttachment(attachment)
    }
  }

  suspend fun deleteAttachments(relatedId: Long, dcId: Long){
    withContext(Dispatchers.IO){
      database.attachmentDao().deleteAttachments(relatedId, dcId)
    }
  }
  /** #End Attachments ================================================================= **/
}