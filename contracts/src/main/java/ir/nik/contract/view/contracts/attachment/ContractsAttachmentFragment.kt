package ir.nik.contract.view.contracts.attachment

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
import ir.nik.contract.data.network.model.request.ContractAttachmentRequest
import ir.nik.contract.data.network.model.response.ContractAttachmentResponse
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.ContractsConst
import ir.nik.contract.utils.contractAttachmentJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractsViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_contract_attachment_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractsAttachmentFragment(
    private val contractId: Long,
    private val listener: OnActionListener
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractsViewModel>()
    private var pageNumber = 1

    override fun setup() {
        if (viewModel.isOfflineMode) {
            layoutUpdate.isVisible = true
            btnUpdate.setOnClickListener {
                logout()
            }
        }
        rclContractAttachment.layoutManager(
            LinearLayoutManager(activity)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_attachment_contracts, container, false)
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
        viewModel.contractAttachmentResponse.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                it.result?.let { list ->
                  setAdapter(list)

                } ?: kotlin.run {
                    rclContractAttachment.showNoData()
                }
            }
        })
    }


    private fun getItems() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getContractAttachment(contractId).observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclContractAttachment.showNoData()
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
            viewModel.getContractAttachment(
                contractId,
                ContractAttachmentRequest().also { request ->
                    request.caId = 0
                    request.pageNumber = pageNumber
                    request.jsonParameters = contractAttachmentJson(contractId)
                    request.userId = viewModel.userId
                    request.typeOperation = 101
                }
            )
    }

    private fun convertJsonToModel(json: String?) {
        viewModel.getContractAttachment(json).observe(viewLifecycleOwner, { list ->
            setAdapter(list)
        })
    }

    private fun setAdapter(list: MutableList<ContractAttachmentResponse.Result>) {
        if (list.isEmpty())
            rclContractAttachment.showNoData()
        else {
            rclContractAttachment.view?.adapter = ContractsAdapter(
                list,
                object : ContractsAdapter.OnActionListener {
                    override fun onItemClick(cbId: Long, caId: Long) {
                        listener.onItemClick(getAttachmentId(cbId), caId)
                    }
                }
            )
        }
    }


    private fun getAttachmentId(cbId: Long): Long {
        return when (cbId) {
            ContractsConst.ContractAttachment.ADDITION -> ContractsConst.ContractAttachment.ADDITION_DC_ID
            ContractsConst.ContractAttachment.ATTACHMENTS -> ContractsConst.ContractAttachment.ATTACHMENTS_DC_ID
            ContractsConst.ContractAttachment.CONTRACT_TEXT -> ContractsConst.ContractAttachment.CONTRACT_TEXT_DC_ID
            ContractsConst.ContractAttachment.TEMP_DELIVERY -> ContractsConst.ContractAttachment.TEMP_DELIVERY_DC_ID
            ContractsConst.ContractAttachment.PERMANENT_DELIVERY -> ContractsConst.ContractAttachment.PERMANENT_DELIVERY_DC_ID
            ContractsConst.ContractAttachment.LAND_DELIVERY -> ContractsConst.ContractAttachment.LAND_DELIVERY_DC_ID
            ContractsConst.ContractAttachment.WORKSHOP_DELIVERY -> ContractsConst.ContractAttachment.WORKSHOP_DELIVERY_DC_ID
            else -> 0
        }
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclContractAttachment.showNoData()
                activity?.showError(it?.message)
            }
        })
    }

    interface OnActionListener {
        fun onItemClick(dcId: Long, caId: Long)
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractsAttachmentFragment::class.java.simpleName}"
    }
}