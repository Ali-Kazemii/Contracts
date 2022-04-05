package ir.nik.contract.view.contracts.goods.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nik.contract.data.network.model.response.ContractGoodsResponse
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.item_contract_goods.view.*

internal class ContractsAdapter(
    private val list: List<ContractGoodsResponse.Result>
) : RecyclerView.Adapter<ContractsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contract_goods, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(model: ContractGoodsResponse.Result) {
            itemView.txtGoods.text = model.textMember?.let {
                getItems(model.textMember)
            }?: kotlin.run {
                ""
            }
            /*itemView.txtGoodsCode.text = model.goodsId.toString()
            itemView.txtGoodsName.text = model.goodsName
            itemView.txtGoodsUnit.text = model.guName
            itemView.txtCount.text = model.quantity.toString()
            itemView.txtTotalPrice.text = model.price.toString()
            itemView.txtIssueAmount.text = model.issueAmountTemp
            itemView.txtFactorAmount.text = model.factorAmount
            itemView.txtRemain.text = model.remain
            itemView.txtUnitPrice.text = model.unitPrice*/
        }

        private fun getItems(item: String): String {
            val str = StringBuilder()
            item.split(",").forEach {
                str.append(it)
                str.append("\n")
            }
            return str.toString()
        }
    }
}