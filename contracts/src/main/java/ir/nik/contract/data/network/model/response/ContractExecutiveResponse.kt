package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractExecutiveResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: MutableList<Result>?= null

    inner class Result{
        @SerializedName("cnT_CepID")
        val cepId: Long?= null

        @SerializedName("cnT_CetDescription")
        val cetDescription: String?= null

        @SerializedName("cnT_CepDescription")
        val cepDescription: String?= null

        @SerializedName("cnT_CepJob")
        val cepJob :String?= null

        @SerializedName("cnT_CepMobail")
        val cepMobile: String?= null

        @SerializedName("cnT_CepStartDate")
        val cepStartDate: String?= null

        @SerializedName("cnT_CepEndDate")
        val cepEndDate: String?= null

    }
}