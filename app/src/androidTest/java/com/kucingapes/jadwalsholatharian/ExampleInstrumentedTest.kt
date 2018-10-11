/*
 * ExampleInstrumentedTest.kt on JadwalSholatHarian
 * Developed by Muhammad Utsman on 10/11/18 8:26 AM
 * Last modified 10/11/18 8:07 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.jadwalsholatharian

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.kucingapes.jadwalsholatharian", appContext.packageName)
    }
}
