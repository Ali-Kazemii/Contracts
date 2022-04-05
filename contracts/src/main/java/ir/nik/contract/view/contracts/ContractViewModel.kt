package ir.nik.contract.view.contracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import ir.awlrhm.modules.extentions.convertModelToJson
import ir.nik.contract.data.database.ContractLocalRepository
import ir.nik.contract.data.database.entity.*
import ir.nik.contract.data.local.ContractPreferenceConfiguration
import ir.nik.contract.data.network.api.ContractRemoteRepository
import ir.nik.contract.data.network.model.model.ContractBaseResponse
import ir.nik.contract.data.network.model.request.*
import ir.nik.contract.data.network.model.response.*
import ir.nik.contract.utils.*
import ir.nik.contract.view.base.ContractBaseViewModel
import kotlinx.coroutines.launch

internal class ContractViewModel(
    pref: ContractPreferenceConfiguration,
    private val remote: ContractRemoteRepository,
    private val repository: ContractLocalRepository
): ContractBaseViewModel(pref, remote) {

    val projectCustomerResponse = MutableLiveData<ContractCustomerResponse>()
    val contractListResponse = MutableLiveData<ContractResponse>()
    val contractAttachmentResponse = MutableLiveData<ContractAttachmentResponse>()
    val contractExecutiveResponse = MutableLiveData<ContractExecutiveResponse>()
    val contractGoodsResponse = MutableLiveData<ContractGoodsResponse>()
    val contractDelayResponse = MutableLiveData<ContractDelayResponse>()
    val contractExtendResponse = MutableLiveData<ContractExtendResponse>()
    val reportResponse = MutableLiveData<ContractDocumentReportResponse>()

    fun getListContracts(
        request: ContractListRequest
    ) {
        remote.getListContracts(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractResponse> {
                override fun onDataLoaded(data: ContractResponse) {
                    data.result?.let { list ->
                        deleteContracts()
                        insertContract(
                            Contract().apply {
                                this.xJson =
                                    convertModelToJson(list as List<ContractResponse.Result>)
                                this.xUpdateDate = data.dateTime
                            }
                        )
                    }
                    contractListResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            }
        )
    }


    fun getCustomer(
        request: ContractCustomerRequest
    ) {
        remote.getCustomer(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractCustomerResponse> {
                override fun onDataLoaded(data: ContractCustomerResponse) {
                    projectCustomerResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            }
        )
    }


    fun getContractAttachment(
        contractId: Long,
        request: ContractAttachmentRequest
    ) {
        remote.getContractAttachment(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractAttachmentResponse> {
                override fun onDataLoaded(data: ContractAttachmentResponse) {
                    data.result?.let { list ->
                        if (list.isNotEmpty()) {
                            deleteContractAttachment(contractId)
                            insertContractAttachment(
                                ContractAttachment().apply {
                                    this.xContractId = contractId
                                    this.xJson =
                                        convertModelToJson(list)
                                    this.xUpdateDate = data.dateTime
                                }
                            )
                        }
                    }
                    contractAttachmentResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getContractExecutive(
        contractId: Long,
        request: ContractExecutiveRequest
    ) {
        remote.getContractExecutive(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractExecutiveResponse> {
                override fun onDataLoaded(data: ContractExecutiveResponse) {
                    data.result?.let { list ->
                        if (list.isNotEmpty()) {
                            deleteContractExecutive(contractId)
                            insertContractExecutive(
                                ContractExecutive().apply {
                                    this.xContractId = contractId
                                    this.xJson =
                                        convertModelToJson(list)
                                    this.xUpdateDate = data.dateTime
                                }
                            )
                        }
                    }
                    contractExecutiveResponse.postValue(data)

                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getContractGoods(
        contractId: Long,
        request: ContractGoodsRequest
    ) {
        remote.getContractGoods(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractGoodsResponse> {
                override fun onDataLoaded(data: ContractGoodsResponse) {
                    data.result?.let { list ->
                        if (list.isNotEmpty()) {
                            deleteContractGoods(contractId)
                            insertContractGoods(
                                ContractGoods().apply {
                                    this.xContractId = contractId
                                    this.xJson =
                                        convertModelToJson(list)
                                    this.xUpdateDate = data.dateTime
                                }
                            )
                        }
                    }
                    contractGoodsResponse.postValue(data)

                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getContractDelay(
        contractId: Long,
        request: ContractDelayRequest
    ) {
        remote.getContractDelay(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractDelayResponse> {
                override fun onDataLoaded(data: ContractDelayResponse) {
                    data.result?.let { list ->
                        if (list.isNotEmpty()) {
                            deleteContractDelay(contractId)
                            insertContractDelay(
                                ContractDelay().apply {
                                    this.xContractId = contractId
                                    this.xJson =
                                        convertModelToJson(list)
                                    this.xUpdateDate = data.dateTime
                                }
                            )
                        }
                    }
                    contractDelayResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getContractExtend(
        contractId: Long,
        request: ContractExtendRequest
    ) {
        remote.getContractExtend(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractExtendResponse> {
                override fun onDataLoaded(data: ContractExtendResponse) {
                    data.result?.let { list ->
                        if (list.isNotEmpty()) {
                            deleteContractExtend(contractId)
                            insertContractExtend(
                                ContractExtend().apply {
                                    this.xContractId = contractId
                                    this.xJson =
                                        convertModelToJson(list)
                                    this.xUpdateDate = data.dateTime
                                }
                            )
                        }
                    }
                    contractExtendResponse.postValue(data)

                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getContractStatus(
        request: ContractStatusRequest
    ) {
        remote.getContractStatus(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractDocumentReportResponse> {
                override fun onDataLoaded(data: ContractDocumentReportResponse) {
                    reportResponse.postValue(data)

                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getAccountingDocumentReport(
        request: ContractAccountingDocumentRequest
    ) {
        remote.getAccountingDocumentReport(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractDocumentReportResponse> {
                override fun onDataLoaded(data: ContractDocumentReportResponse) {
                    reportResponse.postValue(data)

                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }

    fun getPeymanCardReport(
        request: ContractPeymanCardReportRequest
    ) {
        remote.getPeymanCardReport(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractDocumentReportResponse> {
                override fun onDataLoaded(data: ContractDocumentReportResponse) {
                    reportResponse.postValue(data)

                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }




    //--------------------------- Database ----------------------------------//
    /** #Begin Contract ================================================== **/
    fun getContractDb() = repository.getContracts()

    fun insertContract(contract: Contract) {
        viewModelScope.launch {
            repository.insertContract(contract)
        }
    }

    fun deleteContracts() {
        viewModelScope.launch {
            repository.deleteContract()
        }
    }

    fun getContract(json: String?): LiveData<MutableList<ContractResponse.Result>> =
        liveData {
            val list = convertJsonContractToModel(json ?: "")
            emitSource(list)
        }
    /** #End Contract ======================================================== **/




    /** #Begin Contract Attachment ============================================= **/
    fun getContractAttachment(contractId: Long) = repository.getContractAttachment(contractId)

    fun getContractAttachment(json: String?):
            LiveData<MutableList<ContractAttachmentResponse.Result>> =
        liveData {
            val list = convertJsonContractAttachmentToModel(json ?: "")
            emitSource(list)
        }

    fun deleteContractAttachment(contractId: Long) {
        viewModelScope.launch {
            repository.deleteContractAttachment(contractId)
        }
    }

    fun insertContractAttachment(contractAttachment: ContractAttachment) {
        viewModelScope.launch {
            repository.insertContractAttachment(contractAttachment)
        }
    }
    /** #End Contract Attachment ================================================ **/




    /** #Begin Contract Delay ==================================================== **/
    fun getContractDelay(contractId: Long) = repository.getContractDelay(contractId)

    fun getContractDelay(json: String?):
            LiveData<MutableList<ContractDelayResponse.Result>> =
        liveData {
            val list = convertJsonContractDelayToModel(json ?: "")
            emitSource(list)
        }

    fun deleteContractDelay(contractId: Long) {
        viewModelScope.launch {
            repository.deleteContractDelay(contractId)
        }
    }

    fun insertContractDelay(contractDelay: ContractDelay) {
        viewModelScope.launch {
            repository.insertContractDelay(contractDelay)
        }
    }
    /** #End Contract Delay ===================================================== **/




    /** #Begin Contract Executive ==================================================== **/
    fun getContractExecutive(contractId: Long) = repository.getContractExecutive(contractId)

    fun getContractExecutive(json: String?):
            LiveData<MutableList<ContractExecutiveResponse.Result>> =
        liveData {
            val list = convertJsonContractExecutiveToModel(json ?: "")
            emitSource(list)
        }

    fun deleteContractExecutive(contractId: Long) {
        viewModelScope.launch {
            repository.deleteContractExecutive(contractId)
        }
    }

    fun insertContractExecutive(contractExecutive: ContractExecutive) {
        viewModelScope.launch {
            repository.insertContractExecutive(contractExecutive)
        }
    }
    /** #End Contract Executive ===================================================== **/




    /** #Begin Contract Extend ======================================================= **/
    fun getContractExtend(contractId: Long) = repository.getContractExtend(contractId)

    fun getContractExtend(json: String?):
            LiveData<MutableList<ContractExtendResponse.Result>> =
        liveData {
            val list = convertJsonContractExtendToModel(json ?: "")
            emitSource(list)
        }

    fun deleteContractExtend(contractId: Long) {
        viewModelScope.launch {
            repository.deleteContractExtend(contractId)
        }
    }

    fun insertContractExtend(contractExtend: ContractExtend) {
        viewModelScope.launch {
            repository.insertContractExtend(contractExtend)
        }
    }
    /** #End Contract Extend ========================================================== **/




    /** #Begin Contract Goods ========================================================= **/
    fun getContractGoods(contractId: Long) = repository.getContractGoods(contractId)

    fun getContractGoods(json: String?):
            LiveData<MutableList<ContractGoodsResponse.Result>> =
        liveData {
            val list = convertJsonContractGoodsToModel(json ?: "")
            emitSource(list)
        }

    fun deleteContractGoods(contractId: Long) {
        viewModelScope.launch {
            repository.deleteContractGoods(contractId)
        }
    }

    fun insertContractGoods(contractGoods: ContractGoods) {
        viewModelScope.launch {
            repository.insertContractGoods(contractGoods)
        }
    }
    /** #End Contract Goods ============================================================ **/



}