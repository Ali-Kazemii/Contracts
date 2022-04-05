package ir.nik.contract.view.contracts.goods.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.awlrhm.modules.enums.MessageStatus
import ir.awlrhm.modules.extentions.showError
import ir.awlrhm.modules.view.ActionDialog
import ir.nik.contract.data.network.model.request.ContractGoodsRequest
import ir.nik.contract.data.network.model.response.ContractGoodsResponse
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.ContractsConst
import ir.nik.contract.utils.contractGoodsJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_contract_goods_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractGoodsListFragment(
    private val contractId: Long,
    private val ssId: Long,
    private val name: String
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractViewModel>()
    private var pageNumber = 1

    override fun setup() {
        txtTitle.text = name

        if (viewModel.isOfflineMode) {
            layoutUpdate.isVisible = true

            btnUpdate.setOnClickListener {
                logout()
            }
        }
        rclContractGoods.layoutManager(LinearLayoutManager(activity))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_goods_contracts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getItems()
    }


    override fun handleOnClickListeners() {
        val activity = activity ?: return

        btnBack.setOnClickListener {
            activity.onBackPressed()
        }
    }

    override fun handleObservers() {
        viewModel.contractGoodsResponse.observe(viewLifecycleOwner, {
            it.result?.let { list ->
                setAdapter(list)

            } ?: kotlin.run {
                rclContractGoods.showNoData()
            }
        })
    }


    private fun getItems() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getContractGoods(ssId).observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclContractGoods.showNoData()
                    txtLastUpdateDate.text = getString(R.string.no_date)
                    ActionDialog.Builder()
                        .setAction(MessageStatus.ERROR)
                        .setTitle(getString(R.string.offline_mode))
                        .setDescription(getString(R.string.connect_to_internet_update))
                        .setNegative(
                            getString(R.string.understand)
                        ) {}
                        .build()
                        .show(activity.supportFragmentManager, ActionDialog.TAG)
                }
            })

        } else
            getGoods()
    }

    private fun getGoods() {
        viewModel.getContractGoods(
            ssId,
            ContractGoodsRequest().also { request ->
                request.cdId = 0
                request.pageNumber = pageNumber
                request.jsonParameters = contractGoodsJson(contractId)
                request.userId = viewModel.userId
                request.typeOperation = when (ssId) {
                    ContractsConst.ContractGoods.KEY_GOODS -> 101
                    ContractsConst.ContractGoods.KEY_SERVICES -> 102
                    else -> 103
                }
            }
        )
    }


    private fun convertJsonToModel(json: String?) {
        viewModel.getContractGoods(json).observe(viewLifecycleOwner, { list ->
            setAdapter(list)
        })
    }


    private fun setAdapter(list: MutableList<ContractGoodsResponse.Result>) {
        if (list.isEmpty())
            rclContractGoods.showNoData()
        else {
            rclContractGoods.view?.adapter = ContractsAdapter(list)
        }
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclContractGoods.showNoData()
                activity?.showError(it?.message)
            }
        })
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractGoodsListFragment::class.java.simpleName}"
    }
}