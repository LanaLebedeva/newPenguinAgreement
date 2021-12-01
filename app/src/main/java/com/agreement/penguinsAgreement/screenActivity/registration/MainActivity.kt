package com.agreement.penguinsAgreement.screenActivity.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.utils.PreferenceUtil

class MainActivity : AppCompatActivity() {

    private lateinit var viewPenguins: ViewPenguins

    private var model: ModelPenguins = ModelPenguins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val preferences: PreferenceUtil = PreferenceUtil(this)

        model.initPreferenceResources(preferences, resources)
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
        viewPenguins.saveViewPenguins()
    }
}