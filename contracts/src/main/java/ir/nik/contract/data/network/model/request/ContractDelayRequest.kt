package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractDelayRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_CdhID")
    var cdhId: Long?= null
}