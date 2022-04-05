package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

internal class ContractPlaceRequest: ContractBaseGetRequest() {
    @SerializedName("tbL_PlaceID")
    var placeId: Long?= null

}