package com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.PluralsRes

interface PenguinsContract {
    interface PenguinView {
        fun initView()
        fun initListeners()
        fun onSaveViewPenguins()
        fun updatePenguins(pluralsNumber: String?)
        fun updateDays(pluralsNumber: String?)
        fun setAgreement(agreementReturn: String)
    }

    interface PenguinPresenter {
        fun changedPluralNumberTextViews(numberToString: String, @PluralsRes plurals: Int): String?
        fun changedAgreementTextView()
    }

    interface PenguinModel {
//        fun getBinding(): ActivityMainBinding?
        fun getPreferences(): SharedPreferences?
        fun getResources(): Resources?
        fun updatePlural(numberToString: String, @PluralsRes plurals: Int): String?
        fun updateAgreement(): Boolean
    }
}