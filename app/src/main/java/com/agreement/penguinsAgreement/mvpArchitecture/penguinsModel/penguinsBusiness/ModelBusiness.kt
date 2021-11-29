package com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.penguinsBusiness

import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.R
import java.lang.NumberFormatException

class ModelBusiness{
    private  lateinit var preferences: SharedPreferences
    private lateinit var resources: Resources

    fun initModelBusiness(_preferences: SharedPreferences, _resources: Resources) {
        preferences = _preferences
        resources = _resources
    }

    fun updatePlural(
        numberToString: String,
        @PluralsRes plurals: Int,
    ): String {
        val parsInt: Int = try {
            Integer.parseInt(numberToString)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
            val e1 = Log.e(
                "ModelPenguins",
                "The number of penguins or days greater than Integer.MAX_INT"
            )
            e1 // TODO понять что такое е1 и изменить название
        }
        val pluralStr = resources.getQuantityString(plurals, parsInt)
        when (plurals) {
            R.plurals.penguins -> preferences.edit().putString(PREF_STR_PENGUINS,pluralStr).apply()
            R.plurals.days -> preferences.edit().putString(PREF_STR_DAYS,pluralStr).apply()
        }
        return pluralStr
    }
    fun updateAgreement(): Boolean {
//        val title: String = binding?.tvTitle?.text.toString()
//        val subject: String = binding?.tvSubject?.text.toString()
//        val numberPenguins: String = binding?.tvPenguinsNumber?.text.toString()
//        val numberDays: String = binding?.tvDaysNumber?.text.toString()
        val title: String? = preferences.getString(PREF_STR_TITLE, "")
        val subject: String? = preferences.getString(PREF_STR_SUBJECT, "")
        val numberPenguins: String? = preferences.getString(PREF_STR_NUMBER_PENGUINS, "")
        val numberDays:String? = preferences.getString(PREF_STR_NUMBER_DAYS,"")

        val fieldsNotEmpty =
            title != "" && subject != "" && numberPenguins != "" && numberDays != ""
        val agreementStr= if (fieldsNotEmpty) {
            resources.getString(R.string.text_agreement,
                title,
                subject,
                numberPenguins,
                numberDays)
        } else {
            resources.getString(R.string.text_there_will_be_an_agreement)
        }
        preferences.edit().putString(PREF_STR_AGREEMENT, agreementStr).apply()
        return fieldsNotEmpty
    }
    companion object {
        private const val PREF_STR_AGREEMENT = "AGREEMENT_VARIABLE"
        private const val PREF_STR_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val PREF_STR_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val PREF_STR_TITLE = "TITLE_KEY"
        private const val PREF_STR_SUBJECT = "SUBJECT_KEY"
        private const val PREF_STR_PENGUINS = "PENGUINS"
        private const val PREF_STR_DAYS = "DAYS"
    }
}
