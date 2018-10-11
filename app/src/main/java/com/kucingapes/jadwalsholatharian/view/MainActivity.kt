/*
 * MainActivity.kt on JadwalSholatHarian
 * Developed by Muhammad Utsman on 10/11/18 10:18 AM
 * Last modified 10/11/18 10:13 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.jadwalsholatharian.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.kucingapes.jadwalsholatharian.presenter.Api
import com.kucingapes.jadwalsholatharian.presenter.MainPresenter
import com.kucingapes.jadwalsholatharian.R
import com.kucingapes.jadwalsholatharian.model.Shalat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity(), Contractor.MainView {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(this)

        presenter = MainPresenter(this, Api(this))

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                presenter.requestDataAPI()

            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1)
            }
        } else {
            presenter.requestDataAPI()

        }

    }

    @SuppressLint("SetTextI18n")
    override fun setDataSalat(salat: Shalat) {
        val items = salat.items[0]

        subuh.text = ": ${convertTime(items.fajr)}"
        dzuhur.text = ": ${convertTime(items.dhuhr)}"
        asr.text = ": ${convertTime(items.asr)}"
        magrib.text = ": ${convertTime(items.maghrib)}"
        isya.text = ": ${convertTime(items.isha)}"

        header_title.text = "Jadwal Shalat Hari Ini Untuk Lokasi \n${salat.title}"
    }

    override fun onResponseFailed(anError: ANError) {
        Log.d("gagal", anError.message)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter.requestDataAPI()
                } else {
                    finish()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertTime(time: String) : String {
        val before = SimpleDateFormat("h:mm a").parse(time)
        return SimpleDateFormat("HH:mm").format(before)
    }
}
