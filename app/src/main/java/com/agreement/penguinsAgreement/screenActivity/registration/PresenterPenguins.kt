package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.res.Resources
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.utils.PreferenceUtil

class PresenterPenguins : ContractRegistration.Presenter {
    private lateinit var viewPenguin: ContractRegistration.View

    override fun initPresenter(_viewPenguin: ContractRegistration.View) {
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

    override fun onDaysNumberTextChange(numberDays: String) {
        ModelPenguins.setDaysNumber(numberDays)
        viewPenguin.updateDays(ModelPenguins.getPluralDays())
        viewPenguin.updateAgreement(ModelPenguins.getAgreement())
    }

    override fun onPenguinsNumberTextChange(numberPenguins: String) {
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
            viewPenguin.navigateToAgreement()
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

    override fun checkLeadingActivity() {
        if (ModelPenguins.getBoolOnAgreement()) {
            viewPenguin.navigateToAgreement()
        }
    }
}