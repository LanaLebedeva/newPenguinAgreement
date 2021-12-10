package com.agreement.penguinsAgreement.screenActivity.agreement

interface PresenterAgreementI {
    fun initAgreement()
    fun onBtnDisagreeClick()
    fun onBackPressedClick()
}

interface AgreementActivityI {
    fun updateAgreement(agreement: String)
    fun navigateToRegistration()
}