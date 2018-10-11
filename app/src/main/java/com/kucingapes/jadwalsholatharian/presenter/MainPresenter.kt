/*
 * MainPresenter.kt on JadwalSholatHarian
 * Developed by Muhammad Utsman on 10/11/18 8:33 AM
 * Last modified 10/11/18 8:33 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.jadwalsholatharian.presenter

import com.androidnetworking.error.ANError
import com.kucingapes.jadwalsholatharian.model.Shalat
import com.kucingapes.jadwalsholatharian.view.Contractor

class MainPresenter(private var mainView: Contractor.MainView,
                    private var getSalatInteractor: Contractor.GetSalatInteractor) : Contractor.Presenter, Contractor.GetSalatInteractor.OnFinishListener {

    override fun requestDataAPI() {
        getSalatInteractor.getData(this)
    }

    override fun onFinished(salat: Shalat) {
        mainView.setDataSalat(salat)
    }

    override fun onFailed(anError: ANError) {
        mainView.onResponseFailed(anError)
    }
}