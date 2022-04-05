package ir.nik.contract.view.item

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ir.awlrhm.modules.extentions.convertToBitmap
import ir.nik.contract.data.network.model.response.ContractSubSystemResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.items_contracts.view.*

internal class ContractsItemsAdapter(
    private val list: MutableList<ContractSubSystemResponse.Result>,
    private val callback:(Long, String) -> Unit
): RecyclerView.Adapter<ContractsItemsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_contracts, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(model: ContractSubSystemResponse.Result){

            itemView.txtNameItems.text = model.name
            itemView.imgItems.setImageBitmap(convertToBitmap(model.body ?: ""))
            itemView.imgItems.setColorFilter(ContextCompat.getColor(itemView.context,
                R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            itemView.setOnClickListener {
                callback.invoke(model.ssId ?: 0, model.name ?: "")
            }
        }
    }
}