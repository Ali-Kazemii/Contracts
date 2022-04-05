package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractListRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_ContractID")
    var contractId: Long?= 0
}