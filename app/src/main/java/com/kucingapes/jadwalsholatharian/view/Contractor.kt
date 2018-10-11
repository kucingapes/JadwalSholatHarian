/*
 * Contractor.kt on JadwalSholatHarian
 * Developed by Muhammad Utsman on 10/11/18 10:18 AM
 * Last modified 10/11/18 9:52 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.jadwalsholatharian.view

import com.androidnetworking.error.ANError
import com.kucingapes.jadwalsholatharian.model.Shalat

interface Contractor {
    interface Presenter {
        fun requestDataAPI()
    }

    interface MainView {
        fun setDataSalat(salat: Shalat)
        fun onResponseFailed(anError: ANError)
    }

    interface GetSalatInteractor {
        interface OnFinishListener {
            fun onFinished(salat: Shalat)
            fun onFailed(anError: ANError)
        }

        fun getData(onFinishListener: OnFinishListener)
    }
}