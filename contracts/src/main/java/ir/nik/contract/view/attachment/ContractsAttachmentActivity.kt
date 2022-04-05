package ir.nik.contract.view.attachment

import android.os.Bundle
import ir.awlrhm.modules.extentions.addFragmentInActivity
import ir.awlrhm.modules.extentions.replaceFragmentInActivity
import ir.nik.contract.utils.ContractsConst
import ir.nik.contract.view.base.ContractsBaseActivity
import ir.nik.contract.view.noconnection.ContractsNoConnectionFragment
import ir.nik.contracts.R

internal class ContractsAttachmentActivity : ContractsBaseActivity() {

    private var dcId: Long? = null
    private var relatedTableId: Long? = null
    private var relatedTableName: String? = null

    override fun setup() {
        dcId = intent.extras?.getLong(ContractsConst.KEY_DC_ID)
        relatedTableId = intent.extras?.getLong(ContractsConst.KEY_RELATED_TABLE_ID)
        relatedTableName = intent.extras?.getString(ContractsConst.KEY_RELATED_TABLE_NAME)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_attachment_contracts)
        super.onCreate(savedInstanceState)

        replaceFragmentInActivity(
            R.id.container,
            ContractsAttachmentFragment(
                dcId ?: 0,
                relatedTableId ?: 0,
                object : ContractsAttachmentFragment.OnActionListener {
                    override fun onAddClick() {
                        gotoAddAttachment()
                    }
                }
            ),
            ContractsAttachmentFragment.TAG
        )
    }

    private fun gotoAddAttachment() {
        replaceFragmentInActivity(
            R.id.container,
            ContractsAddAttachmentFragment(
                relatedTableId ?: 0,
                relatedTableName ?: "",
                dcId?: 0,
            ),
            ContractsAddAttachmentFragment.TAG
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
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else
            this.finish()
    }
}
