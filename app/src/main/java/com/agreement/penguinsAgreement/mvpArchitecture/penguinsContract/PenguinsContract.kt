package com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract

import android.content.SharedPreferences
import android.content.res.Resources
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins

interface PenguinsContract {
    interface PenguinView {
        fun initView()
        fun initListeners()
        fun onPauseViewPenguins()
    }

    interface PenguinPresenter {
        fun makePlural(numberToString: String, plurals: Int)
        fun makeAgreement()
    }

    interface PenguinModel {
        fun initModelPinguins(_binding: ActivityMainBinding, _preferences: SharedPreferences, _resources: Resources)
        fun getBinding(): ActivityMainBinding?
        fun getPreferences(): SharedPreferences?
        fun getResources(): Resources?
        fun makePlural(numberToString: String, plurals: Int)
        fun makeAgreement()
    }
}