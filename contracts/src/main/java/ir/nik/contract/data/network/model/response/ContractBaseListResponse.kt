package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractBaseListResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: List<Result>?= null

    inner class Result{
        @SerializedName("cnT_CbID")
        val cbId: Long?= null

        @SerializedName("cnT_CbParentID_fk")
        val cbParentId: Long?= null

        @SerializedName("cnT_CbName")
        val cbName: String?= null

        @SerializedName("cnT_CbSystemCode")
        val cbSystemCode: String?= null

        @SerializedName("cnT_CbNote")
        val cbNote: String?= null

        @SerializedName("cnT_CbActive")
        val cbActive: Int?= null

        @SerializedName("cnT_CbStatus")
        val cbStatus: Int?= null

        @SerializedName("cnT_CbType")
        val cbType: Int?= null

        @SerializedName("cnT_CbRegisterDate")
        val cbRegisterDate: String?= null

        @SerializedName("cnT_CbDeleteDate")
        val cbDeleteDate: String?= null
    }
}