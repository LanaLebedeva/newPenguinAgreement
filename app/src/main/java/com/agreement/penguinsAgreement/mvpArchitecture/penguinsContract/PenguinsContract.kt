package com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract

import android.content.SharedPreferences
import android.content.res.Resources
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.google.android.material.textfield.TextInputEditText

interface PenguinsContract {
    interface PenguinView {
        fun initView()
        fun initListeners()
        fun onPauseViewPenguins()
        fun setPenguins(pluralsNumber: String?)
        fun setDays(pluralsNumber: String?)
    }

    interface PenguinPresenter {
        fun updatePluralNumberViews(numberToString: String, plurals: Int): String?
        fun makeAgreement()
    }

    interface PenguinModel {
        fun initModelPinguins(_binding: ActivityMainBinding, _preferences: SharedPreferences, _resources: Resources)
        fun getBinding(): ActivityMainBinding?
        fun getPreferences(): SharedPreferences?
        fun getResources(): Resources?
        fun updatePluralNumberViews(numberToString: String, plurals: Int): String?
        fun updateAgreement()
    }
}