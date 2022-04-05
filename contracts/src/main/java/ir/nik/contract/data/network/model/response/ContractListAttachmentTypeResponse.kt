package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractListAttachmentTypeResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: MutableList<Result>?= null

    inner class Result {
        @SerializedName("pmS_PatID")
        val patId: Long?= null

        @SerializedName("pmS_PatparentID_fk")
        val patParentId: Long?= null

        @SerializedName("pmS_PatName")
        val name: String?= null

        @SerializedName("pmS_PatNote")
        val note: String?= null
    }
}