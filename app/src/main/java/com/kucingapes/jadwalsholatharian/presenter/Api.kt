/*
 * Api.kt on JadwalSholatHarian
 * Developed by Muhammad Utsman on 10/11/18 10:19 AM
 * Last modified 10/11/18 9:52 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.jadwalsholatharian.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.kucingapes.jadwalsholatharian.BuildConfig
import com.kucingapes.jadwalsholatharian.model.Shalat
import com.kucingapes.jadwalsholatharian.view.Contractor
import java.util.*

class Api(var context: Context) : Contractor.GetSalatInteractor {

    @SuppressLint("MissingPermission")
    override fun getData(onFinishListener: Contractor.GetSalatInteractor.OnFinishListener) {

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val geocoder = Geocoder(context, Locale.getDefault())
        val cityAdress = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        val cityName = cityAdress[0].locality

        AndroidNetworking.get(BuildConfig.BaseUrl)
                .addPathParameter("APIKEY", BuildConfig.ApiKey)
                .addPathParameter("LOCATION", cityName)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(Shalat::class.java, object : ParsedRequestListener<Shalat> {
                    override fun onResponse(response: Shalat?) {
                        onFinishListener.onFinished(response!!)
                    }

                    override fun onError(anError: ANError?) {
                        onFinishListener.onFailed(anError!!)
                    }

                })
    }
}