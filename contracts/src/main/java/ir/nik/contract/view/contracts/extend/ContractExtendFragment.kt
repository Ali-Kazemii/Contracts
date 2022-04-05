package ir.nik.contract.view.contracts.extend

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
import ir.nik.contract.data.network.model.request.ContractExtendRequest
import ir.nik.contract.data.network.model.response.ContractExtendResponse
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.contractExtendJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_contract_extend_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractExtendFragment(
    private val contractId: Long
): ContractsBaseFragment() {

    private val viewModel by viewModel<ContractViewModel>()
    private var pageNumber = 1

    override fun setup() {
        if (viewModel.isOfflineMode) {
            layoutUpdate.isVisible = true

            btnUpdate.setOnClickListener {
                logout()
            }
        }
        rclContractExtend.layoutManager(LinearLayoutManager(activity))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_extend_contracts, container, false)
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
        viewModel.contractExtendResponse.observe(viewLifecycleOwner, {
            it.result?.let { list ->
                setAdapter(list)

            }?: kotlin.run {
                rclContractExtend.showNoData()
            }
        })
    }

    private fun setAdapter(list: MutableList<ContractExtendResponse.Result>) {
        if (list.isEmpty())
            rclContractExtend.showNoData()
        else {
            rclContractExtend.view?.adapter = ContractsAdapter(list)
        }
    }

    private fun getItems() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getContractExtend(contractId).observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclContractExtend.showNoData()
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
        viewModel.getContractExtend(
            contractId,
            ContractExtendRequest().also { request ->
                request.crId = 0
                request.jsonParameters = contractExtendJson(contractId)
                request.pageNumber = pageNumber
                request.userId = viewModel.userId
                request.typeOperation = 101
            }
        )
    }



    private fun convertJsonToModel(json: String?) {
        viewModel.getContractExtend(json).observe(viewLifecycleOwner, { list ->
            setAdapter(list)
        })
    }


    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclContractExtend.showNoData()
                activity?.showError(it?.message)
            }
        })
    }

    companion object{
        val TAG = "$APP_NAME: ${ContractExtendFragment::class.java.simpleName}"
    }
}