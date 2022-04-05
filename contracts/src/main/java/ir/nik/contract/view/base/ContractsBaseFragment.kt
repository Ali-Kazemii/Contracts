package ir.nik.contract.view.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class ContractsBaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        handleOnClickListeners()
        handleObservers()
        handleError()
    }


    fun logout(){
        (activity as ContractsBaseActivity).logout()
    }

    private fun checkCurrentNetwork(){
        (activity as ContractsBaseActivity).checkCurrentNetwork()
    }

    open fun setup(){}
    open fun handleOnClickListeners(){}
    open fun handleObservers(){}
    open fun handleError(){
        checkCurrentNetwork()
    }
}