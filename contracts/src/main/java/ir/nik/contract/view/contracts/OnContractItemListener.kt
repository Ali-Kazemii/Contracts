package ir.nik.contract.view.contracts

import ir.nik.contract.data.network.model.response.ContractResponse
import ir.nik.contract.view.contracts.filter.ContractFilterModel

internal interface OnContractItemListener {
    fun onSearch()
    fun onFilter(listFilter: MutableList<ContractFilterModel>)
    fun onAttachment(model: ContractResponse.Result)
    fun onExecutive(model: ContractResponse.Result)
    fun onExtend(model: ContractResponse.Result)
    fun onGoods(model: ContractResponse.Result)
    fun onAccounting(model: ContractResponse.Result)
    fun onPeymanCard(model: ContractResponse.Result)
    fun onStatus(model: ContractResponse.Result)
    fun onDelay(model: ContractResponse.Result)
}