package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.utils.PreferenceUtil

interface PenguinsContract {
    interface PenguinView {
        fun initView()
        fun initListeners()
        fun saveViewPenguins()
        fun updatePenguins(pluralsNumber: String?)
        fun updateDays(pluralsNumber: String?)
        fun setAgreement(agreementReturn: String)
    }

    interface PenguinPresenter {
        fun onPluralNumberTextViewsChange(numberToString: String, @PluralsRes plurals: Int): String?
        fun onFormAgreementClick()
    }

    interface PenguinModel {
//        fun getBinding(): ActivityMainBinding?
        fun getPreferences(): PreferenceUtil
        fun getResources(): Resources?
        fun updatePlural(numberToString: String, @PluralsRes plurals: Int): String?
        fun updateAgreement(): Boolean
    }
}