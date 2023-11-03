package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Old way with findViewById()
        binding.calculateBtn.setOnClickListener{calculateTip()}


}
    private fun calculateTip(){
        val costStr = binding.costServiceInput.text.toString()
        val cost = costStr.toDoubleOrNull()
        if(cost!= null) {
            var tip = cost * getSelectedId()
            val roundUp = binding.roundUpSw.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.resultTip.text = getString(R.string.tip_amount, formattedTip)
        }
        else{
            binding.resultTip.text = ""
        }


    }

    private fun getSelectedId():Double {
        val tipPercentage = when(binding.tipOption.checkedRadioButtonId){
            R.id.option_amazing_20 -> 0.20
            R.id.option_good_18 -> 0.18
            else -> 0.15
        }
        return tipPercentage
    }
}