package com.agreement.penguinsAgreement.screenActivity.agreement

import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins

class PresenterAgreement(private val view: ContractAgreement.View) : ContractAgreement.Presenter {
    private val model = ModelPenguins

    override fun initAgreement() {
        view.updateAgreement(model.getAgreement())
    }

    override fun onButtonDisagreeClick() {
        model.setBoolOnAgreement(false)
        view.navigateToRegistration()
    }

    override fun onBackPressedClick() {
        model.setBoolOnAgreement(false)
    }

    override fun onButtonAgreeClick() {
        TODO("Not yet implemented")
    }

}