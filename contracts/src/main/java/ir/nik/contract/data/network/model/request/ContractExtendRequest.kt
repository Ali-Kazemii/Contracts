package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractExtendRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_CrID")
    var crId: Long?= null
}
