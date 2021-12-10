package com.agreement.penguinsAgreement.screenActivity.agreement

interface ContractAgreement {

    interface Presenter {
        fun initAgreement()
        fun onButtonDisagreeClick()
        fun onButtonAgreeClick()
        fun onBackPressedClick()
    }

    interface View {
        fun updateAgreement(agreement: String)
        fun navigateToRegistration()
    }
}