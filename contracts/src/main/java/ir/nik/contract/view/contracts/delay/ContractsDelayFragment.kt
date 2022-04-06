package ir.nik.contract.view.contracts.delay

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
import ir.nik.contract.data.network.model.request.ContractDelayRequest
import ir.nik.contract.data.network.model.response.ContractDelayResponse
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.contractDelayJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractsViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_contract_delay_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractsDelayFragment(
    private val contractId: Long
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractsViewModel>()
    private val pageNumber = 1

    override fun setup() {
        if (viewModel.isOfflineMode) {
            layoutUpdate.isVisible = true

            btnUpdate.setOnClickListener {
                logout()
            }
        }

        rclContractDelay.layoutManager(LinearLayoutManager(activity))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_delay_contracts, container, false)
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
        viewModel.contractDelayResponse.observe(viewLifecycleOwner, {
            it.result?.let { list ->
                setAdapter(list)


            } ?: kotlin.run {
                rclContractDelay.showNoData()
            }
        })
    }

    private fun getItems() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getContractDelay(contractId).observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclContractDelay.showNoData()
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
            viewModel.getContractDelay(
                contractId,
                ContractDelayRequest().also { request ->
                    request.cdhId = 0
                    request.jsonParameters = contractDelayJson(contractId)
                    request.userId = viewModel.userId
                    request.pageNumber = pageNumber
                    request.typeOperation = 101
                }
            )
    }


    private fun convertJsonToModel(json: String?) {
        viewModel.getContractDelay(json).observe(viewLifecycleOwner, { list ->
            setAdapter(list)
        })
    }


    private fun setAdapter(list: List<ContractDelayResponse.Result>) {
        if (list.isEmpty())
            rclContractDelay.showNoData()
        else {
            rclContractDelay.view?.adapter = ContractsAdapter(list)
        }
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclContractDelay.showNoData()
                activity?.showError(it?.message)
            }
        })

    }

    companion object {
        val TAG = "$APP_NAME: ${ContractsDelayFragment::class.java.simpleName}"
    }
}