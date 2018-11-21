package com.relieve.android.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.helper.BottomNavBar
import com.relieve.android.R
import com.relieve.android.adapter.RvAdapter
import com.relieve.android.base.Component
import com.relieve.android.base.RelieveViewHolder
import com.relieve.android.components.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = object : RvAdapter<RelieveViewHolder>(this) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                TitleBarItem.VIEW_TYPE -> { TitleBarItem.createViewHolder(ctx, parent) }
                UserBarItem.VIEW_TYPE -> { UserBarItem.createViewHolder(ctx, parent) }
                StatusBarItem.VIEW_TYPE -> { StatusBarItem.createViewHolder(ctx, parent) }
                DiscoverItem.VIEW_TYPE -> { DiscoverItem.createViewHolder(ctx, parent) }
                FamilyItem.VIEW_TYPE -> { FamilyItem.createViewHolder(ctx, parent) }
                else -> object : RelieveViewHolder(View(ctx)) {
                    override fun bind(data: Component) {}
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHome.layoutManager = LinearLayoutManager(this)
        rvHome.adapter = adapter
        bindNavClick()

        render()
    }

    private fun render() {
        adapter.apply {
            add(UserBarItem("Halo", "Muh. Alif Akbar"))
            add(StatusBarItem(".jpg", "Bojongsoang, Bandung Barat"))
            add(TitleBarItem("Discover", "Update informasi terkini bencana di seluruh Indonesia"))
            add(DiscoverItem(0, 0, "Palu", 0,true))
            add(DiscoverItem(0, 0, "Lombok", 100, false))
            add(DiscoverItem(0, 0, "Lombok", 3_000, false))
            add(TitleBarItem("Daftar Kerabat", "Pantau kondisi kerabat terdekat anda dimanapun berada"))
            add(FamilyItem(".jpg", FamilyStatus.Good, "Ayah"))
            add(FamilyItem(".jpg", FamilyStatus.Bad, "Ibu"))
            add(FamilyItem(".jpg", FamilyStatus.Unknown, "Kasih Ku"))
            add(FamilyItem("", FamilyStatus.Unknown, "", true))
        }
    }

    private fun bindNavClick() {
        BottomNavBar(navHome).apply {
            setHomeClickListener {

            }
            setDiscoverClickListener {

            }
            setCallClickListener {

            }
            setChatClickListener {

            }
            setProfileClickListener {

            }
        }
    }
}
