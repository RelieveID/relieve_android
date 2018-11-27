package com.relieve.android.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.relieve.android.R
import com.relieve.android.components.*
import com.relieve.android.rsux.adapter.HorizontalRecycler
import com.relieve.android.rsux.adapter.VerticalGridRecycler
import com.relieve.android.rsux.helper.dptoPx
import com.relieve.android.rsux.adapter.VerticalAdapter
import kotlinx.android.synthetic.main.recycler_view_full.*
import kotlinx.android.synthetic.main.recycler_view_with_toolbar.*

class CallActivity : AppCompatActivity() {

    private val adapter = VerticalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_with_toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        rvWithToolbar.layoutManager = LinearLayoutManager(this)
        rvWithToolbar.adapter = adapter

        render()
    }

    private fun render() {
        adapter.removeAll()
        adapter.apply {
            add(UserBarItem("", getString(R.string.emergency_call)))
            add(LocationPickerItem())
            add(TitleBarItem(getString(R.string.emergency_foundation), ""))
            add(
                VerticalGridRecycler(
                listOf(
                    EmergencyCallItem(R.drawable.ic_guard, getString(R.string.emergency_police)),
                    EmergencyCallItem(R.drawable.ic_ambulance, getString(R.string.emergency_hospital)) {
                        test()
                    },
                    EmergencyCallItem(R.drawable.ic_red_cross, getString(R.string.emergency_red_cross)),
                    EmergencyCallItem(R.drawable.ic_fire, getString(R.string.emergency_fire_fighter)),
                    EmergencyCallItem(R.drawable.ic_flashlight, getString(R.string.emergency_sar)),
                    EmergencyOptionItem(R.drawable.ic_others, getString(R.string.emergency_other)) {
                        startActivity(Intent(this@CallActivity, CallListActivity::class.java))
                    }
                ), 2) { 1 }
            )
            add(TitleBarItem(getString(R.string.emergency_fam), ""))
            add(
                HorizontalRecycler(
                    listOf(
                        SpaceItem(8.dptoPx(), LinearLayout.LayoutParams.MATCH_PARENT),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Good, "Ayah"),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Bad, "Ibu"),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Unknown, "Kasih Ku") {
                            test()
                        },
                        FamilyItem("", FamilyItem.FamilyStatus.Unknown, "", true),
                        SpaceItem(8.dptoPx(), LinearLayout.LayoutParams.MATCH_PARENT)
                    )
                )
            )
        }
    }

    private fun test() {
        BottomSheetDialog(this).apply {
            setContentView(layoutInflater.inflate(R.layout.recycler_view_full, null))
            this.rvFull.layoutManager = LinearLayoutManager(this.context)
            this.rvFull.adapter = VerticalAdapter().apply {
                this.add(SpaceItem(LinearLayout.LayoutParams.MATCH_PARENT, 16.dptoPx()))
                this.add(TitleBarItem("Daftar kontak ambulance", ""))
                this.add(SpaceItem(LinearLayout.LayoutParams.MATCH_PARENT, 16.dptoPx()))
                this.add(CallItem("RS Islam Muhammadiyah", "0.8 Km", ""))
                this.add(CallItem("RS Islam Muhammadiyah", "2.3 Km", ""))
                this.add(CallItem("RS Islam Muhammadiyah", "2.8 Km", ""))
                this.add(CallItem("RS Islam Muhammadiyah", "3.4 Km", ""))
                this.add(SpaceItem(LinearLayout.LayoutParams.MATCH_PARENT, 16.dptoPx()))
            }
            show()
        }
    }
}
