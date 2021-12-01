package com.agreement.penguinsAgreement

import android.app.Application
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins

class AppPenguin: Application() {
    private val model: ModelPenguins = ModelPenguins
}