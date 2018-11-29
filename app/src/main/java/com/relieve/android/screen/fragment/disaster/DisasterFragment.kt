package com.relieve.android.screen.fragment.disaster

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.relieve.android.R
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.screen.viewmodel.DisasterViewModel
import kotlinx.android.synthetic.main.fragment_disaster.*

class DisasterFragment : RsuxFragment<DisasterViewModel.DisasterState, DisasterViewModel>() {

    init {
        layoutId = R.layout.fragment_disaster
    }

    override val vModel by lazy { ViewModelProviders.of(this).get(DisasterViewModel::class.java) }

    override fun render(state: DisasterViewModel.DisasterState) {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
