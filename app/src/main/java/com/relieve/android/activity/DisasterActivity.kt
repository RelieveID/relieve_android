package com.relieve.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.relieve.android.R
import kotlinx.android.synthetic.main.activity_disaster.*

class DisasterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disaster)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
