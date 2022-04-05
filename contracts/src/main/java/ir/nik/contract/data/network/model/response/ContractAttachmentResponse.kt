package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractAttachmentResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: MutableList<Result>?= null

    inner class Result{
        @SerializedName("cnT_CaID")
        val caId: Long?= null

        @SerializedName("cnT_CbID")
        val cbId: Long?= null

        @SerializedName("cnT_CbName")
        val cbName: String?= null

        @SerializedName("cnT_CaDate")
        val caDate :String?= null

        @SerializedName("cnT_CaDescription")
        val caDescription: String?= null

        @SerializedName("cnT_CaNote")
        val caNote :String?= null
    }
}