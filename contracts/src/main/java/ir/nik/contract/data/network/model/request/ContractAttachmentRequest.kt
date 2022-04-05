package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractAttachmentRequest : ContractBaseGetRequest(){
    @SerializedName("cnT_CaID")
    var caId: Long?= null
}