package ir.nik.contract.view.gateway

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.nik.contract.di.injectContractsKoin
import ir.nik.contract.view.base.ContractPrivateViewModel
import ir.nik.contract.view.contracts.ContractsActivity
import ir.nik.contract.view.gateway.model.ContractsBindDataModel
import ir.nik.contract.view.gateway.model.KEY_MODULE_CONTRACTS
import ir.nik.contracts.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContractsGatewayActivity : AppCompatActivity() {

    private val viewModel by viewModel<ContractPrivateViewModel>()

    companion object {
        const val KEY_RESULT = "result"
        const val LOG_OUT = 1234321
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectContractsKoin()
        setContentView(R.layout.activity_gateway_contracts)

        //TODO: handle offline mode
//        if(viewModel.isOfflineMode)

        if (viewModel.isLogout) {
            viewModel.isLogout = false

            val intent = Intent()
            intent.putExtra(KEY_RESULT, LOG_OUT)
            setResult(Activity.RESULT_OK, intent)
            this@ContractsGatewayActivity.finish()

        } else {
            getInitValues()

            gotoContractActivity()
        }
    }

    private fun gotoContractActivity() {
        startActivity(Intent(this@ContractsGatewayActivity, ContractsActivity::class.java))
    }

    private fun getInitValues() {

        val model = intent.getSerializableExtra(KEY_MODULE_CONTRACTS) as ContractsBindDataModel

        viewModel.accessToken = model.token

        viewModel.hostName = model.hostName

        viewModel.personnelId = model.personnelId

        viewModel.userId = model.userId

        viewModel.imei = model.imei

        viewModel.osVersion = model.osVersion

        viewModel.deviceModel = model.deviceModel

        viewModel.appVersion = model.appVersion
    }
}