package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractPeymanCardReportRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_CcdID")
    var ccdId: Long?= null
}