package com.agreement.penguinsAgreement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsView.ViewPenguins
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewPenguins: ViewPenguins

    private val model: ModelPenguins = ModelPenguins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.initModelPinguins(ActivityMainBinding.inflate(layoutInflater),getSharedPreferences(STR_FILE_SHARED_PREFERENCES, MODE_PRIVATE), resources)
        setContentView(model.getBinding()?.root)

        viewPenguins = ViewPenguins()

        viewPenguins.initView()
        viewPenguins.initListeners()
    }

    override fun onPause() {
        super.onPause()
        viewPenguins.onPauseViewPenguins()
    }

    companion object {
        private const val STR_FILE_SHARED_PREFERENCES = "TASK_PENGUIN"
    }
}