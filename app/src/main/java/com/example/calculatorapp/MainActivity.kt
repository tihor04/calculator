package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newnumber:EditText

    private val displayOperation by lazy(LazyThreadSafetyMode.NONE){findViewById<TextView>(R.id.textView)}


    //variale to use the operands and type of calculations

    private  var oprand1:Double?=null
    private var oprand2:Double=0.0
    private var pendingoperation="="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result=findViewById(R.id.result)
        newnumber=findViewById(R.id.edit2)

        //data input buttons

        val button1:Button=findViewById(R.id.one)
        val button2:Button=findViewById(R.id.two)
        val button3:Button=findViewById(R.id.three)
        val button4:Button=findViewById(R.id.four)
        val button5:Button=findViewById(R.id.five)
        val button6:Button=findViewById(R.id.six)
        val button7:Button=findViewById(R.id.seven)
        val button8:Button=findViewById(R.id.eight)
        val button9:Button=findViewById(R.id.nine)
        val button0:Button=findViewById(R.id.zero)


        //operational buttons


        val buttondecimal:Button=findViewById(R.id.decimal)
        val buttonplus:Button=findViewById(R.id.add)
        val buttonminus:Button=findViewById(R.id.minus)
        val buttonmultiply:Button=findViewById(R.id.multiply)
        val buttondivide:Button=findViewById(R.id.divide)
        val buttonequals:Button=findViewById(R.id.equals)


        val listner= View.OnClickListener { v->
            val b= v as Button
            newnumber.append(b.text)
        }
       buttondecimal.setOnClickListener(listner)
        button0.setOnClickListener(listner)
        button1.setOnClickListener(listner)
        button2.setOnClickListener(listner)
        button3.setOnClickListener(listner)
        button4.setOnClickListener(listner)
        button5.setOnClickListener(listner)
        button6.setOnClickListener(listner)
        button7.setOnClickListener(listner)
        button8.setOnClickListener(listner)
        button9.setOnClickListener(listner)


        val opListner = View.OnClickListener { v->
            val op=(v as Button).text.toString()
            try {
                val value=newnumber.text.toString().toDouble()
                performOperartion(value,op)
            }catch (e:NumberFormatException){
                newnumber.setText("")
            }
            pendingoperation=op
            displayOperation.text=pendingoperation
        }

        buttonplus.setOnClickListener(opListner)
        buttonminus.setOnClickListener(opListner)
        buttonmultiply.setOnClickListener(opListner)
        buttondivide.setOnClickListener(opListner)
        buttonequals.setOnClickListener(opListner)

        val clearbutton:Button=findViewById(R.id.clearButton)

        val clearopration=View.OnClickListener { v->
            oprand1=null
            oprand2 = 0.0
            pendingoperation="="
            result.setText("")
            newnumber.setText("")
            displayOperation.setText("")
        }
        clearbutton.setOnClickListener(clearopration)













    }

   private fun performOperartion(value: Double,op: String){
       if(oprand1==null){
           oprand1=value

       }
       else
       {
           oprand2=value

           if(pendingoperation=="=")
           {
               pendingoperation=op
           }
           else
           {
               when(pendingoperation)
               {
                   "="->oprand1=oprand2
                    "/"->if(oprand2==0.0)
                        {
                           oprand1=Double.NaN //handling the divided by zero case
                        }
                        else {
                         oprand1=oprand1!!/oprand2
                      }
                   "*"->oprand1=oprand1!! *oprand2
                   "-"->oprand1=oprand1!!-oprand2
                   "+"->oprand1=oprand1!!+oprand2


               }
           }
       }
       result.setText(oprand1.toString())
       newnumber.setText("")
   }
}