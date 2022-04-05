package ir.nik.contract.data.network.model.model

import com.google.gson.annotations.SerializedName
import ir.nik.contract.utils.SSID
import java.io.Serializable

internal open class ContractBaseRequest: Serializable{
    @SerializedName("niK_SsID")
    private val ssId: Int = SSID

    @SerializedName("acC_FinancialYearID")
    var financialYearId: Int?= null

    @SerializedName("tbL_UserID")
    var userId: Long?= null
}