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
            updateTitle(ModelPenguins.getTitle())
            updateSubject(ModelPenguins.getSubject())
            updateNumberPenguins(ModelPenguins.getNumberPenguins())
            updateNumberDays(ModelPenguins.getNumberDays())
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
        with(ModelPenguins) {
            setTitle(title)
            setSubject(subject)
            setPenguinsNumber(penguinsNumber)
            setDaysNumber(daysNumber)
        }
    }

    override fun onDaysNumberTextViewChange(numberDays: String) {
        ModelPenguins.setDaysNumber(numberDays)
        viewPenguin.updateDays(ModelPenguins.getPluralDays())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    override fun onPenguinsNumberTextViewChange(numberPenguins: String) {
        ModelPenguins.setPenguinsNumber(numberPenguins)
        viewPenguin.updatePenguins(ModelPenguins.getPluralPenguins())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    override fun onFormAgreementChange() {
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    override fun onFormAgreementClick() {
        onFormAgreementChange()
        if (!ModelPenguins.checkUpdateAgreement()) {
            viewPenguin.setSnackbarAgreement()
        }
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    override fun onConfirmItClick() {
        onFormAgreementChange()
        if (ModelPenguins.getAgreement() != ModelPenguins.getResources()
                .getString(R.string.text_there_will_be_an_agreement)
        ) {
            ModelPenguins.setBoolOnAgreement(true)
            viewPenguin.startAgreement()
        } else {
            viewPenguin.setSnackbarAgreement()
        }
    }

    override fun onTitleTextViewChange(title: String) {
        ModelPenguins.setTitle(title)
    }

    override fun onSubjectTextViewChange(subject: String) {
        ModelPenguins.setSubject(subject)
    }

    override fun checkLeadingActivity(preferences: PreferenceUtil, resources: Resources) {
        ModelPenguins.initPreferenceResources(preferences, resources)
        if (ModelPenguins.getBoolOnAgreement()) {
            viewPenguin.startAgreement()
        }
    }
}