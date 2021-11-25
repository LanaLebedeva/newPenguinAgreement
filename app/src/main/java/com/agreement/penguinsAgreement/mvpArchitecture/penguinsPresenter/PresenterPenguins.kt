package com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter

import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsView.ViewPenguins

class PresenterPenguins(private val viewPenguin: ViewPenguins) : PenguinsContract.PenguinPresenter {
    //private val model: ModelPenguins = ModelPenguins

    override fun updatePluralNumberViews(numberToString: String, @PluralsRes plurals: Int): String? {
        val pluralsModel = ModelPenguins.updatePluralNumberViews(numberToString, plurals)
        when (plurals) {
            R.plurals.penguins -> viewPenguin.setPenguins(pluralsModel)
            R.plurals.days -> viewPenguin.setDays(pluralsModel)
        }
        return pluralsModel
    }

    override fun makeAgreement() {
        ModelPenguins.makeAgreement()
    }
}