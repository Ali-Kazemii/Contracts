package ir.nik.contract.view.contracts.goods.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import ir.awlrhm.modules.extentions.showError
import ir.nik.contract.data.network.model.model.ContractBaseGetRequest
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.utils.ContractsConst
import ir.nik.contract.utils.subSystemJson
import ir.nik.contract.view.base.ContractsBaseFragment
import ir.nik.contract.view.contracts.ContractsViewModel
import ir.nik.contract.view.item.ContractsItemsAdapter
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_goods_subsystem_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContractsGoodsSubSystemFragment(
    private val listener: OnActionListener
): ContractsBaseFragment() {

    private val viewModel by viewModel<ContractsViewModel>()

    override fun setup() {
        rclSubsystem.layoutManager(
            GridLayoutManager(activity, 3)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_goods_subsystem_contracts, container, false)
    }

    override fun onResume() {
        super.onResume()
        if(!rclSubsystem.isOnLoading)
            rclSubsystem.showLoading()

        getSubsystem()
    }

    private fun getSubsystem() {
        viewModel.getSubSystemList(
            ContractBaseGetRequest().also { request ->
                request.userId = viewModel.userId
                request.typeOperation = 4
                request.jsonParameters = subSystemJson(ContractsConst.SubSystems.KEY_GOODS)
            }
        )
    }

    override fun handleObservers() {
        viewModel.subSystemResponse.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                it.result?.let { list ->
                    if (list.size > 0)
                        rclSubsystem.view?.adapter = ContractsItemsAdapter(list) { ssId, name ->
                            listener.onItemClick(ssId, name)
                        }
                    else
                        rclSubsystem.showNoData()

                } ?: kotlin.run {
                    rclSubsystem.showNoData()
                }
            }
        })
    }

    override fun handleOnClickListeners() {
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun handleError() {
        super.handleError()
        viewModel.error.observe(viewLifecycleOwner, {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                rclSubsystem.showNoData()
                activity?.showError(it?.message)
            }
        })
    }

    interface OnActionListener{
        fun onItemClick(ssId: Long, name: String)
    }

    companion object{
        val TAG = "$APP_NAME: ${ContractsGoodsSubSystemFragment::class.java}"
    }

}