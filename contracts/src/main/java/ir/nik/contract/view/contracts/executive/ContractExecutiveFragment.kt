package ir.nik.contract.view.contracts.executive

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
import ir.nik.contract.data.network.model.request.ContractExecutiveRequest
import ir.nik.contract.data.network.model.response.ContractExecutiveResponse
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.contractExecutiveJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_contract_executive_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractExecutiveFragment(
    private val contractId: Long
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractViewModel>()
    private var pageNumber = 1

    override fun setup() {
        if (viewModel.isOfflineMode) {
            layoutUpdate.isVisible = true

            btnUpdate.setOnClickListener {
                logout()
            }
        }

        rclContractExecutive.layoutManager(
            LinearLayoutManager(activity)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_executive_contracts, container, false)
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
        viewModel.contractExecutiveResponse.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                it.result?.let { list ->
                    setAdapter(list)


                } ?: kotlin.run {
                    rclContractExecutive.showNoData()
                }
            }
        })
    }


    private fun getItems() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getContractExecutive(contractId).observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclContractExecutive.showNoData()
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
        viewModel.getContractExecutive(
            contractId,
            ContractExecutiveRequest().also { request ->
                request.cepId = 0
                request.jsonParameters = contractExecutiveJson(contractId)
                request.pageNumber = pageNumber
                request.userId = viewModel.userId
                request.typeOperation = 101
            }
        )
    }



    private fun convertJsonToModel(json: String?) {
        viewModel.getContractExecutive(json).observe(viewLifecycleOwner, { list ->
            setAdapter(list)
        })
    }


    private fun setAdapter(list: MutableList<ContractExecutiveResponse.Result>) {
        if (list.isEmpty())
            rclContractExecutive.showNoData()
        else
            rclContractExecutive.view?.adapter = ContractsAdapter(list)
    }


    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            rclContractExecutive.showNoData()
            activity?.showError(it?.message)
        })
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractExecutiveFragment::class.java.simpleName}"
    }
}