package ir.nik.contract.data.network.model.response

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseResponse

internal class ContractPlaceResponse: ContractBaseResponse() {
    @SerializedName("result")
    val result: List<Result>?= null

    inner class Result{
        @SerializedName("tbL_PlaceID")
        val placeId: Long?= null

        @SerializedName("tbL_PlaceParentID_fk")
        val placeParentId: Long?= null

        @SerializedName("tbL_PlaceName")
        val placeName: String?= null

        @SerializedName("tbL_PlaceNote")
        val placeNote: String?= null
    }
}