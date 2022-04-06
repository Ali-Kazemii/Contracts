package ir.nik.contract.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nik.contract.data.database.entity.*


@Dao
interface ContractDao {

    /** #Begin Contract =========================================================================**/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContract(contract: Contracts)

    @Query("SELECT * FROM tbl_Contract")
    fun getContract(): LiveData<Contracts>

    @Query("DELETE FROM tbl_Contract")
    fun deleteContract()

    /** #End Contract ===========================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Contract Attachment ============================================================= **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContractAttachment(contractAttachment: ContractsAttachment)

    @Query("SELECT * FROM tbl_ContractAttachment WHERE xContractId= :contractId")
    fun getContractAttachment(contractId: Long): LiveData<ContractsAttachment>

    @Query("DELETE FROM tbl_ContractAttachment WHERE xContractId= :contractId")
    fun deleteContractAttachment(contractId: Long)

    /** #End Contract Attachment ================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Contract Delay ================================================================== **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContractDelay(contractDelay: ContractsDelay)

    @Query("SELECT * FROM tbl_ContractDelay WHERE xContractId= :contractId")
    fun getContractDelay(contractId: Long): LiveData<ContractsDelay>

    @Query("DELETE FROM tbl_ContractDelay WHERE xContractId= :contractId")
    fun deleteContractDelay(contractId: Long)

    /** #End Contract Delay =====================================================================**/

    //
    //
    //
    //
    //
    /** #Begin Contract Executive ============================================================== **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContractExecutive(contractExecutive: ContractsExecutive)

    @Query("SELECT * FROM tbl_ContractExecutive WHERE xContractId= :contractId")
    fun getContractExecutive(contractId: Long): LiveData<ContractsExecutive>

    @Query("DELETE FROM tbl_ContractExecutive WHERE xContractId= :contractId")
    fun deleteContractExecutive(contractId: Long)

    /** #End Contract Executive =================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Contract Extend ================================================================= **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContractExtend(contractExtend: ContractsExtend)

    @Query("SELECT * FROM tbl_ContractExtend WHERE xContractId= :contractId")
    fun getContractExtend(contractId: Long): LiveData<ContractsExtend>

    @Query("DELETE FROM tbl_ContractExtend WHERE xContractId= :contractId")
    fun deleteContractExtend(contractId: Long)

    /** #End Contract Extend ====================================================================**/
    //
    //
    //
    //
    //
    /** #Begin Contract Goods ================================================================== **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContractGoods(contractGoods: ContractsGoods)

    @Query("SELECT * FROM tbl_ContractGoods WHERE xContractId= :contractId")
    fun getContractGoods(contractId: Long): LiveData<ContractsGoods>

    @Query("DELETE FROM tbl_ContractGoods WHERE xContractId= :contractId")
    fun deleteContractGoods(contractId: Long)

    /** #End Contract Goods =====================================================================**/
}