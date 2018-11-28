package com.relieve.android.fragment.call

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.relieve.android.R
import com.relieve.android.components.*
import com.relieve.android.rsux.adapter.VerticalGridRecycler
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.adapter.VerticalAdapter
import com.relieve.android.rsux.component.SnackBarItem
import com.relieve.android.rsux.component.SpaceItem
import kotlinx.android.synthetic.main.recycler_view_with_toolbar_and_bottom_action.*

class CallListFragment : Fragment() {

    private val adapter = VerticalAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recycler_view_with_toolbar_and_bottom_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        rvWithToolbar.layoutManager = LinearLayoutManager(context)
        rvWithToolbar.adapter = adapter

        render()
    }

    private fun render() {
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
