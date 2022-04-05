package ir.nik.contract.view.attachment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.awlrhm.modules.extentions.convertToBitmap
import ir.nik.contract.data.network.model.response.ContractAttachmentListResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_attachment_contracts.view.*

internal class ContractsAdapter(
    private var list: MutableList<ContractAttachmentListResponse.Result>,
    private val callback: OnActionListener
) : RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attachment_contracts, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ContractAttachmentListResponse.Result) {
            itemView.txtFileName.text = model.fileName
            itemView.txtDescription.text = model.description
            itemView.txtRegisterDate.text = model.registerDate
            model.thumbnail?.let {thumbnail ->
                itemView.thumbnail.setImageBitmap(convertToBitmap(thumbnail))
            }
            itemView.layoutDelete.setOnClickListener {
                callback.onDelete(model.daId ?: 0)
            }
            itemView.layoutDownload.setOnClickListener {
                callback.onDownload(model)
            }
        }
    }

    interface OnActionListener{
        fun onDelete(daId: Long)
        fun onDownload(model: ContractAttachmentListResponse.Result)
    }
}