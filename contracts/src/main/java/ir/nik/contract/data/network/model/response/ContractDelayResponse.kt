package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractDelayResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: List<Result>?= null

    inner class Result{
        @SerializedName("cnT_CdhID")
        val cdhId: Long?= null

        @SerializedName("cnT_CdhDescription")
        val description: String?= null

        @SerializedName("cnT_CdhStartDate")
        val startDate: String?= null

        @SerializedName("cnT_CdhEndDate")
        val endDate: String?= null

        @SerializedName("cnT_CdhTime")
        val time: String?= null

        @SerializedName("cnT_CdhLetterNo")
        val letterNo: String?= null

        @SerializedName("cnT_CdhLetterDate")
        val letterDate: String?= null
    }
}