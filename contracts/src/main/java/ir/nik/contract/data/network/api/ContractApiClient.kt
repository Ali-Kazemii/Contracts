package ir.nik.contract.data.network.api

import ir.nik.contract.data.local.ContractPreferenceConfiguration

internal class ContractApiClient(
    private val pref: ContractPreferenceConfiguration
) {
    fun getInterface(): ContractApiInterface = ContractWebServiceGateway(
        pref
    )
        .retrofit
        .create(ContractApiInterface::class.java)
}