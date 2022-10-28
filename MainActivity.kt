package com.example.clickersim

// All imports
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clickersim.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    // Setting up variables
    private lateinit var binding: ActivityMainBinding
    public var cupcakes :Double  = 1000000.0;
    public var cps: Double = 0.0;
    public var cpclicks: Double = 1.0;
    public var clickCost: Double = 100.0;
    public var plusOnes: Int = 0;
    public var oneCost: Double = 10.0;
    public var plusHun: Int = 0;
    public var hunCost: Double = 2500.0;
    public var plusThousand: Int = 0;
    public var thosandCost: Double = 15000.0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)
        // Main button for clicking
        binding.button.setOnClickListener{
            cupcakes += cpclicks
        }

        //The Shop
        // Plus 1
        binding.button2.setOnClickListener{
            if (updateCPS(1.0, oneCost))
            {
                //Increase cost and round to just two decimal
                oneCost *= 1.15
                oneCost = (oneCost * 100.0).roundToInt() / 100.0
                plusOnes++
            }
        }

        //Plus Hundred
        binding.button5.setOnClickListener{
            if (updateCPS(100.0, hunCost))
            {
                //Increase cost and round to just two decimal
                hunCost *= 1.15
                hunCost = (hunCost * 100.0).roundToInt() / 100.0
                plusHun++
            }
        }

        //Plus Thousand
        binding.button6.setOnClickListener{
            if (updateCPS(1000.0, thosandCost))
            {
                //Increase cost and round to just two decimal
                thosandCost *= 1.15
                thosandCost = (thosandCost * 100.0).roundToInt() / 100.0
                plusThousand++
            }
        }

        // Times two on clicker
        binding.button4.setOnClickListener{
            if (cupcakes >= clickCost)
            {
                cupcakes -= clickCost
                cpclicks *= 2
                clickCost *= 3
            }
        }

        // Update display every 10th of a second
        Timer().scheduleAtFixedRate(0, 100) {
            updateDisplay()
        }

        // Add the CPS every second to total count
        Timer().scheduleAtFixedRate(0, 1000) {
            cupcakes +=  cps
        }
    }

    // Refresh all display text
    fun updateDisplay(){
        runOnUiThread { binding.button.text = "+ $cpclicks" }
        runOnUiThread {  binding.MainCounter.text = cupcakes.toString()}
        runOnUiThread {  binding.cpsDisplay.text = cps.toString()}
        runOnUiThread {  binding.OneNum.text = plusOnes.toString()}
        runOnUiThread {  binding.OneCost.text = "Cost: $oneCost"}
        runOnUiThread {  binding.HunNum.text = plusHun.toString()}
        runOnUiThread {  binding.HunCost.text = "Cost: $hunCost"}
        runOnUiThread {  binding.ThousandNum.text = plusThousand.toString()}
        runOnUiThread {  binding.ThousandCost.text = "Cost: $thosandCost"}
        runOnUiThread {  binding.clickCost.text = "Cost: $clickCost" }
    }

    // Checks if total is high enough for cost, then removes cost from total 
    fun updateCPS(increase :Double, cost :Double): Boolean{
        if (cupcakes >= cost)
        {
            cupcakes -= cost
            cupcakes = (cupcakes * 100.0).roundToInt() / 100.0
            cps += increase
            return true
        }
        return false
    }
}