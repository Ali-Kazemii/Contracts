package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractSubSystemResponse: ContractBaseResponse() {

    @SerializedName("result")
    val result: MutableList<Result> ?= null

    inner class Result {
        @SerializedName("niK_SsID")
        val ssId: Long?= null

        @SerializedName("niK_SsFarsiName")
        val name: String?= null

        @SerializedName("tbL_RiBody")
        val body: String?= null

        @SerializedName("niK_SsParentID_fk")
        val parentId: Long?= null

        @SerializedName("prM_RoleID_fk")
        val roleId: Long?= null
    }
}