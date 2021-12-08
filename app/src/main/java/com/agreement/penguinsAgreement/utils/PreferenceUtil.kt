package com.agreement.penguinsAgreement.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class PreferenceUtil(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(FILE_STR_SHARED_PREFERENCES, MODE_PRIVATE)

    fun getPrefStrNumberPenguins(): String? =
        preferences.getString(PREF_STR_NUMBER_PENGUINS, null)

    fun setPrefStrNumberPenguins(parameter: String) =
        preferences.edit()?.putString(PREF_STR_NUMBER_PENGUINS, parameter)?.apply()

    fun getPrefStrNumberDays(): String? =
        preferences.getString(PREF_STR_NUMBER_DAYS, null)

    fun setPrefStrNumberDays(parameter: String) =
        preferences.edit()?.putString(PREF_STR_NUMBER_DAYS, parameter)?.apply()

    fun getPrefStrTitle(): String? =
        preferences.getString(PREF_STR_TITLE, null)

    fun setPrefStrTitle(parameter: String) =
        preferences.edit()?.putString(PREF_STR_TITLE, parameter)?.apply()

    fun getPrefStrSubject(): String? =
        preferences.getString(PREF_STR_SUBJECT, null)

    fun setPrefStrSubject(parameter: String) =
        preferences.edit()?.putString(PREF_STR_SUBJECT, parameter)?.apply()

    fun getPrefBoolOnAgreement(): Boolean =
        preferences.getBoolean(PREF_BOOL_ON_AGREEMENT, false)

    fun setPrefBoolOnAgreement(parameter: Boolean) =
        preferences.edit()?.putBoolean(PREF_BOOL_ON_AGREEMENT, parameter)?.apply()

    companion object {
        private const val FILE_STR_SHARED_PREFERENCES = "TASK_PENGUIN"
        private const val PREF_STR_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val PREF_STR_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val PREF_STR_TITLE = "TITLE_KEY"
        private const val PREF_STR_SUBJECT = "SUBJECT_KEY"
        private const val PREF_BOOL_ON_AGREEMENT = "ON_AGREEMENT"
    }
}