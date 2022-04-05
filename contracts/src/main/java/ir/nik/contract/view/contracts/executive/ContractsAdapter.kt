package ir.nik.contract.view.contracts.executive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contract.data.network.model.response.ContractExecutiveResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_contract_executive.view.*

internal class ContractsAdapter(
    private val list: List<ContractExecutiveResponse.Result>
) : RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contract_executive, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ContractExecutiveResponse.Result) {
            itemView.txtExecutiveType.text = model.cetDescription
            itemView.txtFamily.text = model.cepDescription
            itemView.txtMobile.text = model.cepMobile
            itemView.txtJob.text = model.cepJob
            itemView.txtStartDate.text = model.cepStartDate
            itemView.txtEndDate.text = model.cepEndDate
        }
    }
}