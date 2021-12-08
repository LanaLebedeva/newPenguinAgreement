package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.res.Resources
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.utils.PreferenceUtil

class PresenterPenguins : PresenterPenguin {
    lateinit var viewPenguin: MainActivity

    override fun initPresenter(_viewPenguin: MainActivity) {
        viewPenguin = _viewPenguin
    }

    override fun initView() {
        with(viewPenguin) {
            updateTitle(ModelPenguins.getPreferences().getPrefStrTitle() ?: "")
            updateSubject(ModelPenguins.getPreferences().getPrefStrSubject() ?: "")
            updateNumberPenguins(ModelPenguins.getPreferences().getPrefStrNumberPenguins() ?: "")
            updateNumberDays(ModelPenguins.getPreferences().getPrefStrNumberDays() ?: "")
            updatePenguins(ModelPenguins.getPluralPenguins())
            updateDays(ModelPenguins.getPluralDays())
            updateAgreement(ModelPenguins.getAgreement())
        }
    }

    override fun saveViewPenguins(
        title: String,
        subject: String,
        penguinsNumber: String,
        daysNumber: String
    ) {
        with(ModelPenguins.getPreferences()) {
            setPrefStrTitle(title)
            setPrefStrSubject(subject)
            setPrefStrNumberPenguins(penguinsNumber)
            setPrefStrNumberDays(daysNumber)
        }
    }

    override fun onDaysNumberTextViewChange(numberDays: String) {
        ModelPenguins.updateDaysNumber(numberDays)
        viewPenguin.updateDays(ModelPenguins.getPluralDays())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    fun onPenguinsNumberTextViewChange(numberPenguins: String) {
        ModelPenguins.updatePenguinsNumber(numberPenguins)
        viewPenguin.updatePenguins(ModelPenguins.getPluralPenguins())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    private fun onFormAgreementChange() {
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    fun onFormAgreementClick() {
        onFormAgreementChange()
        if (!ModelPenguins.updateAgreement()) {
            viewPenguin.setSnackbarAgreement()
        }
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    fun onConfirmItClick() {
        onFormAgreementChange()
        if (ModelPenguins.getAgreement() != ModelPenguins.getResources()
                .getString(R.string.text_there_will_be_an_agreement)
        ) {
            ModelPenguins.getPreferences().setPrefBoolOnAgreement(true)
            viewPenguin.startAgreement()
        } else {
            viewPenguin.setSnackbarAgreement()
        }
    }

    fun onTitleTextViewChange(title: String) {
        ModelPenguins.getPreferences().setPrefStrTitle(title)
    }

    fun onSubjectTextViewChange(subject: String) {
        ModelPenguins.getPreferences().setPrefStrSubject(subject)
    }

    fun checkLeadingActivity(preferences: PreferenceUtil, resources: Resources) {
        ModelPenguins.initPreferenceResources(preferences, resources)
        if (ModelPenguins.getPreferences().getPrefBoolOnAgreement()) {
            viewPenguin.startAgreement()
        }
    }
}