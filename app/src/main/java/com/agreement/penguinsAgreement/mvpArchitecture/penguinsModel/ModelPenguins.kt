package com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel

import android.content.SharedPreferences
import android.content.res.Resources
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.penguinsBusiness.ModelBusiness

object ModelPenguins: PenguinsContract.PenguinModel {
    private lateinit var preferences: SharedPreferences
    private lateinit var resources: Resources
    private var modelBusiness = ModelBusiness()

    fun initPreferenceResources(_preferences: SharedPreferences, _resources: Resources) {
        preferences = _preferences
        resources = _resources
        modelBusiness.initModelBusiness(preferences, resources)
    }

    override fun getPreferences() = preferences

    override fun getResources() = resources

    override fun updateAgreement(): Boolean {
        return modelBusiness.updateAgreement()
    }

    override fun updatePlural(numberToString: String, plurals: Int): String {
        return modelBusiness.updatePlural(numberToString, plurals)
    }


}
