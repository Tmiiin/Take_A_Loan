package com.example.takealoan.loanscreen.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.takealoan.R
import com.example.takealoan.loanscreen.ui.Fragment1
import com.google.android.material.snackbar.Snackbar


class LoanView: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_fragment)
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            onFabClick(view)
        }
        supportFragmentManager.beginTransaction().add(R.id.container, Fragment1(this), "")
        .commit()
    }

    fun onFabClick(it: View){
        Snackbar.make(it, "Here's a Snackbar", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
}