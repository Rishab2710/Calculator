package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{


        TextView resultTv,solutionTv;
        Button  buttonPie, buttonMC,buttonMR,buttonMplus,buttonMminus;
        Button buttonC, buttonAC, buttonEXP,buttonDivide;
        Button button7, button8,button9,buttonMultiply;
        Button button4, button5, button6, buttonSubtract;
        Button button1, button2, button3, buttonAdd;
        Button button0, buttonDecimal, buttonPercent, buttonEquals;


        boolean operator=false;
        double memory = 0.0;

        private void initialize(){

                try{

                        setContentView(R.layout.activity_main);
                        resultTv = findViewById(R.id.result_txtview);
                        solutionTv = findViewById(R.id.solution_txtview);

                        buttonC = findViewById(R.id.button_c);
                        buttonAC = findViewById(R.id.button_AC);
                        buttonPercent = findViewById(R.id.button_percentage);
                        buttonDecimal = findViewById(R.id.button_decimal);
                        buttonDivide = findViewById(R.id.button_divide);
                        buttonMultiply = findViewById(R.id.button_multiply);
                        buttonAdd = findViewById(R.id.button_addition);
                        buttonSubtract = findViewById(R.id.button_subtract);
                        buttonEquals = findViewById(R.id.button_equals);

                        button0 = findViewById(R.id.button_0);
                        button1 = findViewById(R.id.button_1);
                        button2 = findViewById(R.id.button_2);
                        button3 = findViewById(R.id.button_3);
                        button4 = findViewById(R.id.button_4);
                        button5 = findViewById(R.id.button_5);
                        button6 = findViewById(R.id.button_6);
                        button7 = findViewById(R.id.button_7);
                        button8 = findViewById(R.id.button_8);
                        button9 = findViewById(R.id.button_9);
                        buttonPie = findViewById(R.id.button_pie);

                        buttonMplus = findViewById(R.id.button_M_plus);
                        buttonMminus = findViewById(R.id.button_M_minus);
                        buttonMR = findViewById(R.id.button_MR);
                        buttonMC = findViewById(R.id.button_MC);
                        buttonEXP = findViewById(R.id.button_exponent);
                }
                catch (Exception e){
                        e.printStackTrace();
                }
        }


        @Override
        protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                onConfigurationChanged(getResources().getConfiguration());
                initialize();
                eventListners();
        }

        private void eventListners(){
                buttonAC.setOnClickListener(view ->{
                        operator = false;
                        resultTv.setText("");
                        solutionTv.setText("");
                } );

                buttonC.setOnClickListener(view -> {
                        try{
                                Double.parseDouble(resultTv.getText().toString());
                                String currentString = resultTv.getText().toString();
                                String modifiedString = currentString.substring(1);
                                resultTv.setText(modifiedString);
                                String previous = solutionTv.getText().toString().substring(1);
                                solutionTv.setText(previous);
                        }
                        catch (Exception e){
                                resultTv.setText("");
                                solutionTv.setText("");
                        }
                });
                buttonPercent.setOnClickListener(view -> {
                        try {
                                String stringPrevious = solutionTv.getText().toString();

                                solutionTv.setText(stringPrevious + "%");
                        }
                        catch(Exception e){

                        }
                });
                buttonDivide.setOnClickListener(view -> {
                        try {
                                operator = true;
                                String stringPrevious = solutionTv.getText().toString();
                                solutionTv.setText(stringPrevious + " / ");
                        }
                        catch(Exception e){

                        }
                });
                buttonMultiply.setOnClickListener(view -> {
                        try {
                                operator = true;
                                String stringPrevious = solutionTv.getText().toString();
                                solutionTv.setText(stringPrevious + " * ");
                        }
                        catch(Exception e){

                        }
                });
                buttonAdd.setOnClickListener(view -> {
                        try {
                                operator = true;
                                String stringPrevious = solutionTv.getText().toString();
                                solutionTv.setText(stringPrevious + " + ");
                        }
                        catch(Exception e){

                        }
                });
                buttonSubtract.setOnClickListener(view -> {
                        try {
                                operator = true;
                                String stringPrevious = solutionTv.getText().toString();
                                solutionTv.setText(stringPrevious + " - ");
                        }
                        catch(Exception e){

                        }
                });

                buttonEXP.setOnClickListener(view -> {
                        try {
                                operator = true;
                                String stringPrevious = solutionTv.getText().toString();
                                solutionTv.setText("e^("+stringPrevious+")");
                        }
                        catch(Exception e){

                        }
                });

                buttonPie.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "\u03C0";
                        solutionTv.setText(stringPrevious);
                        if (operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                                resultTv.setText("\u03C0");
                        else {
                                str = str + "\u03C0";
                                resultTv.setText(str);
                        }
                });

                buttonDecimal.setOnClickListener(view -> {
                        String str = resultTv.getText().toString();
                        String stringPrevious = solutionTv.getText().toString();
                        if(str.contains(".")){}
                        else {
                                stringPrevious = stringPrevious + ".";
                                solutionTv.setText(stringPrevious);
                                if(str.equals("")) {
                                        resultTv.setText("0.");
                                }
                                else {
                                        str = str + ".";
                                        resultTv.setText(str);
                                }
                        }
                });

                buttonMplus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                try{
                                        if(resultTv.getText().toString().equals("\u03C0")) {
                                                memory+=Math.PI;
                                        }
                                        else
                                        {
                                                Double currentValue = Double.parseDouble(resultTv.getText().toString());
                                                memory += currentValue;
                                        }
                                }
                                catch(Exception e)
                                {
                                        System.out.println("No decimal found");
                                }
                        }
                });

                buttonMminus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                try{
                                        Double currentValue = Double.parseDouble(resultTv.getText().toString());
                                        memory -= currentValue;
                                }
                                catch(Exception e)
                                {
                                        System.out.println("No decimal found");
                                }
                        }
                });

                buttonMR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String stringPrevious = solutionTv.getText().toString();
                                if(memory==Math.PI)
                                {
                                        solutionTv.setText(stringPrevious + " \u03C0");
                                        resultTv.setText("\u03C0" + "");
                                }
                                else {
                                        solutionTv.setText(stringPrevious + " " + memory);
                                        resultTv.setText(memory + "");
                                }
                        }
                });

                buttonMC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                memory = 0.0;
                        }
                });

                buttonEquals.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                if(resultTv.getText().toString().equals(""))
                                {
                                        resultTv.setText("");
                                }
                                else {
                                        CalculatorLogic solver = new CalculatorLogic(solutionTv.getText().toString());
                                        resultTv.setText(solver.evaluate()+"");
                                }
                        }
                });

                button0.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "0";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();
                        if(str.equals(""))
                        {
                                resultTv.setText("0");
                        }
                        else {
                                str = str + "0";
                                resultTv.setText(str);
                        }
                });
                button1.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "1";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                        {
                                resultTv.setText("");operator=false;
                        }

                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("1");
                        }
                        else {
                                str = str + "1";
                                resultTv.setText(str);
                        }
                });
                button2.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "2";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                        {
                                resultTv.setText("");operator=false;
                        }

                        String str = resultTv.getText().toString();
                        if(str.equals(""))
                        {
                                resultTv.setText("2");

                        }
                        else {
                                str = str + "2";
                                resultTv.setText(str);
                        }
                });
                button3.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "3";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("3");
                        }
                        else {
                                str = str + "3";
                                resultTv.setText(str);

                        }
                });
                button4.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "4";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("4");

                        }
                        else {
                                str = str + "4";
                                resultTv.setText(str);

                        }
                });
                button5.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "5";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;

                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("5");

                        }
                        else {
                                str = str + "5";
                                resultTv.setText(str);

                        }
                });
                button6.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "6";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;

                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("6");

                        }
                        else {
                                str = str + "6";
                                resultTv.setText(str);

                        }
                });
                button7.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "7";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("7");

                        }
                        else {
                                str = str + "7";
                                resultTv.setText(str);

                        }
                });
                button8.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "8";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("8");

                        }
                        else {
                                str = str + "8";
                                resultTv.setText(str);

                        }
                });
                button9.setOnClickListener(view -> {
                        String stringPrevious = solutionTv.getText().toString();
                        stringPrevious = stringPrevious + "9";
                        solutionTv.setText(stringPrevious);
                        if(operator)
                                resultTv.setText("");operator=false;
                        String str = resultTv.getText().toString();

                        if(str.equals(""))
                        {
                                resultTv.setText("9");
                        }
                        else {
                                str = str + "9";
                                resultTv.setText(str);
                        }
                });

        }

}
