package com.night.suitmedia_test

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.night.suitmedia_test.databinding.ActivityMainBinding
import com.night.suitmedia_test.databinding.DialogCloseAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host)


        val actionBar = supportActionBar
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            actionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        } else {
            actionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }

        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.firstFragment
                -> {
                    actionBar!!.hide()
                }
                else -> {
                    actionBar!!.show()
                }
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {
        var backState = false
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.firstFragment-> {
                    backState = false
                }
                else -> {
                    backState = true
                }
            }
        }
        if (backState) {
            onBackPressedDispatcher.onBackPressed()
        } else {
            quitDialog()
        }
    }

    private fun quitDialog() {
        val quitApps = DialogCloseAppBinding.inflate(LayoutInflater.from(this))
        val quitDialogBuilder = AlertDialog.Builder(this, R.style.RoundedCornerDialog)
            .setView(quitApps.root)
        quitDialogBuilder.setCancelable(true)
        val showQuitDialog = quitDialogBuilder.show()
        quitApps.btnClose.setOnClickListener {
            finish()
        }
        quitApps.btnDismiss.setOnClickListener {
            showQuitDialog.cancel()
        }

    }

}
