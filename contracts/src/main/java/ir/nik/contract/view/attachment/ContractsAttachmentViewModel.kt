package ir.nik.contract.view.attachment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import ir.awlrhm.modules.extentions.convertModelToJson
import ir.awlrhm.modules.utils.calendar.PersianCalendar
import ir.nik.contract.data.database.ContractLocalRepository
import ir.nik.contract.data.database.entity.ContractsAttachment
import ir.nik.contract.data.local.ContractPreferenceConfiguration
import ir.nik.contract.data.network.api.ContractRemoteRepository
import ir.nik.contract.data.network.model.model.ContractBaseResponse
import ir.nik.contract.data.network.model.request.ContractDeleteFileRequest
import ir.nik.contract.data.network.model.request.ContractDocumentAttachmentRequest
import ir.nik.contract.data.network.model.request.PostContractAttachmentRequest
import ir.nik.contract.data.network.model.response.ContractAttachmentListResponse
import ir.nik.contract.data.network.model.response.ContractResponseId
import ir.nik.contract.utils.convertJsonAttachmentToModel
import ir.nik.contract.view.base.ContractBaseViewModel
import kotlinx.coroutines.launch

internal class ContractsAttachmentViewModel(
    pref: ContractPreferenceConfiguration,
    calendar: PersianCalendar,
    private val remote: ContractRemoteRepository,
    private val repository: ContractLocalRepository
) : ContractBaseViewModel(
    pref = pref,
    calendar = calendar
) {

    val listAttachmentResponse = MutableLiveData<ContractAttachmentListResponse>()
    val downloadError = MutableLiveData<ContractBaseResponse?>()

    fun getListAttachment(
        relatedTableId: Long,
        dcId: Long,
        request: ContractDocumentAttachmentRequest
    ) {
        remote.getListAttachment(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractAttachmentListResponse> {
                override fun onDataLoaded(data: ContractAttachmentListResponse) {
                    data.result?.let { list ->
                        if (list.isNotEmpty()) {
                            deleteAttachmentDb(relatedTableId, dcId)
                            insertAttachment(
                                ContractsAttachment().apply {
                                    this.xRelatedTableId = relatedTableId
                                    this.xDcId = dcId
                                    this.xJson = convertModelToJson(list)
                                    this.xUpdateDate = data.dateTime
                                }
                            )
                        }
                    }
                    listAttachmentResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }


    fun deleteFile(
        request: ContractDeleteFileRequest
    ) {
        remote.deleteFile(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractResponseId> {
                override fun onDataLoaded(data: ContractResponseId) {
                    responseId.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    errorDelete.postValue(response)
                }
            }
        )
    }

    fun postAttachment(
        request: PostContractAttachmentRequest
    ) {
        remote.postAttachment(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractResponseId> {
                override fun onDataLoaded(data: ContractResponseId) {
                    responseId.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            }
        )
    }


    /** #Begin Database ==========================================================**/
    fun getAttachments(relatedTableId: Long, dcId: Long) =
        repository.getAttachments(relatedTableId, dcId)

    fun getAttachments(json: String?):
            LiveData<MutableList<ContractAttachmentListResponse.Result>> =
        liveData {
            val list = convertJsonAttachmentToModel(json ?: "")
            emitSource(list)
        }

    fun deleteAttachmentDb(relatedTableId: Long, dcId: Long) {
        viewModelScope.launch {
            repository.deleteAttachments(relatedTableId, dcId)
        }
    }

    fun insertAttachment(attachment: ContractsAttachment) {
        viewModelScope.launch {
            repository.insertAttachment(attachment)
        }
    }
    /** #End Database ==========================================================**/

}



