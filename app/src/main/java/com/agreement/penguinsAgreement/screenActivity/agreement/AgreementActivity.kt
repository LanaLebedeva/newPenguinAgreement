package com.agreement.penguinsAgreement.screenActivity.agreement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.screenActivity.registration.MainActivity

//private lateinit var bindingAgreement: ActivityAgreementBinding

class AgreementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agreement)
//        bindingAgreement = ActivityAgreementBinding.inflate(layoutInflater)
//        bindingAgreement.tvAgreementText.text = ModelPenguins.getAgreement()

        val textAgreement: TextView = findViewById(R.id.tv_agreementText)
        textAgreement.text = ModelPenguins.getAgreement()
        initListeners()
    }
    private fun initListeners() {

        findViewById<Button>(R.id.btn_agree).setOnClickListener {
        }
        findViewById<Button>(R.id.btn_disagree).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}