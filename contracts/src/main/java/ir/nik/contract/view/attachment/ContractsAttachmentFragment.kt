package ir.nik.contract.view.attachment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.awlrhm.modules.enums.MessageStatus
import ir.awlrhm.modules.extentions.*
import ir.awlrhm.modules.view.ActionDialog
import ir.nik.contract.data.network.model.request.ContractDeleteFileRequest
import ir.nik.contract.data.network.model.request.ContractDocumentAttachmentRequest
import ir.nik.contract.data.network.model.response.ContractAttachmentListResponse
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.PATH_STORAGE
import ir.nik.contract.utils.attachmentJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_attachment_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractsAttachmentFragment(
    private val dcId: Long,
    private val relatedTableId: Long,
    private val listener: OnActionListener
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractsAttachmentViewModel>()
    private val pageNumber: Int = 1
    private var isOnDownload: Boolean = false

    override fun setup() {
        if (viewModel.isOfflineMode) {
            btnAdd.isVisible = false
            layoutUpdate.isVisible = true

            btnUpdate.setOnClickListener {
                logout()
            }
        }

        rclAttachment.layoutManager(LinearLayoutManager(activity))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attachment_contracts, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (!rclAttachment.isOnLoading)
            rclAttachment.showLoading()
        getAttachments()
    }

    override fun handleOnClickListeners() {
        btnBack.setOnClickListener { activity?.onBackPressed() }
        btnAdd.setOnClickListener { listener.onAddClick() }
    }

    override fun handleObservers() {
        val activity = activity ?: return
        viewModel.listAttachmentResponse.observe(viewLifecycleOwner, {
            if (rclAttachment.actionLoading)
                rclAttachment.actionLoading = false
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                it.result?.let { list ->
                    if (isOnDownload) {
                        isOnDownload = false
                        activity.checkWriteStoragePermission {
                            saveAttachment(list[0])
                        }
                    } else
                        setAdapter(list)

                } ?: kotlin.run {
                    if (isOnDownload) {
                        activity.showError(
                            it.message ?: getString(R.string.error_download_file_call_support)
                        )
                    } else
                        rclAttachment.showNoData()
                }
            }
        })

        viewModel.responseId.observe(viewLifecycleOwner, {
            rclAttachment.actionLoading = false
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                it.result?.let { result ->
                    if (result != 0L) {
                        activity.successOperation(it.message)
                        rclAttachment.showLoading()
                        getAttachments()

                    } else
                        activity.showError(it.message)
                } ?: kotlin.run {
                    activity.showError(it.message)
                }
            }
        })
    }


    private fun getAttachments() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getAttachments(relatedTableId, dcId).observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclAttachment.showNoData()
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
            viewModel.getListAttachment(
                relatedTableId,
                dcId,
                ContractDocumentAttachmentRequest().also { request ->
                    request.jsonParameters = attachmentJson(relatedTableId, dcId)
                    request.pageNumber = pageNumber
                    request.userId = viewModel.userId
                    request.typeOperation = 101
                }
            )
    }


    private fun convertJsonToModel(json: String?) {
        viewModel.getAttachments(json).observe(viewLifecycleOwner, { list ->
            setAdapter(list)
        })
    }

    private fun setAdapter(list: MutableList<ContractAttachmentListResponse.Result>) {
        val activity = activity ?: return

        if (list.isEmpty()) {
            rclAttachment.showNoData()

        } else {
            rclAttachment.view?.adapter = ContractsAdapter(
                list,
                object : ContractsAdapter.OnActionListener {
                    override fun onDelete(daId: Long) {
                        viewModel.checkConnection(activity) {
                            ActionDialog.Builder()
                                .setTitle(getString(R.string.warning))
                                .setDescription(getString(R.string.are_you_sure_delete))
                                .setPositive(getString(R.string.yes)) {
                                    rclAttachment.actionLoading = true
                                    viewModel.deleteFile(
                                        ContractDeleteFileRequest().also { request ->
                                            request.daId = daId
                                        }
                                    )
                                }
                                .setNegative(getString(R.string.no)) {}
                                .build()
                                .show(activity.supportFragmentManager, ActionDialog.TAG)
                        }
                    }

                    override fun onDownload(model: ContractAttachmentListResponse.Result) {
                        isOnDownload = true
                        rclAttachment.actionLoading = true
                        //download attachment service
                        viewModel.getListAttachment(
                            relatedTableId,
                            dcId,
                            ContractDocumentAttachmentRequest().also { request ->
                                request.daId = model.daId ?: 0
                                request.userId = viewModel.userId
                                request.typeOperation = 501
                            }
                        )
                    }
                })
        }
    }


    private fun saveAttachment(result: ContractAttachmentListResponse.Result) {
        val activity = activity ?: return
        if (
            result.documentBody != null
            && result.extensionType != null
            && result.documentName != null
        ) {
            convertBase64ToFile(
                result.documentBody,
                PATH_STORAGE,
                result.documentName,
                result.extensionType
            ) {
                if (it)
                    activity.ySnake(
                        getString(R.string.path_saved_file),
                        getString(R.string.understand)
                    )
                else
                    activity.showFlashbar(
                        "",
                        getString(R.string.error_save_file_call_support),
                        MessageStatus.ERROR
                    )
            }
        } else
            activity.showFlashbar(
                getString(R.string.error_save_file),
                getString(R.string.incomplete_file_call_support),
                MessageStatus.ERROR
            )
    }


    override fun handleError() {
        super.handleError()
        val activity = activity ?: return

        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclAttachment.actionLoading = false
                rclAttachment.showNoData()
                activity.showError(it?.message)
            }
        })
        viewModel.downloadError.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclAttachment.actionLoading = false
                rclAttachment.showNoData()
                activity.showError(it?.message)
            }
        })
        viewModel.errorDelete.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclAttachment.actionLoading = false
                activity.showError(it?.message)
            }
        })
    }

    interface OnActionListener {
        fun onAddClick()
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractsAttachmentFragment::class.java.simpleName}"
    }
}