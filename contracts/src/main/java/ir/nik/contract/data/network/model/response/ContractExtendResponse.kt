package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractExtendResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: MutableList<Result>?= null

    inner class Result{
        @SerializedName("CNT_CrID")
        val crId: Long?= null

        @SerializedName("cnT_CrCode")
        val crCode: String?= null

        @SerializedName("cnT_ContractRevivalTypeTitle")
        val extendTypeTitle: String?= null

        @SerializedName("cnT_CrDate")
        val crDate: String?= null

        @SerializedName("cnT_CbTimeTypeTitle")
        val timeTypeTitle: String?= null

        @SerializedName("cnT_CrTime")
        val crTime: String?= null

        @SerializedName("cnT_CrPercent")
        val crPercent: String?= null

        @SerializedName("cnT_CrDebitPrice")
        val debitPrice: String?= null

        @SerializedName("CNT_CrCreditPrice")
        val creditPrice: String?= null

        @SerializedName("cnT_CrExpireDate")
        val expireDate: String?= null

        @SerializedName("CNT_Crstatus")
        val status: Int?= null

        @SerializedName("CNT_CrDescription")
        val description: String?= null

    }
}