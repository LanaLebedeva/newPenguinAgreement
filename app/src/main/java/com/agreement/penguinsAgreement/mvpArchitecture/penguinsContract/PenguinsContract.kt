package com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.google.android.material.textfield.TextInputEditText

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
        fun updatePluralNumberTextViews(numberToString: String, @PluralsRes plurals: Int): String?
        fun updateAgreementTextView()
    }

    interface PenguinModel {
//        fun getBinding(): ActivityMainBinding?
        fun getPreferences(): SharedPreferences?
        fun getResources(): Resources?
        fun updatePlural(numberToString: String, @PluralsRes plurals: Int): String?
        fun updateAgreement(): Boolean
    }
}