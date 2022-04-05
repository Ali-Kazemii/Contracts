package ir.nik.contract.view.contracts.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import ir.awlrhm.modules.extentions.failedData
import ir.awlrhm.modules.extentions.showError
import ir.awlrhm.modules.models.ItemModel
import ir.awlrhm.modules.view.Spinner
import ir.nik.contract.data.network.model.request.ContractBaseListRequest
import ir.nik.contract.data.network.model.request.ContractCustomerRequest
import ir.nik.contract.data.network.model.request.ContractPlaceRequest
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractViewModel
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.contain_contract_filter_contracts.*
import kotlinx.android.synthetic.main.fragment_contract_filter_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractFilterFragment(
    private val listFilter: MutableList<ContractFilterModel>,
    private val listener: OnActionListener
) : ContractsBaseFragment() {

    private val viewModel by viewModel<ContractViewModel>()

    private val listProjectCustomer: MutableList<ItemModel> = mutableListOf()
    private val listProjectPlace: MutableList<ItemModel> = mutableListOf()
    private val listProjectContract: MutableList<ItemModel> = mutableListOf()

    private var contractCustomerId: Long = -1
    private var contractPlaceId: Long = -1
    private var contractStatusId: Long = -1

    override fun setup() {
        if(listFilter.isNotEmpty()){
            listFilter.forEach {model ->
                when(model.type){
                    ContractFilterType.CONTRACT_CUSTOMER ->{
                        contractCustomerId = model.id
                        spProjectCustomer.text = model.value
                    }
                    ContractFilterType.CONTRACT_PLACE ->{
                        contractPlaceId = model.id
                        spProjectPlace.text = model.value
                    }
                    ContractFilterType.CONTRACT_STATUS ->{
                        contractStatusId = model.id
                        spContractStatus.text = model.value
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contract_filter_contracts, container, false)
    }

    override fun handleObservers() {
        viewModel.placeResponse.observe(viewLifecycleOwner, {
            it.result?.let {
                listProjectPlace.apply {
                    it.forEach { model ->
                        add(
                            ItemModel(model.placeId ?: 0, model.placeName ?: "")
                        )
                    }
                }
                showProjectPlace()

            } ?: kotlin.run {
                spProjectPlace.failedData()
            }
        })
        viewModel.projectCustomerResponse.observe(viewLifecycleOwner, {
            it.result?.let {
                listProjectCustomer.apply {
                    it.forEach { model ->
                        add(
                            ItemModel(model.customerId ?: 0, model.customerTitle ?: "")
                        )
                    }
                }
                showProjectCustomer()

            } ?: kotlin.run {
                spProjectCustomer.failedData()
            }
        })
        viewModel.contractStatusResponse.observe(viewLifecycleOwner, {
            it.result?.let {
                listProjectContract.apply {
                    it.forEach { model ->
                        add(
                            ItemModel(model.cbId ?: 0, model.cbName ?: "")
                        )
                    }
                }
                showProjectContract()

            } ?: kotlin.run {
                spContractStatus.failedData()
            }
        })

    }

    override fun handleOnClickListeners() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        spProjectPlace.setOnClickListener {
            getProjectPlace()
        }
        spProjectCustomer.setOnClickListener {
            getProjectCustomer()
        }
        spContractStatus.setOnClickListener {
            getProjectContract()
        }
        btnDone.setOnClickListener {
            listener.onFilterChoose(
                mutableListOf<ContractFilterModel>().apply {
                    if (contractPlaceId != -1L)
                        add(
                            ContractFilterModel(
                                ContractFilterType.CONTRACT_PLACE,
                                "\\\"TBL_PlaceIDs_fk\\\":${contractPlaceId}",
                                "${getString(R.string.electric_company)}: ${spProjectPlace.text}",
                                contractPlaceId,
                                spProjectPlace.text.toString()
                            )
                        )
                    if (contractCustomerId != -1L)
                        add(
                            ContractFilterModel(
                                ContractFilterType.CONTRACT_CUSTOMER,
                                "\\\"TBL_CustomerIDs_fk\\\":${contractCustomerId}",
                                "${getString(R.string.company_contract)}: ${spProjectCustomer.text}",
                                contractCustomerId,
                                spProjectCustomer.text.toString()
                            )
                        )
                    if (contractStatusId != -1L)
                        add(
                            ContractFilterModel(
                                ContractFilterType.CONTRACT_STATUS,
                                "\\\"CNT_CsIDs_fk\\\":${contractStatusId}",
                                "${getString(R.string.contract_status)}: ${spContractStatus.text}",
                                contractStatusId,
                                spContractStatus.text.toString()
                            )
                        )
                }
            )
        }
    }

    private fun getProjectContract() {
        if (listProjectContract.isNotEmpty())
            showProjectContract()
        else {
            spContractStatus.loading(true)
            viewModel.getContractBaseList(
                ContractBaseListRequest().also { request ->
                    request.cbId = 0
                    request.typeOperation = 15
                }
            )
        }
    }

    private fun showProjectContract() {
        val activity = activity ?: return
        spContractStatus.showData(listProjectContract, activity) {
            contractStatusId = it.id
        }
    }

    private fun getProjectCustomer() {
        if (listProjectCustomer.isNotEmpty())
            showProjectCustomer()
        else {
            spProjectCustomer.loading(true)
            viewModel.getCustomer(
                ContractCustomerRequest().also { request ->
                    request.customerId = 0
                    request.typeOperation = 15
                }
            )
        }
    }

    private fun showProjectCustomer() {
        val activity = activity ?: return
        spProjectCustomer.showData(listProjectCustomer, activity) {
            contractCustomerId = it.id
        }
    }

    private fun getProjectPlace() {
        if (listProjectPlace.isNotEmpty())
            showProjectPlace()
        else {
            spProjectPlace.loading(true)
            viewModel.getPlace(
                ContractPlaceRequest().also { request ->
                    request.placeId = 0
                    request.userId = viewModel.userId
                    request.typeOperation = 15
                }
            )
        }
    }

    private fun showProjectPlace() {
        val activity = activity ?: return
        spProjectPlace.showData(listProjectPlace, activity) {
            contractPlaceId = it.id
        }
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                for (i in 0..layoutParent.childCount) {
                    val view = layoutParent.getChildAt(i)
                    if (view is Spinner)
                        view.loading(false)
                }
                activity?.showError(it?.message)
            }
        })
    }


    interface OnActionListener {
        fun onFilterChoose(list: MutableList<ContractFilterModel>)
    }

    companion object {
        val TAG = "$APP_NAME: ${ContractFilterFragment::class.java.simpleName}"
    }
}