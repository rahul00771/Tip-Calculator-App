package com.example.tipapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipapp.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding   //for view finding(for avoiding R.type.name repeatedly)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)                    //root means mainActivity

        binding.calButton.setOnClickListener {
            calculateTip()
        }

    }


    private fun calculateTip()              //for calculating the tip
    {

        val stringIn = binding.costOfService.text.toString()
        val doubleOut = stringIn.toDoubleOrNull()

        if(doubleOut==null)
        {
            binding.tipResult.text = " "
            return
        }

        val tipPercent = when(binding.tipOption.checkedRadioButtonId)
        {
            R.id.twenty -> 0.20
            R.id.fifteen -> 0.15
            else -> 0.10
        }

        var tip = doubleOut*tipPercent
        val round = binding.roundUp.isChecked
        if(round)
        {
            tip = ceil(tip)
        }

        val formattedTip = java.text.NumberFormat.getCurrencyInstance().format(tip)     //converting to currency format

        binding.tipResult.text = formattedTip.toString()


    }



}



