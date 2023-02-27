package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.lab2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    fun calculate_res(a:String,b:String,c:String){
        val int_a = a.toIntOrNull()
        val int_b = b.toIntOrNull()
        val int_c = c.toIntOrNull()
        if (int_a !is Int || int_b !is Int || int_c !is Int){
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Ошибка").setMessage("Проверьте введенные данные!").setNeutralButton("OK", { dialog, whichButton ->
                dialog.dismiss()
            })
            dialogBuilder.show()
        }
        else {
            val discr = (int_b*int_b - 4 * int_a * int_c)
            getx(discr.toDouble(),int_a,int_b)

        }
    }

    fun getx(discr : Double, a:Int, b:Int){
        var rootx1 = (-b + kotlin.math.sqrt(discr)) / (2 * a)
        rootx1 = if (rootx1.isNaN() || rootx1 == Double.NEGATIVE_INFINITY || rootx1 == Double.POSITIVE_INFINITY) 0.0 else String.format("%.2f",rootx1).toDouble()
        var rootx2 = (-b - kotlin.math.sqrt(discr)) / (2 * a)
        rootx2 = if (rootx2.isNaN() || rootx2 == Double.NEGATIVE_INFINITY || rootx2 == Double.POSITIVE_INFINITY) 0.0 else String.format("%.2f",rootx2).toDouble()

        var final: String
        if (discr > 0){
            final = "x1 = $rootx1 || x2 = $rootx2"

        }
        else if (discr == 0.0){
            final = "x1 = x2 = $rootx1"
        }
        else {
            final = "Корней нет!"
        }
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Результат").setMessage(final).setNeutralButton("OK", { dialog, whichButton ->
            dialog.dismiss()
        })
        dialogBuilder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener(){

            val a = binding.editTextTextPersonName1.text

            val b = binding.editTextTextPersonName2.text
            val c = binding.editTextTextPersonName3.text
            calculate_res(a.toString(),b.toString(),c.toString())
        }
    }
}