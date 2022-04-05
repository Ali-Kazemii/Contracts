package ir.nik.contract.view.contracts.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contract.data.network.model.response.ContractResponse
import ir.nik.contract.view.contracts.OnContractItemListener
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_contract.view.*

internal class ContractsAdapter(
    private val listener: OnContractItemListener?
): RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    private val list: MutableList<ContractResponse.Result> = mutableListOf()

    fun setSource(items: MutableList<ContractResponse.Result>){
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear(){
        list.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contract, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(model: ContractResponse.Result){
            itemView.txtContractTitle.text = model.contractTitle
            itemView.txtContractCode.text = model.contractCode
            itemView.txtContractName.text = model.ctName
            itemView.txtCustomerTitle.text = model.customerTitle
            itemView.txtApproveDate.text = model.contractApproveDate
            itemView.txtStartDate.text = model.contractStartDate
            itemView.txtExpireDate.text = model.contractExpireDate
            itemView.txtTimeTitle.text = model.contractTimeTitle
            itemView.txtPhysicalPercentPlan.text = model.contractPhysicalPercentPlan.toString()
            itemView.txtPhysicalPercentProgress.text = model.contractPhysicalPercentProgress.toString()
            itemView.txtPercentPriceProgress.text = model.contractPercentPriceProgress.toString()
            itemView.txtTotalPrice.text = model.contractTotalPriceCurrentProject.toString()
            
            itemView.layoutAttachment.setOnClickListener {
                listener?.onAttachment(model)
            }
            itemView.layoutExecutive.setOnClickListener {
                listener?.onExecutive(model)
            }
            itemView.layoutExtend.setOnClickListener { 
                listener?.onExtend(model)
            }
            itemView.layoutGoods.setOnClickListener { 
                listener?.onGoods(model)
            }
            itemView.layoutAccounting.setOnClickListener {
                listener?.onAccounting(model)
            }
            itemView.layoutPeymanCard.setOnClickListener { 
                listener?.onPeymanCard(model)
            }
            itemView.layoutStatus.setOnClickListener { 
                listener?.onStatus(model)
            }
            itemView.layoutDelay.setOnClickListener { 
                listener?.onDelay(model)
            }
        }
    }


}