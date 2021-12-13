package com.agreement.penguinsAgreement.screenActivity.registration

import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins

class PresenterPenguins : ContractRegistration.Presenter {
    private lateinit var viewPenguin: ContractRegistration.View
    private val model: ModelPenguins = ModelPenguins

    override fun initPresenter(_viewPenguin: ContractRegistration.View) {
        viewPenguin = _viewPenguin
    }

    override fun initView() {
        checkLeadingActivity()
        with(viewPenguin) {
            updateTitle(model.getTitle())
            updateSubject(model.getSubject())
            updateNumberPenguins(model.getNumberPenguins())
            updateNumberDays(model.getNumberDays())
            updatePenguins(model.getPluralPenguins())
            updateDays(model.getPluralDays())
            updateAgreement(model.getAgreement())
        }
    }

    override fun saveViewPenguins(
        title: String,
        subject: String,
        penguinsNumber: String,
        daysNumber: String
    ) {
        with(model) {
            setTitle(title)
            setSubject(subject)
            setPenguinsNumber(penguinsNumber)
            setDaysNumber(daysNumber)
        }
    }

    override fun onDaysNumberTextChange(numberDays: String) {
        model.setDaysNumber(numberDays)
        viewPenguin.updateDays(model.getPluralDays())
        viewPenguin.updateAgreement(model.getAgreement())
    }

    override fun onPenguinsNumberTextChange(numberPenguins: String) {
        model.setPenguinsNumber(numberPenguins)
        viewPenguin.updatePenguins(model.getPluralPenguins())
        viewPenguin.updateAgreement(model.getAgreement())
    }

    override fun onFormAgreementChange() {
        viewPenguin.updateAgreement(model.getAgreement())
    }

    override fun onFormAgreementButtonClick() {
        onFormAgreementChange()
        if (!model.checkUpdateAgreement()) {
            viewPenguin.setSnackbarAgreement()
        }
        viewPenguin.updateAgreement(model.getAgreement())
    }

    override fun onConfirmItButtonClick() {
        onFormAgreementChange()
        if (model.getAgreement() != model.getResources()
                .getString(R.string.text_there_will_be_an_agreement)
        ) {
            model.setBoolOnAgreement(true)
            viewPenguin.navigateToAgreement()
        } else {
            viewPenguin.setSnackbarAgreement()
        }
    }

    override fun onTitleTextChange(title: String) {
        model.setTitle(title)
    }

    override fun onSubjectTextChange(subject: String) {
        model.setSubject(subject)
    }

    private fun checkLeadingActivity() {
        if (model.getBoolOnAgreement()) {
            viewPenguin.navigateToAgreement()
        }
    }
}