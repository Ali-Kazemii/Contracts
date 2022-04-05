package ir.nik.contract.data.network.model.request

import com.google.gson.annotations.SerializedName
import ir.nik.contract.data.network.model.model.ContractBaseRequest

/**
 * Created by Ali_Kazemi on 28/09/2021.
 */
internal class ContractDeleteDocumentRequest: ContractBaseRequest() {
    @SerializedName("pmS_PaID")
    var paId: Long?= null
}