package ir.nik.contract.view.contracts.extend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contract.data.network.model.response.ContractExtendResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_contract_extend.view.*

internal class ContractsAdapter(
    private val list: List<ContractExtendResponse.Result>
) : RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contract_extend, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ContractExtendResponse.Result) {

            val context = itemView.context
            itemView.txtExtendCode.text = model.crCode
            itemView.txtExtendType.text = model.extendTypeTitle
            itemView.txtExtendDate.text = model.crDate
            itemView.txtDurationType.text = model.timeTypeTitle
            itemView.txtChangeDuration.text = model.crTime
            itemView.txtPercent.text = model.crPercent
            itemView.txtExpireDate.text = model.expireDate
            itemView.txtDebitPrice.text = model.debitPrice
            itemView.txtCreditPrice.text = model.creditPrice
            itemView.txtStatus.text = if(model.status == 1) context.getString(R.string.yes) else context.getString(R.string.no)
            itemView.txtDescription.text = model.description
        }
    }
}