package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractExecutiveRequest: ContractBaseGetRequest() {
    @SerializedName("cnT_CepID")
    var cepId: Long?= null
}