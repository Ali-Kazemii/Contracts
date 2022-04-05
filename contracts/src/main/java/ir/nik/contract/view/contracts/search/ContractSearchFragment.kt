package ir.nik.contract.view.contracts.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.awlrhm.modules.enums.MessageStatus
import ir.awlrhm.modules.extentions.hideKeyboard
import ir.awlrhm.modules.extentions.showError
import ir.awlrhm.modules.extentions.showKeyboard
import ir.awlrhm.modules.extentions.yToast
import ir.awlrhm.modules.view.RecyclerView
import ir.nik.contract.data.network.model.response.ContractListRequest
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contract.view.contracts.OnContractItemListener
import ir.nik.contract.view.contracts.fragment.ContractsAdapter
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_search_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractSearchFragment(
    private val listener: OnContractItemListener?
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractViewModel>()
    private lateinit var adapter: ContractsAdapter
    private var pageNumber: Int = 1
    private val pageSize: Int = 5
    private var totalCount: Long = 0
    private var isOnPaging = false


    override fun setup() {
        rclSearch.layoutManager(LinearLayoutManager(activity))
        adapter = ContractsAdapter(listener)
        rclSearch.onActionRecyclerViewListener(
            object : RecyclerView.OnRecyclerViewListener {
                override fun onScrolled(
                    recyclerView: androidx.recyclerview.widget.RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                    val endHasBeenReached = lastVisible + 1 >= totalItemCount
                    if (totalItemCount > 0 && endHasBeenReached && totalCount > adapter.itemCount) {
                        rclSearch.paging = true
                        pageNumber += 1
                        isOnPaging = true
                        search()
                    }
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_contracts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.showKeyboard()
        edtSearch.requestFocus()
    }

    override fun handleObservers() {
        viewModel.contractListResponse.observe(viewLifecycleOwner, { response ->
            response.result?.let { list ->
                if (list.size > 0) {
                    totalCount = response.resultCount ?: 0
                    rclSearch.paging = false
                    if (adapter.itemCount == 0)
                        rclSearch.view?.adapter = adapter
                    adapter.setSource(list)

                } else
                    rclSearch.showNoData()

            } ?: kotlin.run {
                rclSearch.showNoData()
            }
        })
    }

    override fun handleOnClickListeners() {
        val activity = activity ?: return

        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                isOnPaging = false
                search()
                return@setOnEditorActionListener true
            }
            false
        }
        btnBack.setOnClickListener {
            if (edtSearch.text.isEmpty()) {
                activity.hideKeyboard(layoutSearch)
                activity.onBackPressed()
            } else
                onStatus(Status.SEARCH)

        }
        btnSearch.setOnClickListener {
            isOnPaging = false
            search()
        }
    }

    private fun search() {
        val search = edtSearch.text.toString()
        if (search.isNotEmpty()) {
            if (!isOnPaging) {
                adapter.clear()
                onStatus(Status.LOADING)
            }
            viewModel.getListContracts(
                ContractListRequest().also { request ->
                    request.jsonParameters = getContractSearchJson()
                    request.typeOperation = 100
                    request.pageNumber = pageNumber
                    request.pageSize = pageSize
                    request.userId = viewModel.userId
                }
            )
        } else
            activity?.yToast(
                getString(R.string.fill_search_key),
                MessageStatus.ERROR
            )
    }

    private fun getContractSearchJson(): String {
        val searchJson = StringBuilder()
        searchJson.append("{")
        searchJson.append("\\\"ExpressionSearch\\\":\\\"${edtSearch.text}\\\"")
        searchJson.append("}")
        return searchJson.toString()
    }


    private fun onStatus(status: Status) {
        when (status) {
            Status.LOADING -> {
                logoSearch.isVisible = false
                rclSearch.isVisible = true
                rclSearch.showLoading()
            }
            Status.FAILURE -> {
                rclSearch.showNoData()
            }
            Status.SEARCH -> {
                logoSearch.isVisible = true
                rclSearch.isVisible = false
                edtSearch.setText("")
            }
        }
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            onStatus(Status.FAILURE)
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                activity?.showError(it?.message)
            }
        })
    }


    enum class Status {
        SEARCH,
        LOADING,
        FAILURE
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractSearchFragment::class.java.simpleName}"
    }
}