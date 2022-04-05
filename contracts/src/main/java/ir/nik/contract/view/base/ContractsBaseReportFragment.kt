package ir.nik.contract.view.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.awlrhm.modules.enums.MessageStatus
import ir.awlrhm.modules.extentions.*
import ir.nik.contract.utils.PATH_STORAGE
import ir.nik.contract.view.dialog.ContractsRegisterDataDialog
import ir.nik.contracts.R

internal abstract class ContractsBaseReportFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        handleOnClickListeners()
        handleObservers()
        handleError()
    }

    fun onSave(pdfStream: String?) {
        activity?.checkWriteStoragePermission {
            showFileNameDialog(pdfStream)
        }
    }

    private fun showFileNameDialog(pdfStream: String?) {
        val activity = activity ?: return

        pdfStream?.let {
            ContractsRegisterDataDialog(
                getString(R.string.enter_report_name),
                ""
            ) { fileName ->

                convertToPdf(it, PATH_STORAGE, fileName) {
                    activity.hideKeyboard(this.requireView())
                    if (it)
                        activity.ySnake(
                            getString(R.string.path_saved_file),
                            getString(R.string.understand)
                        )
                    else
                        activity.yToast(getString(R.string.error_save_report), MessageStatus.ERROR)
                }
            }.show(activity.supportFragmentManager, ContractsRegisterDataDialog.TAG)
        } ?: kotlin.run {
            activity.yToast(getString(R.string.not_pdf_exist), MessageStatus.ERROR)
        }
    }

    open fun setup() {}
    open fun handleOnClickListeners() {}
    open fun handleObservers() {}
    open fun handleError() {}

}