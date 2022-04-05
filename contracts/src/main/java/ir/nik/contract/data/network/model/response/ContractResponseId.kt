package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractResponseId: ContractBaseResponse() {
    @SerializedName("result")
    val result: Long?= null
}