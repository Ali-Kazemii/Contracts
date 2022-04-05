package ir.nik.contract.view.base

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ir.nik.contract.utils.isConnected
import ir.nik.contract.utils.isSecure
import ir.nik.contract.view.gateway.ContractsGatewayActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class ContractsBaseActivity
    : AppCompatActivity() {

    private val viewModel by viewModel<ContractPrivateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isSecure)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        setup()
        handleOnClickListeners()
        handleObservers()
        handleError()
    }

    fun logout() {
        viewModel.isLogout = true
        startActivity(Intent(this, ContractsGatewayActivity::class.java))
    }

    fun checkCurrentNetwork() {
        if (!viewModel.isOfflineMode)
            if (!isConnected()) {
                showNoConnection()
            }
    }

    open fun setup() {}
    open fun handleError() {}
    open fun handleOnClickListeners() {}
    open fun handleObservers() {}
    open fun showNoConnection(){}
}