package com.agreement.penguinsAgreement.contract

interface Contract {
    interface ViewMain {
        fun initValue()
        fun checkButton()
        fun updateAgreement()
        fun updatePlPinguins(text: String)
        fun updatePlDays(text: String)
    }

    interface PresenterMain {
        fun makeAgreement(text1: String, text2: String, numberPenguins: String, numberDays: String)
        fun makePlural(number: String, plurals: Int)
        fun getAgreement(): String
    }

    interface ModelAgreement {
        fun getAgreement(): String
        fun makeAgreement(
            text1: String,
            text2: String,
            number1: String,
            number2: String,
        )
    }
}