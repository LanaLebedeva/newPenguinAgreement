package com.agreement.penguinsAgreement.screenActivity.reconfirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.databinding.ActivityReconfirmationBinding
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins

class ReconfirmationActivity : AppCompatActivity() {
    private lateinit var bindingAgree: ActivityReconfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reconfirmation)
        bindingAgree =
            ActivityReconfirmationBinding.inflate(getLayoutInflater())
//        val text: TextView = findViewById(R.id.tv_agreement_agree)
//        text.text = ModelPenguins.getAgreement()
        bindingAgree.tvAgreementAgree.text = "GDGFDG" //= ModelPenguins.getAgreement()
    }
}