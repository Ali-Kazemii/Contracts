package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: MutableList<Result>?= null

    inner class Result{
        @SerializedName("cnT_ContractID")
        val contractId: Long?= null

        @SerializedName("cnT_ContractCode")
        val contractCode: String?= null

        @SerializedName("cnT_ContractTitle")
        val contractTitle: String?= null

        @SerializedName("cnT_CtName")
        val ctName: String?= null

        @SerializedName("tbL_CustomerTitle")
        val customerTitle: String?= null

        @SerializedName("cnT_ContractApproveDate")
        val contractApproveDate:String?= null

        @SerializedName("cnT_ContractStartDate")
        val contractStartDate: String?= null

        @SerializedName("cnT_ContractExpireDate")
        val contractExpireDate: String?= null

        @SerializedName("cnT_ContractTimeTitle")
        val contractTimeTitle: String?= null

        @SerializedName("cnT_ContractPhysicalPercentPlan")
        val contractPhysicalPercentPlan: Float?= null

        @SerializedName("cnT_ContractPhysicalPercentProgress")
        val contractPhysicalPercentProgress: Float?= null

        @SerializedName("cnT_ContractPercentPriceProgress")
        val contractPercentPriceProgress: Float?= null

        @SerializedName("cnT_ContractTotalPriceCurrentProject")
        val contractTotalPriceCurrentProject: Long?= null
    }
}