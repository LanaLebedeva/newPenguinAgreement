package com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract

import android.content.SharedPreferences
import android.content.res.Resources
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding

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
        fun setBinding(_binding: ActivityMainBinding)
        fun setPreference(_preferences: SharedPreferences)
        fun setResources(_resources: Resources)
        fun makePlural(numberToString: String, plurals: Int)
        fun makeAgreement()
    }
}