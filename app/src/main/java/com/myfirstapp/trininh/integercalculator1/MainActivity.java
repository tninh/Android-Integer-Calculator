package com.myfirstapp.trininh.integercalculator1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import android.util.*;


public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String str, str1, str2, result, sign, tempStr;
    private int a, b, resultInt, temp, count;
    //boolean lastNumeric;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.textview);
        str = "";
        str1="";
        tempStr = "";
        sign = "";
        //lastNumeric = false;
    }
    public void onClick(View v){
        Button btn = (Button) v;

            if ((tempStr.startsWith("0") && btn.getText().toString().equals("0") && !str1.isEmpty())|| (tempStr.startsWith("0") && !btn.getText().toString().equals("0") && !str1.isEmpty())) {
                str = btn.getText().toString();

                Log.d("This is tempStr0", tempStr);
                Log.d("This is str", str);
                Log.d("This is str1", str1);
                tempStr  = str;
                str1 = str1 + tempStr;
                screen.setText(str);
            }
            else {

                str = btn.getText().toString();
                tempStr += str;
                Log.d("This is tempStr", tempStr);
                str1 += str;
                Log.d("This is str", str);
                Log.d("This is str1", str1);
                screen.setText(tempStr);
            }

            if((!sign.isEmpty()) && (sign.equals("+") || sign.equals("-")))
            {
                a = Integer.parseInt(sign + str);
                Log.d("This is a", Integer.toString(a));
            }

            else {
                a = Integer.parseInt(tempStr);
                Log.d("This is a", Integer.toString(a));
            }




    }
    public void onClickSigns(View v){

        Button btn = (Button) v;
            if(str1.endsWith("+") || str1.endsWith("-") || str1.endsWith("*") || str1.endsWith("/")) {
                Log.d("This is str1:", str1);
                if(str1.length() == 1)
                {
                    str1 = btn.getText().toString();
                }
                else {
                    str1 = str1.substring(0, str1.length() - 1) + btn.getText().toString();
                    //str1 = str1 + btn.getText().toString();
                }
                //sign = btn.getText().toString();
                //str1 += sign;
                Log.d("This is str1_0:", str1);
                sign = btn.getText().toString();
                str = "";
                tempStr = "";
                count += 1;
                temp = a;
            }else
            {
                sign = btn.getText().toString();
                Log.d("This is sign", sign);
                str1 += sign;

                Log.d("This is str1:", str1);
                str = "";
                tempStr = "";
                count += 1;
                temp = a;
                Log.d("This is temp", Integer.toString(temp));
            }

    }
    public void onClickClear(View v){
        Button btn = (Button) v;
        screen.setText("");
        str = "";
        sign = "";
        str1 = "";
        tempStr = "";
        temp = 0;
        Log.d("This is count_clear", Integer.toString(count));
        count = 0;
        a = 0;
        //temp = 0;
        //temp = a;
    }

    public void calculate(View v){
        Button btn = (Button) v;
        Log.d("This is str1:", str1);
        //str1 += "=";
        onEqual();

    }
    private void onEqual()
    {
        //Integer.toString(count);
        if (count == 1 || (count !=1 && (str1.startsWith("+") || str1.startsWith("-")))) {
            str2 = screen.getText().toString();
            b = Integer.parseInt(str2);
            try {
                if (sign.equals("+")) {
                    Log.d("This is temp", Integer.toString(temp));
                    resultInt = temp + b;
                    result = Integer.toString(resultInt);
                } else if (sign.equals("-")) {
                    resultInt = temp - b;
                    result = Integer.toString(resultInt);
                    Log.d("This is result", result);
                } else if (sign.equals("*")) {
                    resultInt = temp * b;
                    result = Integer.toString(resultInt);
                } else if (sign.equals("/")) {
                    Log.d("This is temp", Integer.toString(temp));
                    resultInt = Math.round((float)temp / (float)b);
                    result = Integer.toString(resultInt);
                    Log.d("This is resultInt", result);
                    Log.d("This is count", Integer.toString(count));
                } else {
                    result = "Error";
                }
                }catch (NullPointerException e) {
                screen.setText("Error");
            }
            count = 0;
        }
        else {
            //str1 += screen.getText().toString();
            Log.d("This is the real str1", str1);
            Log.d("This is count", Integer.toString(count));
            try {
                Expression expression = new ExpressionBuilder(str1).build();

                resultInt = (int) expression.evaluate();
                result = Integer.toString(resultInt);
                screen.setText(result);
            }catch (ArithmeticException e)
            {
                screen.setText("Error");
            }
            count = 0;
        }
        screen.setText(result);
    }
}
