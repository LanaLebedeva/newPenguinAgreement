package com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter

import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins

object PresenterPenguins
 {
    fun makePlural(numberToString: String, plurals: Int) {
        ModelPenguins.makePlural(numberToString, plurals)
    }

    fun makeAgreement() {
        ModelPenguins.makeAgreement()
    }
}