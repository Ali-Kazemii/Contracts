package ir.nik.contract.data.network.model.model

import com.google.gson.annotations.SerializedName
import ir.nik.contract.utils.PAGE_SIZE
import java.io.Serializable

internal open class ContractBaseGetRequest: ContractBaseRequest(), Serializable {
    @SerializedName("jsonParameters")
    var jsonParameters: String?= null

    @SerializedName("pageNumber")
    var pageNumber: Int?= null

    @SerializedName("pageSize")
    var pageSize : Int = PAGE_SIZE

    @SerializedName("typeOperation")
    var typeOperation: Int = 0
}