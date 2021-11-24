package com.agreement.penguinsAgreement

import android.content.SharedPreferences
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
        model.setBinding(ActivityMainBinding.inflate(layoutInflater))
        setContentView(model.binding?.root)
        model.setPreference(getSharedPreferences(STR_FILE_SHARED_PREFERENCES, MODE_PRIVATE))
        model.setResources(resources)

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