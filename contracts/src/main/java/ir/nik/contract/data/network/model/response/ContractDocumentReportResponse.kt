package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractDocumentReportResponse : ContractBaseResponse() {
    @SerializedName("result")
    val result: String? = null
}