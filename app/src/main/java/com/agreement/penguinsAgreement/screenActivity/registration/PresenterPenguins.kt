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

    override fun onDaysNumberTextViewChange(numberDays: String): String {
        ModelPenguins.setDaysNumber(numberDays)
        viewPenguin.updateDays(ModelPenguins.getPluralDays())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
        return ModelPenguins.getNumberDays()
    }

    override fun onPenguinsNumberTextViewChange(numberPenguins: String): String {
        ModelPenguins.setPenguinsNumber(numberPenguins)
        viewPenguin.updatePenguins(ModelPenguins.getPluralPenguins())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
        return ModelPenguins.getNumberPenguins()
    }

    override fun onFormAgreementChange(): String {
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
        return ModelPenguins.getAgreement()
    }

    override fun onFormAgreementClick(): String {
        onFormAgreementChange()
        if (!ModelPenguins.checkUpdateAgreement()) {
            viewPenguin.setSnackbarAgreement()
        }
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
        return ModelPenguins.getAgreement()
    }

    override fun onConfirmItClick(): String {
        onFormAgreementChange()
        if (ModelPenguins.getAgreement() != ModelPenguins.getResources()
                .getString(R.string.text_there_will_be_an_agreement)
        ) {
            ModelPenguins.setBoolOnAgreement(true)
            viewPenguin.startAgreement()
        } else {
            viewPenguin.setSnackbarAgreement()
        }
        return ModelPenguins.getAgreement()
    }

    override fun onTitleTextViewChange(title: String): String {
        ModelPenguins.setTitle(title)
        return ModelPenguins.getTitle()
    }

    override fun onSubjectTextViewChange(subject: String): String {
        ModelPenguins.setSubject(subject)
        return ModelPenguins.getSubject()
    }

    override fun checkLeadingActivity() {
        if (ModelPenguins.getBoolOnAgreement()) {
            viewPenguin.startAgreement()
        }

    }

    override fun initModelPenguins(preferences: PreferenceUtil, resources: Resources) {
        ModelPenguins.initPreferenceResources(preferences, resources)
    }
}