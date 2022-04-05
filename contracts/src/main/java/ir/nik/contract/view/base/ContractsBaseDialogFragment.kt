package ir.nik.contract.view.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

abstract class ContractsBaseDialogFragment: DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        handleObservers()
        handleOnClickListeners()
        handleError()
    }

    abstract fun setup()
    abstract fun handleObservers()
    abstract fun handleOnClickListeners()
    abstract fun handleError()

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}