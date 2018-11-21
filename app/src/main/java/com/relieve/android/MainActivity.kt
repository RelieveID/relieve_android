package com.relieve.android

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.adapter.RvAdapter
import com.relieve.android.components.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = object : RvAdapter<RelieveViewHolder>(this) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelieveViewHolder {
            return when (viewType) {
                TitleBar.VIEW_TYPE -> {
                    TitleBar.createViewHolder(ctx, parent)
                }
                UserBar.VIEW_TYPE -> {
                    UserBar.createViewHolder(ctx, parent)
                }
                StatusBar.VIEW_TYPE -> {
                    StatusBar.createViewHolder(ctx, parent)
                }
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
            add(UserBar("Halo", "Muh. Alif Akbar"))
            add(StatusBar(".jpg", "Bojongsoang, Bandung Barat"))
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
