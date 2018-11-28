package com.relieve.android.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.relieve.android.R
import com.relieve.android.components.*
import com.relieve.android.rsux.adapter.HorizontalRecycler
import com.relieve.android.rsux.helper.dpToPx
import com.relieve.android.rsux.adapter.VerticalAdapter
import com.relieve.android.rsux.component.SpaceItem
import kotlinx.android.synthetic.main.recycler_view_full.view.*
import kotlinx.android.synthetic.main.sheet_notice.*

class DashboardHomeFragment : Fragment() {
    private val adapter = VerticalAdapter()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view_full, container, false).apply {
            this.rvFull.layoutManager = LinearLayoutManager(context)
            this.rvFull.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        render()
    }

    private fun render() {
        adapter.removeAll()
        adapter.apply {
            add(UserBarItem("Halo", "Muh. Alif Akbar"))
            add(StatusBarItem(".jpg", "Bojongsoang, Bandung Barat"))
            add(TitleBarItem("Discover", "Update informasi terkini bencana di seluruh Indonesia"))
            add(
                HorizontalRecycler(
                    listOf(
                        SpaceItem(
                            8.dpToPx(),
                            LinearLayout.LayoutParams.MATCH_PARENT
                        ),
                        DiscoverItem(0, 0, "Palu", 0, false),
                        DiscoverItem(0, 0, "Lombok", 100, false),
                        DiscoverItem(0, 0, "Lombok", 3_000, false),
                        SpaceItem(8.dpToPx(), LinearLayout.LayoutParams.MATCH_PARENT)
                    ), 1
                )
            )
            add(TitleBarItem("Daftar Kerabat", "Pantau kondisi kerabat terdekat anda dimanapun berada"))
            add(
                HorizontalRecycler(
                    listOf(
                        SpaceItem(
                            8.dpToPx(),
                            LinearLayout.LayoutParams.MATCH_PARENT
                        ),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Good, "Ayah"),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Bad, "Ibu"),
                        FamilyItem(".jpg", FamilyItem.FamilyStatus.Unknown, "Kasih Ku") {
                            test()
                        },
                        FamilyItem("", FamilyItem.FamilyStatus.Unknown, "", true),
                        SpaceItem(8.dpToPx(), LinearLayout.LayoutParams.MATCH_PARENT)
                    ), 2
                )
            )
        }
    }

    private fun test() {
        context?.let { ctx ->
            BottomSheetDialog(ctx).apply {
                setContentView(layoutInflater.inflate(R.layout.sheet_notice, null))
                var moveToNext = false
                this.btnPositive.setOnClickListener {
                    moveToNext = true
                    dismiss()
                }

                this.btnNegative.setOnClickListener {
                    moveToNext = true
                    dismiss()
                }
                show()

                setOnDismissListener {
                    if (moveToNext) findNavController().navigate(R.id.action_dashboardFragment_to_disasterFragment)
                }
            }
        }
    }
}