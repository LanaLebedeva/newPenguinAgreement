package com.agreement.penguinsAgreement.screenActivity.agreement

import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins

class PresenterAgreement(val viewAgreement: AgreementActivity) : PresenterAgreementI {
    override fun initAgreement() {
        viewAgreement.updateAgreement(ModelPenguins.getAgreement())
    }

    override fun onBtnDisagreeClick() {
        ModelPenguins.setBoolOnAgreement(false)
        viewAgreement.startRegistration()
    }

    override fun onBackPressedClick() {
        ModelPenguins.setBoolOnAgreement(false)
    }

}