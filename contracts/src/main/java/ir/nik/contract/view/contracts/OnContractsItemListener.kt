package ir.nik.contract.view.contracts

import ir.nik.contract.data.network.model.response.ContractResponse
import ir.nik.contract.view.contracts.filter.ContractsFilterModel

internal interface OnContractsItemListener {
    fun onSearch()
    fun onFilter(listFilter: MutableList<ContractsFilterModel>)
    fun onAttachment(model: ContractResponse.Result)
    fun onExecutive(model: ContractResponse.Result)
    fun onExtend(model: ContractResponse.Result)
    fun onGoods(model: ContractResponse.Result)
    fun onAccounting(model: ContractResponse.Result)
    fun onPeymanCard(model: ContractResponse.Result)
    fun onStatus(model: ContractResponse.Result)
    fun onDelay(model: ContractResponse.Result)
}