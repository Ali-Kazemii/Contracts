package ir.nik.moduleprojects

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ir.nik.contract.view.gateway.ContractsGatewayActivity
import ir.nik.contract.view.gateway.model.ContractsBindDataModel
import ir.nik.contract.view.gateway.model.KEY_MODULE_CONTACTS
import ir.nik.modulecontracts.R
import ir.nik.moduleprojects.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContracts.setOnClickListener {
            showContracts()
        }
    }

    private fun showContracts() {
        val intent = Intent(this@MainActivity, ContractsGatewayActivity::class.java)

        val bundle = Bundle()
        val cardboardModel = ContractsBindDataModel(
            token = token,
            hostName = hostName,
            personnelId = personnelId,
            userId = userId,
            imei = imei,
            osVersion = osVersion,
            deviceModel = deviceModel,
            appVersion = appVersion,
        )

        bundle.putSerializable(KEY_MODULE_CONTACTS, cardboardModel)
        intent.putExtras(bundle)
        contractResult.launch(intent)
    }

    private var contractResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

        }
        //TODO: download and authentication error handling
        /*else{
                val data: Intent? = result.data
                val result = data?.getIntExtra(KEY_RESULT, 0)
                Toast.makeText(this@MainActivity, result.toString(), Toast.LENGTH_LONG).show()
            }*/
    }
}