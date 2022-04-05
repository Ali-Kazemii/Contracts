package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractCustomerRequest : ContractBaseGetRequest() {
    @SerializedName("tbL_CustomerID")
    var customerId: Long? = null
}