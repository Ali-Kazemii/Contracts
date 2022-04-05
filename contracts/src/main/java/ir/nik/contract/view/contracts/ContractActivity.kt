package ir.nik.contract.view.contracts

import android.content.Intent
import android.os.Bundle
import ir.awlrhm.modules.extentions.addFragmentInActivity
import ir.awlrhm.modules.extentions.replaceFragmentInActivity
import ir.nik.contract.data.network.model.response.ContractResponse
import ir.nik.contract.utils.ContractsConst
import ir.nik.contract.utils.OnBackPressed
import ir.nik.contract.view.attachment.ContractsAttachmentActivity
import ir.nik.contract.view.base.ContractsBaseActivity
import ir.nik.contract.view.contracts.accounting.ContractsAccountingDocumentFragment
import ir.nik.contract.view.contracts.attachment.ContractAttachmentFragment
import ir.nik.contract.view.contracts.delay.ContractDelayFragment
import ir.nik.contract.view.contracts.executive.ContractExecutiveFragment
import ir.nik.contract.view.contracts.extend.ContractExtendFragment
import ir.nik.contract.view.contracts.filter.ContractFilterFragment
import ir.nik.contract.view.contracts.filter.ContractFilterModel
import ir.nik.contract.view.contracts.fragment.ContractListFragment
import ir.nik.contract.view.contracts.goods.item.ContractsGoodsSubSystemFragment
import ir.nik.contract.view.contracts.goods.list.ContractGoodsListFragment
import ir.nik.contract.view.contracts.peyman.ContractsPeymanCardFragment
import ir.nik.contract.view.contracts.search.ContractSearchFragment
import ir.nik.contract.view.contracts.status.ContractStatusFragment
import ir.nik.contract.view.noconnection.ContractsNoConnectionFragment
import ir.nik.contracts.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContractActivity : ContractsBaseActivity() {

    private val viewModel by viewModel<ContractViewModel>()
    private var listener: OnContractItemListener? = null


    override fun setup() {
        listener = object : OnContractItemListener {
            override fun onSearch() {
                gotoSearch()
            }

            override fun onFilter(listFilter: MutableList<ContractFilterModel>) {
                gotoContractFilter(listFilter)
            }

            override fun onAttachment(model: ContractResponse.Result) {
                gotoContractAttachment(model.contractId ?: 0)
            }

            override fun onExecutive(model: ContractResponse.Result) {
                gotoExecutive(model.contractId ?: 0)
            }

            override fun onExtend(model: ContractResponse.Result) {
                gotoExtendFragment(model.contractId ?: 0)
            }

            override fun onGoods(model: ContractResponse.Result) {
                gotoContractGoods(model.contractId ?: 0)
            }

            override fun onAccounting(model: ContractResponse.Result) {
                viewModel.checkConnection(this@ContractActivity) {
                    gotoAccountingDocument(model.contractId ?: 0)
                }
            }

            override fun onPeymanCard(model: ContractResponse.Result) {
                viewModel.checkConnection(this@ContractActivity) {
                    gotoPeymanCard(model.contractId ?: 0)
                }
            }

            override fun onStatus(model: ContractResponse.Result) {
                viewModel.checkConnection(this@ContractActivity) {
                    gotoContractStatus(model.contractId ?: 0)
                }
            }

            override fun onDelay(model: ContractResponse.Result) {
                gotoDelayFragment(model.contractId ?: 0)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract_contracts)

      gotoContract()
    }

    private fun gotoPeymanCard(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractsPeymanCardFragment(contractId),
            ContractsPeymanCardFragment.TAG
        )
    }

    private fun gotoAccountingDocument(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractsAccountingDocumentFragment(contractId),
            ContractsAccountingDocumentFragment.TAG
        )
    }

    private fun gotoContractStatus(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractStatusFragment(contractId),
            ContractStatusFragment.TAG
        )
    }

    private fun gotoExtendFragment(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractExtendFragment(contractId),
            ContractExtendFragment.TAG
        )
    }

    private fun gotoDelayFragment(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractDelayFragment(contractId),
            ContractDelayFragment.TAG
        )
    }


    private fun gotoContractGoods(
        contractId: Long
    ) {
        replaceFragmentInActivity(
            R.id.container,
            ContractsGoodsSubSystemFragment(
                object : ContractsGoodsSubSystemFragment.OnActionListener {
                    override fun onItemClick(ssId: Long, name: String) {
                        gotoContractGoodsList(contractId, ssId, name)
                    }
                }
            ),
            ContractsGoodsSubSystemFragment.TAG
        )
    }

    private fun gotoContractGoodsList(
        contractId: Long,
        ssId: Long,
        name: String
    ) {
        replaceFragmentInActivity(
            R.id.container,
            ContractGoodsListFragment(
                contractId = contractId,
                ssId = ssId,
                name = name
            ),
            ContractGoodsListFragment.TAG
        )
    }

    private fun gotoExecutive(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractExecutiveFragment(contractId),
            ContractExecutiveFragment.TAG
        )
    }

    private fun gotoContractAttachment(contractId: Long) {
        replaceFragmentInActivity(
            R.id.container,
            ContractAttachmentFragment(
                contractId,
                object : ContractAttachmentFragment.OnActionListener {
                    override fun onItemClick(dcId: Long, caId: Long) {
                        val intent = Intent(this@ContractActivity, ContractsAttachmentActivity::class.java)
                        val bundle = Bundle()
                        bundle.putLong(ContractsConst.KEY_DC_ID, dcId)//patId
                        bundle.putLong(ContractsConst.KEY_RELATED_TABLE_ID, caId)
                        bundle.putString(
                            ContractsConst.KEY_RELATED_TABLE_NAME,
                            ContractsConst.KEY_CONTRACT
                        )
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }
            ),
            ContractAttachmentFragment.TAG
        )
    }

    private fun gotoContract() {
        replaceFragmentInActivity(
            R.id.container,
            ContractListFragment(
                listener = listener
            ),
            ContractListFragment.TAG
        )
    }

    private fun gotoContractListWithFilter(list: MutableList<ContractFilterModel>) {
        replaceFragmentInActivity(
            R.id.container,
            ContractListFragment(
                list,
                listener
            ),
            ContractListFragment.TAG
        )
    }

    private fun gotoSearch() {
        replaceFragmentInActivity(
            R.id.container,
            ContractSearchFragment(listener),
            ContractSearchFragment.TAG
        )
    }


    private fun gotoContractFilter(listFilter: MutableList<ContractFilterModel>) {
        replaceFragmentInActivity(
            R.id.container,
            ContractFilterFragment(
                listFilter,
                object : ContractFilterFragment.OnActionListener {
                    override fun onFilterChoose(list: MutableList<ContractFilterModel>) {
                        gotoContractListWithFilter(list)
                    }
                }
            ),
            ContractFilterFragment.TAG
        )
    }


    override fun showNoConnection() {
        addFragmentInActivity(
            R.id.container,
            ContractsNoConnectionFragment(),
            ContractsNoConnectionFragment.TAG
        )
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        when {
            fragment is OnBackPressed -> fragment.onBackPressed()
            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStack()
            else -> this.finish()
        }
    }

}