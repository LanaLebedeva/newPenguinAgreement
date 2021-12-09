package com.agreement.penguinsAgreement

import android.app.Application
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.utils.PreferenceUtil

class Penguins : Application() {

    override fun onCreate() {
        super.onCreate()
        ModelPenguins.initPreferenceResources(PreferenceUtil(applicationContext), resources)
    }
}