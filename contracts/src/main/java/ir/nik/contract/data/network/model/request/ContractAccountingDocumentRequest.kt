package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractAccountingDocumentRequest: ContractBaseGetRequest() {
    @SerializedName("acC_AddID")
    var addId: Long?= null
}