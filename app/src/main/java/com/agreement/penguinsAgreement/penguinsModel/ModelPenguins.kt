package com.agreement.penguinsAgreement.penguinsModel

import android.content.res.Resources
import android.util.Log
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.utils.PreferenceUtil

object ModelPenguins {
    private lateinit var preferences: PreferenceUtil
    private lateinit var resources: Resources

    fun initPreferenceResources(_preferences: PreferenceUtil, _resources: Resources) {
        preferences = _preferences
        resources = _resources
    }

    private fun getPreferences() = preferences

    fun getResources() = resources

    fun getPluralPenguins(): String {
        val parsInt: Int = try {
            Integer.parseInt(
                getNumberPenguinsOrNull()
                    ?: resources.getString(R.string.text_penguins)
            )
        } catch (e: NumberFormatException) {
            Log.e(
                "ModelPenguins",
                "The number of penguins greater than Integer.MAX_INT"
            )
            Integer.MAX_VALUE
        }
        return resources.getQuantityString(R.plurals.penguins, parsInt)
    }

    fun getPluralDays(): String {
        val parsInt: Int = try {
            Integer.parseInt(
                getNumberDaysOrNull()
                    ?: resources.getString(R.string.text_days)
            )
        } catch (e: NumberFormatException) {
            Log.e(
                "ModelPenguins",
                "The number of days greater than Integer.MAX_INT"
            )
            Integer.MAX_VALUE
        }
        return resources.getQuantityString(R.plurals.days, parsInt)
    }

    fun getAgreement(): String {
        val title: String = getTitle()
        val subject: String = getSubject()
        val numberPenguins: String = getNumberPenguins()
        val numberDays: String = getNumberDays()

        val fieldsNotEmpty =
            title != "" && subject != "" && numberPenguins != "" && numberDays != ""
        val agreementStr = if (fieldsNotEmpty) {
            resources.getString(
                R.string.text_agreement,
                title,
                subject,
                numberPenguins,
                numberDays
            )
        } else {
            resources.getString(R.string.text_there_will_be_an_agreement)
        }
        return agreementStr
    }

    fun checkUpdateAgreement(): Boolean {
        if (getAgreement() == resources.getString(R.string.text_there_will_be_an_agreement)) {
            return false
        }
        return true
    }

    fun getTitle(): String =
        preferences.getPrefStrTitle() ?: ""

    fun getSubject(): String =
        preferences.getPrefStrSubject() ?: ""

    fun getNumberPenguins(): String =
        preferences.getPrefStrNumberPenguins() ?: ""

    fun getNumberPenguinsOrNull(): String? =
        preferences.getPrefStrNumberPenguins()

    fun getNumberDays(): String =
        preferences.getPrefStrNumberDays() ?: ""

    fun getNumberDaysOrNull(): String? =
        preferences.getPrefStrNumberDays()

    fun setTitle(title: String) =
        preferences.setPrefStrTitle(title)

    fun setSubject(subject: String) =
        preferences.setPrefStrSubject(subject)


    fun setPenguinsNumber(numberPenguins: String) =
        preferences.setPrefStrNumberPenguins(numberPenguins)

    fun setDaysNumber(numberDays: String) =
        preferences.setPrefStrNumberDays(numberDays)

    fun setBoolOnAgreement(onAgreement: Boolean) =
        getPreferences().setPrefBoolOnAgreement(onAgreement)

    fun getBoolOnAgreement(): Boolean =
        getPreferences().getPrefBoolOnAgreement()
}
