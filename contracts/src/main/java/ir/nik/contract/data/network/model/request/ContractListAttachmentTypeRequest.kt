package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest

/**
 * Created by Ali_Kazemi on 28/09/2021.
 */
internal class ContractListAttachmentTypeRequest: ContractBaseGetRequest() {
    @SerializedName("pmS_PatID")
    private val patId = 0
}