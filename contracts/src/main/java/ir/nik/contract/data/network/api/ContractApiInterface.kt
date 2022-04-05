package ir.nik.contract.data.network.api

import ir.nik.contract.data.network.model.model.ContractBaseGetRequest
import ir.nik.contract.data.network.model.request.*
import ir.nik.contract.data.network.model.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST

internal interface ContractApiInterface {


    /** #Begin Attachment ====================================================================== **/

    @HTTP(method = "DELETE", path = "DMS_DocumentAttachment/DMS_Da_Delete_MobAPI", hasBody = true)
    fun deleteFile(
        @Body request: ContractDeleteFileRequest
    ): Call<ContractResponseId>

    @POST("DMS_DocumentAttachment/DMS_Da_InsertFile_MobAPI")
    fun postAttachment(
        @Body request: PostContractAttachmentRequest
    ): Call<ContractResponseId>

    @POST("DMS_DocumentAttachment/DMS_Da_GetData_MobAPI")
    fun getListAttachment(
        @Body request: ContractDocumentAttachmentRequest
    ): Call<ContractAttachmentListResponse>

    @POST("PMS_ProjectAttachmentType/PMS_Pat_GetData_MobAPI")
    fun getListAttachmentsType(
        @Body request: ContractListAttachmentTypeRequest
    ): Call<ContractListAttachmentTypeResponse>

    @HTTP(
        method = "DELETE",
        path = "PMS_ProjectAttachment/PMS_Pa_Delete_MobAPI",
        hasBody = true
    )
    fun deleteDocuments(
        @Body request: ContractDeleteDocumentRequest
    ): Call<ContractResponseId>

    @POST("PMS_ProjectAttachment/PMS_Pa_Insert_MobAPI")
    fun insertDocumentAttachment(
        @Body request: PostContractDocumentAttachmentRequest
    ): Call<ContractResponseId>

    @POST("PMS_ProjectAttachment/PMS_Pa_Update_MobAPI")
    fun updateDocumentAttachment(
        @Body request: PostContractDocumentAttachmentRequest
    ): Call<ContractResponseId>

    @POST("CNT_ContractAttachment/CNT_Ca_GetData_MobAPI")
    fun getContractAttachment(
        @Body request: ContractAttachmentRequest
    ): Call<ContractAttachmentResponse>

    /** #End Attachment ======================================================================== **/

    @POST("NIK_SubSystem/NIK_Ss_GetData_MobAPI")
    fun getSubSystemList(
        @Body request: ContractBaseGetRequest
    ): Call<ContractSubSystemResponse>


    @POST("TBL_Place/TBL_Place_GetData_MobAPI")
    fun getPlace(
        @Body request: ContractPlaceRequest
    ): Call<ContractPlaceResponse>


    @POST("CNT_ContractBase/CNT_Cb_GetData_MobAPI")
    fun getContractBaseList(
        @Body request: ContractBaseListRequest
    ): Call<ContractBaseListResponse>

    @POST("CNT_Contract/CNT_Contract_GetData_MobAPI")
    fun getListContracts(
        @Body request: ContractListRequest
    ): Call<ContractResponse>

    @POST("CNT_ContractExecutivePerson/CNT_Cep_GetData_MobAPI")
    fun getContractExecutive(
        @Body request: ContractExecutiveRequest
    ): Call<ContractExecutiveResponse>

    @POST("CNT_ContractDetail/CNT_Cd_GetData_MobAPI")
    fun getContractGoods(
        @Body request: ContractGoodsRequest
    ): Call<ContractGoodsResponse>

    @POST("CNT_ContractDelayHistory/CNT_Cdh_GetData_MobAPI")
    fun getContractDelay(
        @Body request: ContractDelayRequest
    ): Call<ContractDelayResponse>

    @POST("CNT_ContractRevival/CNT_Cr_GetData_MobAPI")
    fun getContractExtend(
        @Body request: ContractExtendRequest
    ): Call<ContractExtendResponse>

    @POST("CNT_ContractCase/CNT_Cc_Reports_MobAPI")
    fun getContractStatus(
        @Body request: ContractStatusRequest
    ): Call<ContractDocumentReportResponse>

    @POST("TBL_Customer/TBL_Customer_GetData_MobAPI")
    fun getCustomer(
        @Body request: ContractCustomerRequest
    ): Call<ContractCustomerResponse>

    @POST("ACC_AccountingDocumentDetail/ACC_Add_Reports_MobAPI")
    fun getAccountingDocumentReport(
        @Body request: ContractAccountingDocumentRequest
    ): Call<ContractDocumentReportResponse>

    @POST("CNT_ContractCaseDetail/CNT_Ccd_Reports_MobAPI")
    fun getPeymanCardReport(
        @Body request: ContractPeymanCardReportRequest
    ): Call<ContractDocumentReportResponse>



}