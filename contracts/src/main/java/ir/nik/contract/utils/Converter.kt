package ir.nik.contract.utils

import androidx.lifecycle.liveData
import com.google.gson.GsonBuilder
import ir.nik.contract.data.network.model.response.*
import ir.nik.contract.data.network.model.response.ContractAttachmentResponse
import ir.nik.contract.data.network.model.response.ContractDelayResponse
import ir.nik.contract.data.network.model.response.ContractExecutiveResponse
import ir.nik.contract.data.network.model.response.ContractExtendResponse
import ir.nik.contract.data.network.model.response.ContractResponse


internal fun convertJsonContractToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            })
    }


internal fun convertJsonContractAttachmentToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractAttachmentResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }
        )
    }


internal fun convertJsonContractDelayToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractDelayResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }
        )
    }

internal fun convertJsonContractExecutiveToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractExecutiveResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }
        )
    }

internal fun convertJsonContractExtendToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractExtendResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }
        )
    }

internal fun convertJsonContractGoodsToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractGoodsResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }
        )
    }

internal fun convertJsonAttachmentToModel(json: String) =
    liveData {
        emit(
            try {
                GsonBuilder()
                    .create()
                    .fromJson(json, Array<ContractAttachmentListResponse.Result>::class.java)
                    .toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }
        )
    }