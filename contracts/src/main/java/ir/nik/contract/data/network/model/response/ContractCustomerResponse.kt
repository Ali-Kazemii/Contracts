package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractCustomerResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: List<Result>?= null

    inner class Result{
        @SerializedName("tbL_CustomerID")
        val customerId: Long?= null

        @SerializedName("tbL_CustomerEconomyCode")
        val customerEconomyCode: String?= null

        @SerializedName("tbL_CustomerTitle")
        val customerTitle: String?= null

        @SerializedName("tbL_CustomerMobile")
        val customerMobile: String?= null

        @SerializedName("tbL_CustomerNote")
        val customerNote: String?= null
    }
}