package ir.nik.contract.view.contracts.attachment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contract.data.network.model.response.ContractAttachmentResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_contract_attachment.view.*

internal class ContractsAdapter(
    private val list: List<ContractAttachmentResponse.Result>,
    private val listener: OnActionListener
) : RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contract_attachment, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ContractAttachmentResponse.Result) {
            itemView.txtDocumentName.text = model.cbName
            itemView.txtDocumentDate.text = model.caDate
            itemView.txtNote.text = model.caDescription
            itemView.txtDescription.text = model.caNote

            itemView.btnAttachment.setOnClickListener {
                listener.onItemClick(model.cbId ?: 0, model.caId ?: 0)
            }
        }
    }

    interface OnActionListener{
        fun onItemClick(cbId: Long, caId: Long)
    }

}