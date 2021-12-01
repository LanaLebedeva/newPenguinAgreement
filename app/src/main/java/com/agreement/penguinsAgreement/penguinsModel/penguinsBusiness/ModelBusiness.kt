package com.agreement.penguinsAgreement.penguinsModel.penguinsBusiness

import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.utils.PreferenceUtil
import java.lang.NumberFormatException

class ModelBusiness{
    private  lateinit var preferences: PreferenceUtil
    private lateinit var resources: Resources

    fun initModelBusiness(_preferences: PreferenceUtil, _resources: Resources) {
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
            Log.e(
                "ModelPenguins",
                "The number of penguins or days greater than Integer.MAX_INT"
            )
            Integer.MAX_VALUE
        }
        val pluralStr = resources.getQuantityString(plurals, parsInt)
        when (plurals) {
            R.plurals.penguins -> preferences.setPrefStrPenguins(pluralStr)
            R.plurals.days -> preferences.setPrefStrDays(pluralStr)
        }
        return pluralStr
    }
    fun updateAgreement(): Boolean {
        val title: String? = preferences.getPrefStrTitle()
        val subject: String? = preferences.getPrefStrSubject()
        val numberPenguins: String? = preferences.getPrefStrNumberPenguins()
        val numberDays:String? = preferences.getPrefStrNumberDays()

        val fieldsNotEmpty =
            title != null && subject != null && numberPenguins != null && numberDays != null
        val agreementStr= if (fieldsNotEmpty) {
            resources.getString(R.string.text_agreement,
                title,
                subject,
                numberPenguins,
                numberDays)
        } else {
            resources.getString(R.string.text_there_will_be_an_agreement)
        }
        preferences.setPrefStrAgreement(agreementStr)
        return fieldsNotEmpty
    }
}
