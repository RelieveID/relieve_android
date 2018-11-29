package com.relieve.android.screen.fragment.call

import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.relieve.android.R
import com.relieve.android.components.*
import com.relieve.android.rsux.adapter.VerticalGridRecycler
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.helper.setupWithBaseAdapter
import com.relieve.android.rsux.component.SnackBarItem
import com.relieve.android.rsux.component.SpaceItem
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.screen.viewmodel.CallViewModel
import kotlinx.android.synthetic.main.recycler_view_with_toolbar_and_bottom_action.*

class CallListFragment : RsuxFragment<CallViewModel.CallState, CallViewModel>() {
    override val vModel by lazy { ViewModelProviders.of(this).get(CallViewModel::class.java) }

    private val adapter by lazy {
        rvWithToolbar.setupWithBaseAdapter()
    }

    init {
        layoutId = R.layout.recycler_view_with_toolbar_and_bottom_action
    }

    override fun render(state : CallViewModel.CallState) {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        adapter.removeAll()
        adapter.apply {
            add(ImageItem())
            add(SpaceItem(LinearLayout.LayoutParams.MATCH_PARENT, 8.dpToPx()))
            add(UserBarItem("", "Tentukan Panggilan Pilihanmu"))
            add(SpaceItem(LinearLayout.LayoutParams.MATCH_PARENT, 12.dpToPx()))
            add(
                VerticalGridRecycler(
                    listOf(
                        EmergencyOptionItem(R.drawable.ic_add_more, "Tambah Lainnya"),
                        EmergencyCallItem(R.drawable.ic_guard, getString(R.string.emergency_police)),
                        EmergencyCallItem(R.drawable.ic_ambulance, getString(R.string.emergency_hospital)),
                        EmergencyCallItem(R.drawable.ic_red_cross, getString(R.string.emergency_red_cross)),
                        EmergencyCallItem(R.drawable.ic_fire, getString(R.string.emergency_fire_fighter)),
                        EmergencyCallItem(R.drawable.ic_flashlight, getString(R.string.emergency_sar)) {
                            test()
                        }
                    ), 2
                ) { 1 }
            )
        }


    }

    private fun test() {
        SnackBarItem.make(rootCoordinator, Snackbar.LENGTH_INDEFINITE).apply {
            setButtonClick { this.dismiss() }
        }.show()
    }
}
