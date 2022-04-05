package ir.nik.contract.view.contracts.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_chip_filter_contracts.view.*

internal class ContractFilterAdapter(
    private val list: MutableList<ContractFilterModel>,
    private val listener: OnActionListener
) : RecyclerView.Adapter<ContractFilterAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chip_filter_contracts, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(model: ContractFilterModel) {
            itemView.txtChip.text = model.name
            itemView.chip.setOnClickListener {
                list.removeAt(list.indexOf(model))
                listener.onDeleteFilter(list)
            }
        }
    }

    interface OnActionListener {
        fun onDeleteFilter(list: MutableList<ContractFilterModel>)
    }
}

