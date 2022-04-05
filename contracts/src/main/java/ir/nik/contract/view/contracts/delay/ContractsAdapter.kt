package ir.nik.contract.view.contracts.delay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contract.data.network.model.response.ContractDelayResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_contract_delay.view.*

internal class ContractsAdapter(
    private val list: List<ContractDelayResponse.Result>
) : RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contract_delay, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ContractDelayResponse.Result) {
            itemView.txtDelayDescription.text = model.description
            itemView.txtDelayDuration.text = model.time
            itemView.txtLetterNo.text = model.letterNo
            itemView.txtLetterDate.text = model.letterDate
            itemView.txtStartDate.text = model.startDate
            itemView.txtEndDate.text = model.endDate
        }
    }
}