package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.res.Resources
import com.agreement.penguinsAgreement.utils.PreferenceUtil

interface ContractRegistration {
    interface View {
        fun updateAgreement(agreement: String)
        fun updateTitle(title: String)
        fun updateSubject(subject: String)
        fun updateNumberPenguins(numberPenguins: String)
        fun updatePenguins(pluralsNumber: String)
        fun updateNumberDays(numberDays: String)
        fun updateDays(pluralsNumber: String)
        fun setSnackbarAgreement()
        fun navigateToAgreement()
    }

    interface Presenter {
        fun initPresenter(_viewPenguin: View)
        fun initView()
        fun saveViewPenguins(
            title: String,
            subject: String,
            penguinsNumber: String,
            daysNumber: String
        )

        fun onDaysNumberTextChange(numberDays: String)
        fun onPenguinsNumberTextChange(numberPenguins: String)
        fun onFormAgreementChange()
        fun onFormAgreementClick()
        fun onConfirmItClick()
        fun onTitleTextViewChange(title: String)
        fun onSubjectTextViewChange(subject: String)
        fun checkLeadingActivity()
    }
}