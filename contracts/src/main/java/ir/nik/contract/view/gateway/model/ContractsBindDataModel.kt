package ir.nik.contract.view.gateway.model

import java.io.Serializable

data class ContractsBindDataModel(
    val token: String,
    val hostName: String,
    val personnelId: Long,
    val userId: Long,
    val imei: String,
    val osVersion: String,
    val deviceModel: String,
    val appVersion: String,
): Serializable
