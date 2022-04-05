package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractGoodsRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_CdID")
    var cdId: Long?= null
}