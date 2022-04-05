package ir.nik.contract.view.noconnection

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.nik.contract.utils.APP_NAME
import ir.nik.contract.view.base.ContractPrivateViewModel
import ir.nik.contract.view.gateway.ContractsGatewayActivity
import ir.nik.contracts.R
import kotlinx.android.synthetic.main.fragment_no_connection_contracts.*
import org.koin.androidx.viewmodel.ext.android.viewModel


internal class ContractsNoConnectionFragment: Fragment() {

    private val viewModel by viewModel<ContractPrivateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_connection_contracts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutOffline.setOnClickListener {
            logout()
        }

       layoutWifi.setOnClickListener {
           startActivity( Intent(Settings.ACTION_WIFI_SETTINGS))
       }
        layoutMobile.setOnClickListener {
            startActivity( Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS))
        }
    }
    private fun logout() {
        val activity = activity ?: return
        viewModel.isOfflineMode = true
        activity.startActivity(Intent(activity, ContractsGatewayActivity::class.java))
    }

    companion object{
        val TAG = "$APP_NAME: ${ContractsNoConnectionFragment::class.java.simpleName}"
    }
}