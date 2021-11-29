package com.agreement.penguinsAgreement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsView.ViewPenguins
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewPenguins: ViewPenguins

    private var model: ModelPenguins = ModelPenguins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        model.initPreferenceResources(
            getSharedPreferences(FILE_STR_SHARED_PREFERENCES, MODE_PRIVATE),
            resources
        )
        setContentView(binding.root)
        viewPenguins = ViewPenguins(binding)
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
        viewPenguins.onSaveViewPenguins()
    }

    companion object {
        private const val FILE_STR_SHARED_PREFERENCES = "TASK_PENGUIN"
    }
}