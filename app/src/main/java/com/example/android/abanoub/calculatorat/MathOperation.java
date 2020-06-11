package com.example.android.abanoub.calculatorat;

public class MathOperation {

    double z;
    public MathOperation(){}

    public double operation (double x , double y ,int code){

        switch (code){

            case 1:

                z = add(x, y);

                break;

            case 2:

                z = sub(x, y);

                break;

            case 3:

                z = multi(x, y);

                break;


            case 4:

                z = div(x, y);

                break;

        }

        return z;
    }

    private double add (double x , double y ) {

         z = x+y;
        return z;
    }

    private double sub (double x , double y ) {

         z = x-y;
        return z;
    }

    private double multi (double x , double y ) {

         z = x*y;
        return z;
    }

    private double div (double x , double y ) {

        z = x/y;
        return z;
    }


}
