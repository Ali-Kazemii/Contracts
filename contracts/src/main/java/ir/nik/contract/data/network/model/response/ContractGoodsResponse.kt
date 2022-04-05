package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractGoodsResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: MutableList<Result> ?= null

    inner class Result{
        @SerializedName("cnT_CdID")
        val cdId: Long?= null

        @SerializedName("whS_GoodsID_fk")
        val goodsId: Long?= null

        @SerializedName("whS_GoodsFarsiName")
        val goodsName: String?= null

        @SerializedName("whS_GuName")
        val guName: String?= null

        @SerializedName("cnT_CdQuantity")
        val quantity: Int?= null

        @SerializedName("cnT_CdPrice")
        val price: Long?= null

        @SerializedName("issueAmountTemp")
        val issueAmountTemp: String?= null

        @SerializedName("factorAmount")
        val factorAmount: String?= null

        @SerializedName("remain")
        val remain: String?= null

        @SerializedName("unitPrice")
        val unitPrice: String?= null

        @SerializedName("textMember")
        val textMember: String?= null
    }
}