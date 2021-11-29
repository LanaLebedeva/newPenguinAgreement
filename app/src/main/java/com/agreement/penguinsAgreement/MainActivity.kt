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
        model.initModelPinguins(ActivityMainBinding.inflate(layoutInflater),getSharedPreferences(FILE_STR_SHARED_PREFERENCES, MODE_PRIVATE), resources)
        setContentView(model.getBinding()?.root)

        viewPenguins = ViewPenguins()

        if (savedInstanceState == null) {
            viewPenguins.initView()
        }
        viewPenguins.initListeners()
    }

    override fun onStart() {
        super.onStart()
        viewPenguins.initView()
    }

    override fun onStop() {
        super.onStop()
        viewPenguins.onStopViewPenguins()
    }

    companion object {
        private const val FILE_STR_SHARED_PREFERENCES = "TASK_PENGUIN"
    }
}