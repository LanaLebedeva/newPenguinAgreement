package com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter

import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins

object PresenterPenguins : PenguinsContract.PenguinPresenter {
    override fun makePlural(numberToString: String, plurals: Int) {
        ModelPenguins.makePlural(numberToString, plurals)
    }

    override fun makeAgreement() {
        ModelPenguins.makeAgreement()
    }
}