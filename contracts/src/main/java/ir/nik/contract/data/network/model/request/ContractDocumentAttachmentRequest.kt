package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest


internal class ContractDocumentAttachmentRequest: ContractBaseGetRequest() {
    @SerializedName("dmS_DaID")
    var daId: Long = 0
}