package com.example.calculator;

import java.math.*;

public class CalculatorLogic {

     String eqn;
     int indicator;
     char ch;


    public CalculatorLogic(String eqn) {
        super();
        setEqn(eqn);
    }



    private void nextInput() {
        if(++this.indicator < this.eqn.length()) {
            if(this.eqn.charAt(indicator)==' ') {
                nextInput();
            }
            else
            {
                this.ch = this.eqn.charAt(indicator);
            }
        }
        else
        {
            this.ch = (char)-1;
        }
    }


    private void initialPosition() {
        this.indicator = -1;
    }

    private boolean checkOpr(char charToCheck){
        if(this.ch == charToCheck) {
            nextInput();
            return true;
        }
        return false;
    }


    private double percentOperations(double val, double ans){
        System.out.println("% detected");
        ans = ans/100.0;
        ans = val * ans;
        return ans;
    }

    private double parseSingleFactor(double val) {
        if(checkOpr('+'))
        {
            return +parseSingleFactor(val);
        }
        if(checkOpr('-'))
        {
            return -parseSingleFactor(val);
        }
        double ans=0.0;
        int startingPosition = indicator;
        if((ch>='0' && ch<='9') || ch=='.' || ch=='%' || ch=='\u03C0')
        {
            /*numbers detected*/
            while((ch>='0' && ch<='9') || ch=='.')
            {
                nextInput();
            }
            if(ch!='\u03C0')
                ans = Double.parseDouble(eqn.substring(startingPosition, indicator));
            if(ch=='%')
            {
                ans=percentOperations(val,ans);
                nextInput();
            }
            if(ch=='\u03C0')
            {
                int pie = indicator;
                char precedence;
                if(pie > 0)
                    precedence = eqn.charAt(--pie);
                else
                    precedence = eqn.charAt(pie);
                if((precedence>'0' && precedence<'9') || precedence=='.')
                {
                    ans = Double.parseDouble(precedence+"")*Math.PI;
                }
                else {
                    ans = Math.PI;
                }
                nextInput();
            }
        }
        else if(ch=='e')
        {
            while(!(ch>='0' && ch<='9'))
            {
                nextInput();
            }

            String str="";
            while(!checkOpr(')')) {
                str = str + this.ch;
                nextInput();
            }
            ans = Math.exp(new CalculatorLogic(str).evaluate());
        }
        else
        {
            throw new RuntimeException("Unexpected: '" + (char)ch+"' character.");
        }
        return ans;
    }


    private double parseDoubleFactors() {
        double ans = parseSingleFactor(0);

        while(true)
        {
            if(checkOpr('*'))
            {
                ans = ans * parseSingleFactor(ans);

            }
            else if(checkOpr('/'))
            {
                try {
                    ans = ans / parseSingleFactor(ans);
                }
                catch(ArithmeticException e)
                {

                    e.printStackTrace();
                }
            }
            else
            {
                return ans;
            }
        }
    }

    private double parseCombinedTerm() {
        double ans = parseDoubleFactors();
        while(true)
        {
            if(checkOpr('+'))
            {
                ans = ans+parseSingleFactor(ans);
            }
            else if(checkOpr('-'))
            {
                ans = ans - parseSingleFactor(ans);
            }
            else
            {
                return ans;
            }
        }
    }


    public double evaluate() {
        nextInput();
        return parseCombinedTerm();
    }

    public void setEqn(String eqn) {
        this.eqn = eqn;
        initialPosition();
    }

}
