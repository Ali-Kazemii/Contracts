package ir.nik.contract.view.contracts.accounting

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import ir.awlrhm.modules.extentions.showError
import ir.nik.contract.data.network.model.request.ContractAccountingDocumentRequest
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.accountingDocumentJson
import ir.nik.contract.view.base.ContractsBaseReportFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.contain_report_contracts.*
import kotlinx.android.synthetic.main.fragment_accounting_document_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractsAccountingDocumentFragment(
    private val contractId: Long
) : ContractsBaseReportFragment(), OnPageErrorListener {

    private val viewModel by viewModel<ContractViewModel>()
    private var pdfStream: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accounting_document_contracts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAccountingDocumentReport(
            ContractAccountingDocumentRequest().also { request ->
                request.addId = 0
                request.jsonParameters = accountingDocumentJson(contractId)
                request.userId = viewModel.userId
                request.typeOperation = 302
            }
        )
    }

    override fun handleOnClickListeners() {
        val activity = activity ?: return
        btnBack.setOnClickListener {
            activity.onBackPressed()
        }
        btnSave.setOnClickListener {
            onSave(pdfStream)
        }
    }

    override fun handleObservers() {
        viewModel.reportResponse.observe(viewLifecycleOwner, {
            it.result?.let { pdf ->
                if (pdf.isEmpty())
                    showNoData()
                else {
                    pdfStream = pdf
                    loadPdf()
                }

            } ?: kotlin.run {
                showNoData()
            }
        })
    }

    private fun loadPdf() {
        showPdfLayout()
        pdfView.fromBytes(Base64.decode(pdfStream, Base64.DEFAULT))
            .enableAnnotationRendering(true)
            .spacing(10) // in dp
            .onPageError(this)
            .enableAnnotationRendering(false)
            .enableAntialiasing(true)
            .load()
    }


    private fun showPdfLayout() {
        pdfView.isVisible = true
        prcReport.isVisible = false
        layoutNoData.isVisible = false
    }

    private fun showNoData() {
        layoutNoData.isVisible = true
        prcReport.isVisible = false
        pdfView.isVisible = false
    }

    override fun onPageError(page: Int, t: Throwable?) {
        showNoData()
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                showNoData()
                activity?.showError(it?.message)
            }
        })
    }


    companion object{
        val TAG = "$APP_NAME: ${ContractsAccountingDocumentFragment::class.java.simpleName}"
    }
}