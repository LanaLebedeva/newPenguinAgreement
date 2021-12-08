package com.agreement.penguinsAgreement

import android.app.Application
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.utils.PreferenceUtil

class App : Application() {
    val preferences = PreferenceUtil(this)

    override fun onCreate() {
        super.onCreate()
        ModelPenguins.initPreferenceResources(preferences, resources)
    }
}