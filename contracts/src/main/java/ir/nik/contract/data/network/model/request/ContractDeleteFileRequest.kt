package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseRequest

internal class ContractDeleteFileRequest: ContractBaseRequest() {
    @SerializedName("dmS_DaID")
    var daId: Long?= null
}