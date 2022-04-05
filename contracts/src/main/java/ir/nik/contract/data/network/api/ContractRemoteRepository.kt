package ir.nik.contract.data.network.api

import android.content.Context
import android.content.Intent
import ir.awlrhm.modules.enums.MessageStatus
import ir.awlrhm.modules.extentions.yToast
import ir.nik.contract.data.local.ContractPreferenceConfiguration
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest
import ir.nik.contract.data.network.model.model.ContractBaseResponse
import ir.nik.contract.data.network.model.request.*
import ir.nik.contract.data.network.model.response.*
import ir.nik.contract.utils.ErrorKey
import ir.nik.contract.view.gateway.ContractsGatewayActivity
import okhttp3.Headers

internal class ContractRemoteRepository(
    private val context: Context,
    private val pref: ContractPreferenceConfiguration,
    private val api: ContractApiInterface
) {

    companion object{
        const val ERROR_AUTHORIZATION = "زمان استفاده از برنامه به پایان رسیده... مجددا لاگین کنید"
    }
    
    val onDownloadVersion: ( ()-> Unit )?= null

    interface OnContractApiCallback<Model> {
        fun onDataLoaded(data: Model)
        fun onError(response: ContractBaseResponse?)
    }

    private fun handleError(body: ContractBaseResponse) {
        when (body.statusDescription) {
            ErrorKey.AUTHORIZATION -> context.showLogin()

            ErrorKey.DOWNLOAD_VERSION -> onDownloadVersion?.invoke()
        }
    }

    
    private fun Context.showLogin() {
        yToast(
            ERROR_AUTHORIZATION,
            MessageStatus.ERROR
        )
        pref.isLogout = true
        val intent = Intent(this, ContractsGatewayActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }



    /** #Begin Attachment =======================================================================**/
    fun insertProjectAttachments(
        request: PostContractDocumentAttachmentRequest,
        callback: OnContractApiCallback<ContractResponseId>
    ) {
        val call = api.insertDocumentAttachment(request)
        call.enqueue(object : ContractApiCallback<ContractResponseId>() {
            override fun response(response: ContractResponseId, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun updateProjectAttachments(
        request: PostContractDocumentAttachmentRequest,
        callback: OnContractApiCallback<ContractResponseId>
    ) {
        val call = api.updateDocumentAttachment(request)
        call.enqueue(object : ContractApiCallback<ContractResponseId>() {
            override fun response(response: ContractResponseId, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }


    fun getListAttachment(
        request: ContractDocumentAttachmentRequest,
        callback: OnContractApiCallback<ContractAttachmentListResponse>
    ) {
        val call = api.getListAttachment(request)
        call.enqueue(object : ContractApiCallback<ContractAttachmentListResponse>() {
            override fun response(response: ContractAttachmentListResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }


    fun getListAttachmentsType(
        request: ContractListAttachmentTypeRequest,
        callback: OnContractApiCallback<ContractListAttachmentTypeResponse>
    ) {
        val call = api.getListAttachmentsType(request)
        call.enqueue(object : ContractApiCallback<ContractListAttachmentTypeResponse>() {
            override fun response(response: ContractListAttachmentTypeResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun postAttachment(
        request: PostContractAttachmentRequest,
        callback: OnContractApiCallback<ContractResponseId>
    ) {
        val call = api.postAttachment(request)
        call.enqueue(object : ContractApiCallback<ContractResponseId>() {
            override fun response(response: ContractResponseId, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun deleteFile(
        request: ContractDeleteFileRequest,
        callback: OnContractApiCallback<ContractResponseId>
    ) {
        val call = api.deleteFile(request)
        call.enqueue(object : ContractApiCallback<ContractResponseId>() {
            override fun response(response: ContractResponseId, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getContractAttachment(
        request: ContractAttachmentRequest,
        callback: OnContractApiCallback<ContractAttachmentResponse>
    ) {
        val call = api.getContractAttachment(request)
        call.enqueue(object : ContractApiCallback<ContractAttachmentResponse>() {
            override fun response(response: ContractAttachmentResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    /** #End Attachment =========================================================================**/

    fun getSubSystemList(
        request: ContractBaseGetRequest,
        callback: OnContractApiCallback<ContractSubSystemResponse>
    ) {
        val call = api.getSubSystemList(request)
        call.enqueue(object : ContractApiCallback<ContractSubSystemResponse>() {
            override fun response(response: ContractSubSystemResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getContractBaseList(
        request: ContractBaseListRequest,
        callback: OnContractApiCallback<ContractBaseListResponse>
    ) {
        val call = api.getContractBaseList(request)
        call.enqueue(object : ContractApiCallback<ContractBaseListResponse>() {
            override fun response(response: ContractBaseListResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getPlace(
        request: ContractPlaceRequest,
        callback: OnContractApiCallback<ContractPlaceResponse>
    ) {
        val call = api.getPlace(request)
        call.enqueue(object : ContractApiCallback<ContractPlaceResponse>() {
            override fun response(response: ContractPlaceResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getListContracts(
        request: ContractListRequest,
        callback: OnContractApiCallback<ContractResponse>
    ) {
        val call = api.getListContracts(request)
        call.enqueue(object : ContractApiCallback<ContractResponse>() {
            override fun response(response: ContractResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }
    
    fun getContractExecutive(
        request: ContractExecutiveRequest,
        callback: OnContractApiCallback<ContractExecutiveResponse>
    ) {
        val call = api.getContractExecutive(request)
        call.enqueue(object : ContractApiCallback<ContractExecutiveResponse>() {
            override fun response(response: ContractExecutiveResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getContractGoods(
        request: ContractGoodsRequest,
        callback: OnContractApiCallback<ContractGoodsResponse>
    ) {
        val call = api.getContractGoods(request)
        call.enqueue(object : ContractApiCallback<ContractGoodsResponse>() {
            override fun response(response: ContractGoodsResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getContractDelay(
        request: ContractDelayRequest,
        callback: OnContractApiCallback<ContractDelayResponse>
    ) {
        val call = api.getContractDelay(request)
        call.enqueue(object : ContractApiCallback<ContractDelayResponse>() {
            override fun response(response: ContractDelayResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getContractExtend(
        request: ContractExtendRequest,
        callback: OnContractApiCallback<ContractExtendResponse>
    ) {
        val call = api.getContractExtend(request)
        call.enqueue(object : ContractApiCallback<ContractExtendResponse>() {
            override fun response(response: ContractExtendResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }
    
    fun getContractStatus(
        request: ContractStatusRequest,
        callback: OnContractApiCallback<ContractDocumentReportResponse>
    ) {
        val call = api.getContractStatus(request)
        call.enqueue(object : ContractApiCallback<ContractDocumentReportResponse>() {
            override fun response(response: ContractDocumentReportResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getCustomer(
        request: ContractCustomerRequest,
        callback: OnContractApiCallback<ContractCustomerResponse>
    ) {
        val call = api.getCustomer(request)
        call.enqueue(object : ContractApiCallback<ContractCustomerResponse>() {
            override fun response(response: ContractCustomerResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }


    fun getAccountingDocumentReport(
        request: ContractAccountingDocumentRequest,
        callback: OnContractApiCallback<ContractDocumentReportResponse>
    ) {
        val call = api.getAccountingDocumentReport(request)
        call.enqueue(object : ContractApiCallback<ContractDocumentReportResponse>() {
            override fun response(response: ContractDocumentReportResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }

    fun getPeymanCardReport(
        request: ContractPeymanCardReportRequest,
        callback: OnContractApiCallback<ContractDocumentReportResponse>
    ) {
        val call = api.getPeymanCardReport(request)
        call.enqueue(object : ContractApiCallback<ContractDocumentReportResponse>() {
            override fun response(response: ContractDocumentReportResponse, headers: Headers) {
                callback.onDataLoaded(response)
            }

            override fun failure(response: ContractBaseResponse?) {
                response?.let {
                    handleError(it)
                    callback.onError(response)
                } ?: kotlin.run {
                    callback.onError(response)
                }
            }
        })
    }


}