package ir.nik.contract.view.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.awlrhm.modules.enums.MessageStatus
import ir.awlrhm.modules.extentions.formatDate
import ir.awlrhm.modules.extentions.yToast
import ir.awlrhm.modules.utils.calendar.PersianCalendar
import ir.nik.contract.data.local.ContractPreferenceConfiguration
import ir.nik.contract.data.network.api.ContractRemoteRepository
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest
import ir.nik.contract.data.network.model.model.ContractBaseResponse
import ir.nik.contract.data.network.model.request.ContractBaseListRequest
import ir.nik.contract.data.network.model.request.ContractPlaceRequest
import ir.nik.contract.data.network.model.response.ContractBaseListResponse
import ir.nik.contract.data.network.model.response.ContractResponseId
import ir.nik.contract.data.network.model.response.ContractSubSystemResponse
import ir.nik.contract.data.network.model.response.ContractPlaceResponse
import ir.nik.contracts.R

internal open class ContractBaseViewModel() : ViewModel() {

    private lateinit var pref: ContractPreferenceConfiguration
    private lateinit var calendar: PersianCalendar
    private lateinit var remote: ContractRemoteRepository

    constructor(
        pref: ContractPreferenceConfiguration,
        calendar: PersianCalendar,
        remote: ContractRemoteRepository
    ) : this() {
        this.calendar = calendar
        this.pref = pref
        this.remote = remote
    }

    constructor(
        pref: ContractPreferenceConfiguration,
        calendar: PersianCalendar
    ) : this() {
        this.calendar = calendar
        this.pref = pref
    }

    constructor(
        pref: ContractPreferenceConfiguration,
        remote: ContractRemoteRepository
    ) : this() {
        this.remote = remote
        this.pref = pref
    }

    constructor(
        remote: ContractRemoteRepository,
        calendar: PersianCalendar
    ) : this() {
        this.remote = remote
        this.calendar = calendar
    }

    constructor(pref: ContractPreferenceConfiguration) : this() {
        this.pref = pref
    }

    constructor(remote: ContractRemoteRepository) : this() {
        this.remote = remote
    }

    val response = MutableLiveData<ContractBaseResponse>()
    val error = MutableLiveData<ContractBaseResponse?>()
    val errorDelete = MutableLiveData<ContractBaseResponse?>()
    val responseId = MutableLiveData<ContractResponseId>()
    val errorUnhandledException = MutableLiveData<ContractBaseResponse?>()
    val contractStatusResponse = MutableLiveData<ContractBaseListResponse>()
    val subSystemResponse = MutableLiveData<ContractSubSystemResponse>()
    val placeResponse = MutableLiveData<ContractPlaceResponse>()


    fun getSubSystemList(
        request: ContractBaseGetRequest
    ){
        remote.getSubSystemList(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractSubSystemResponse> {
                override fun onDataLoaded(data: ContractSubSystemResponse) {
                    subSystemResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            })
    }


    fun getContractBaseList(
        request: ContractBaseListRequest
    ) {
        remote.getContractBaseList(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractBaseListResponse> {
                override fun onDataLoaded(data: ContractBaseListResponse) {
                    contractStatusResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            }
        )
    }

    fun checkConnection(context: Context, callback: () -> Unit) {
        if (isOfflineMode) {
            context.yToast(context.getString(R.string.no_internet), MessageStatus.ERROR)
        } else
            callback.invoke()
    }

    fun getPlace(
        request: ContractPlaceRequest
    ) {
        remote.getPlace(
            request,
            object : ContractRemoteRepository.OnContractApiCallback<ContractPlaceResponse> {
                override fun onDataLoaded(data: ContractPlaceResponse) {
                    placeResponse.postValue(data)
                }

                override fun onError(response: ContractBaseResponse?) {
                    error.postValue(response)
                }
            }
        )
    }

    /** ====================================================================================**/
    /** -------------------------------  Params --------------------------------------------**/
    /** ====================================================================================**/
    var accessToken: String
        get() = pref.accessToken
        set(value) {
            pref.accessToken = value
        }

    var hostName: String
        get() = pref.hostName
        set(value) {
            pref.hostName = value
        }

    var isLogout: Boolean
        get() = pref.isLogout
        set(value) {
            pref.isLogout = value
        }

    var isOfflineMode: Boolean
        get() = pref.isOfflineMode
        set(value){
            pref.isOfflineMode = value
        }


    var personnelId: Long
        get() = pref.personnelId
        set(value) {
            pref.personnelId = value
        }

    var userId: Long
        get() = pref.userId
        set(value) {
            pref.userId = value
        }

    val currentDate: String
        get() = formatDate(calendar.persianShortDate)

    var documentStartDate: String
        get() = pref.documentStatDate
        set(value) {
            pref.documentStatDate = value
        }

    var documentEndDate: String
        get() = pref.documentEndDate
        set(value) {
            pref.documentEndDate = value
        }

    var startDate: String
        get() = pref.startDate
        set(value) {
            pref.startDate = value
        }

    var endDate: String
        get() = pref.endDate
        set(value) {
            pref.endDate = value
        }

    val financialYear: Int /*endDate.split('/')[0].toInt()*/
        get() = calendar.persianYear

    var imei: String
        get() = pref.imei
        set(value) {
            pref.imei = value
        }

    var osVersion: String
        get() = pref.osVersion
        set(value) {
            pref.osVersion = value
        }

    var deviceModel: String
        get() = pref.deviceModel
        set(value) {
            pref.deviceModel = value
        }

    var appVersion: String
        get() = pref.appVersion
        set(value) {
            pref.appVersion = value
        }
}