package ir.nik.contract.view.contracts.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.awlrhm.modules.extentions.showError
import ir.awlrhm.modules.view.RecyclerView
import ir.nik.contract.data.network.model.response.ContractListRequest
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.OnBackPressed
import ir.nik.contract.utils.contractFilterContractStatusJson
import ir.nik.contract.utils.lastUpdateDate
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contract.view.contracts.OnContractItemListener
import ir.nik.contract.view.contracts.filter.ContractFilterAdapter
import ir.nik.contract.view.contracts.filter.ContractFilterModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_contract_list_contracts.*
import kotlinx.android.synthetic.main.layout_last_update_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractListFragment(
    private val listener: OnContractItemListener?
) : ContractsBaseFragment(), OnBackPressed {


    constructor(
        list: MutableList<ContractFilterModel>,
        listener: OnContractItemListener?
    ) : this(listener) {
        listFilter = list
    }


    private val viewModel by viewModel<ContractViewModel>()
    private lateinit var filterAdapter: ContractFilterAdapter

    private var listFilter: MutableList<ContractFilterModel> = mutableListOf()
    private lateinit var adapter: ContractsAdapter

    private var contractStatusId: Long = ContractStatus.CURRENT.value

    private var pageNumber: Int = 1
    private val pageSize: Int = 5
    private var totalCount: Long = 0

    override fun setup() {

        if (viewModel.isOfflineMode) {
            layoutUpdate.isVisible = true
            btnFilter.isVisible = false

            btnUpdate.setOnClickListener {
                logout()
            }
        }

        rclContract.layoutManager(LinearLayoutManager(activity))
        rclFilter.layoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        if (listFilter.isNotEmpty()) {
            rclFilter.isVisible = true
            filterAdapter =
                ContractFilterAdapter(
                    listFilter,
                    object :
                        ContractFilterAdapter.OnActionListener {
                        @SuppressLint("NotifyDataSetChanged")
                        override fun onDeleteFilter(list: MutableList<ContractFilterModel>) {
                            rclContract.showLoading()
                            filterAdapter.notifyDataSetChanged()
                            listFilter = list
                            if (list.size == 0)
                                rclFilter.isVisible = false
                            adapter.clear()
                            getListContracts()
                        }
                    })
            rclFilter.view?.adapter = filterAdapter
        }

        initialPaging()

        initialContractStatusValue()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_list_contracts, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (!rclContract.isOnLoading)
            rclContract.showLoading()
        getListContracts()
    }


    private fun initialContractStatusValue() {
        txtCurrentStatus.tag = ContractStatus.CURRENT.value
        txtInExchangeStatus.tag = ContractStatus.IN_EXCHANGE.value
        txtTerminatedStatus.tag = ContractStatus.TERMINATED.value
        txtNeedSpecifyStatus.tag = ContractStatus.NEED_SPECIFY.value
    }

    private fun initialPaging() {
        adapter = ContractsAdapter(listener)
        rclContract.onActionRecyclerViewListener(
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
                        rclContract.paging = true
                        pageNumber += 1
                        getListContracts()
                    }
                }
            }
        )
    }


    override fun handleOnClickListeners() {
        val activity = activity ?: return

        btnBack.setOnClickListener {
            activity.finish()
        }
        btnFilter.setOnClickListener {
            listener?.onFilter(listFilter)
        }
        layoutSearch.setOnClickListener {
            viewModel.checkConnection(activity) {
                listener?.onSearch()
            }
        }
        txtCurrentStatus.setOnClickListener {
            statusSelected(it.tag as Long)
        }
        txtInExchangeStatus.setOnClickListener {
            statusSelected(it.tag as Long)
        }
        txtNeedSpecifyStatus.setOnClickListener {
            statusSelected(it.tag as Long)
        }
        txtTerminatedStatus.setOnClickListener {
            statusSelected(it.tag as Long)
        }
    }


    override fun handleObservers() {
        viewModel.contractListResponse.observe(viewLifecycleOwner, { response ->
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                response.result?.let { list ->
                    if (list.isNotEmpty()) {
                        totalCount = response.resultCount ?: 0
                        rclContract.paging = false
                        if (adapter.itemCount == 0)
                            rclContract.view?.adapter = adapter
                        adapter.setSource(list)

                    } else
                        rclContract.showNoData()

                } ?: kotlin.run {
                    rclContract.showNoData()
                }
            }
        })
    }


    private fun getListContracts() {
        val activity = activity ?: return

        if (viewModel.isOfflineMode) {
            viewModel.getContractDb().observe(viewLifecycleOwner, {
                try {
                    txtLastUpdateDate.text = activity.lastUpdateDate(it.xUpdateDate)
                    convertJsonToModel(it.xJson)

                } catch (e: Exception) {
                    rclContract.showNoData()
                    txtLastUpdateDate.text = getString(R.string.no_date)
                }
            })
        } else {
            val jsonParams = if (listFilter.isNotEmpty()) getContractJson()
            else contractFilterContractStatusJson(
                contractStatusId = contractStatusId
            )
                viewModel.getListContracts(
                    ContractListRequest().also { request ->
                        request.typeOperation = 100
                        request.pageNumber = pageNumber
                        request.pageSize = pageSize
                        request.userId = viewModel.userId
                        request.jsonParameters = jsonParams

                    }
                )
        }
    }

    private fun statusSelected(contractStatusId: Long) {
        this.contractStatusId = contractStatusId

        when (contractStatusId) {
            ContractStatus.CURRENT.value -> {
                rdbInExchange.isChecked = false
                rdbTerminated.isChecked = false
                rdbNeedSpecify.isChecked = false
                rdbCurrent.isChecked = true
            }
            ContractStatus.IN_EXCHANGE.value -> {
                rdbCurrent.isChecked = false
                rdbTerminated.isChecked = false
                rdbNeedSpecify.isChecked = false
                rdbInExchange.isChecked = true
            }
            ContractStatus.NEED_SPECIFY.value -> {
                rdbCurrent.isChecked = false
                rdbInExchange.isChecked = false
                rdbTerminated.isChecked = false
                rdbNeedSpecify.isChecked = true
            }
            ContractStatus.TERMINATED.value -> {
                rdbCurrent.isChecked = false
                rdbInExchange.isChecked = false
                rdbNeedSpecify.isChecked = false
                rdbTerminated.isChecked = true
            }
        }
        refresh()
    }

    private fun refresh() {
        adapter.clear()
        rclContract.showLoading()
        getListContracts()
    }

    private fun convertJsonToModel(json: String?) {
        viewModel.getContract(json).observe(viewLifecycleOwner, { list ->
            if (list.size > 0) {
                rclContract.view?.adapter = adapter
                adapter.setSource(list)
            } else
                rclContract.showNoData()
        })
    }

    private fun getContractJson(): String {
        if (listFilter.isEmpty())
            return "{}"

        val projectJson = StringBuilder()

        projectJson.append("{")
        listFilter.forEach {
            projectJson.append(it.jsonId)
            projectJson.append(",")
        }
        projectJson.append("\\\"CNT_CsIDs_fk\\\":\\\"${contractStatusId}\\\"")
        projectJson.append("}")
        return projectJson.toString()
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(this, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclContract.showNoData()
                activity?.showError(it?.message)
            }
        })
    }

    override fun onBackPressed() {
        activity?.finish()
    }

    internal enum class ContractStatus(val value: Long) {
        IN_EXCHANGE(value = 9810302), //درحال مبادله
        CURRENT(value = 9810303), //جاری
        TERMINATED(value = 9810306), //خاتمه یافته
        NEED_SPECIFY(value = 9810309) //نیازمند تعیین تکلیف
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractListFragment::class.java.simpleName}"
    }
}