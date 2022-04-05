package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractBaseListRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_CbID")
    var cbId: Long?= null
}