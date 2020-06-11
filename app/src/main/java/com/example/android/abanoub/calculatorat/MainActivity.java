package com.example.android.abanoub.calculatorat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_equation , tv_sign;
    private int code = 0;
    private double result = 0;
    private double  x , y ;
    private String equationValue;
    //first row buttons
    private Button bt_ac , bt_minus_plus , bt_percent , bt_div;
    //second row buttons
    private Button bt_7 , bt_8 , bt_9 , bt_multi;
    //third row buttons
    private Button bt_4 , bt_5 , bt_6 , bt_minus;
    //forth row buttons
    private Button bt_1 , bt_2 , bt_3 , bt_plus;
    //fifth row buttons
    private Button bt_0   , bt_equal;

    private MathOperation operation = new MathOperation();

    StringBuilder equation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_equation = (TextView)findViewById(R.id.equation_number);
        tv_sign = (TextView)findViewById(R.id.equation_sign);
        tv_sign.setVisibility(View.INVISIBLE);

        bt_ac = (Button)findViewById(R.id.bt_ac);
        bt_minus_plus = (Button)findViewById(R.id.bt_plus_min);
        bt_percent = (Button)findViewById(R.id.bt_percentage);
        bt_div = (Button)findViewById(R.id.bt_div);

        bt_7 = (Button)findViewById(R.id.bt_7);
        bt_8 = (Button)findViewById(R.id.bt_8);
        bt_9 = (Button)findViewById(R.id.bt_9);
        bt_multi = (Button)findViewById(R.id.bt_multi);

        bt_4 = (Button)findViewById(R.id.bt_4);
        bt_5 = (Button)findViewById(R.id.bt_5);
        bt_6 = (Button)findViewById(R.id.bt_6);
        bt_minus = (Button)findViewById(R.id.bt_minus);

        bt_1 = (Button)findViewById(R.id.bt_1);
        bt_2 = (Button)findViewById(R.id.bt_2);
        bt_3 = (Button)findViewById(R.id.bt_3);
        bt_plus = (Button)findViewById(R.id.bt_plus);

        bt_0 = (Button)findViewById(R.id.bt_0);
//        bt_decimal = (Button)findViewById(R.id.bt_decimal);
        bt_equal = (Button)findViewById(R.id.bt_equal);

        equation = new StringBuilder();




    }

    public void handlingClicks(View view){
        switch (view.getId()){

            //       Numbers Buttons
            case R.id.bt_9:

                equation.append(9);
                tv_equation.setText(equation);
                break;

            case R.id.bt_8:

                equation.append(8);
                tv_equation.setText(equation);

                break;

            case R.id.bt_7:

                equation.append(7);
                tv_equation.setText(equation);

                break;

            case R.id.bt_6:

                equation.append(6);
                tv_equation.setText(equation);

                break;

            case R.id.bt_5:

                equation.append(5);
                tv_equation.setText(equation);

                break;

            case R.id.bt_4:

                equation.append(4);
                tv_equation.setText(equation);

                break;

            case R.id.bt_3:

                equation.append(3);
                tv_equation.setText(equation);

                break;

            case R.id.bt_2:

                equation.append(2);
                tv_equation.setText(equation);

                break;

            case R.id.bt_1:

                equation.append(1);
                tv_equation.setText(equation);

                break;

            case R.id.bt_0:

                equation.append(0);
                tv_equation.setText(equation);

                break;





                //      Math Operation Buttons


            case R.id.bt_plus:

                String plus = "+";
                int plusCode = 1;
                handlingSigns(plus , plusCode);

                break;


            case R.id.bt_minus:

                String minus = "-";
                int minusCode = 2;
                handlingSigns( minus,minusCode );


                break;

            case R.id.bt_multi:

                String multi = "*";
                int multiCode = 3;
                handlingSigns(multi , multiCode);


                break;



            case R.id.bt_div:

                String div = "/";
                int divCode = 4;
                handlingSigns(div , divCode);


                break;



                //     Others

            case R.id.bt_percentage:


                percentage();

                break;

            case R.id.bt_plus_min:

                plusMinus();

                break;


            case R.id.bt_ac:

                clearAll();

                break;


            case R.id.bt_equal:

                gettingResult();

                break;

        }

    }

    private void handlingSigns(String sign , int SignCode ){

        //determines operator sign
             tv_sign.setText(sign);
             tv_sign.setVisibility(View.VISIBLE);

        //determines operator code ( to use it @ MathOperation.Operation)
        code = SignCode;

        //getting the first value
        String st_eq = equation.toString();
        if (!st_eq.isEmpty()){
            //TODO
            x = Double.parseDouble(equation.toString());
            Log.e("String" , " equation = " +st_eq);

        }


        //getting the length of StringBuilder to use it @ delete method
        int length = equation.length();
        equation.delete( 0 , length);
    }



    private void gettingResult(){

        //checks if there is no recent value
        equationValue = equation.toString();
       if (!equationValue.isEmpty()){

           /*
           1)getting the second input
           2)making the operation
           3)updating the x variable for further calculation
            */
           y = Double.parseDouble(equation.toString());
           result =   operation.operation(x,y,code);
           x = result;

           //removing the equation variable to reset everything for further calculation
           String st_result = String.valueOf(result);
           int length =  equation.length();
           equation.delete(0,length);
           //finally publishing the final result
           tv_equation.setText(st_result);

//           Log.v("Result" ," = "+result);

           tv_sign.setVisibility(View.INVISIBLE);
       }else {
           //telling the user to enter input for calculation
           Toast.makeText(this,"Please enter Number First",Toast.LENGTH_SHORT).show();
       }

    }

    private void plusMinus(){

        //getting the output
        String tv = tv_equation.getText().toString();
        StringBuilder builder = new StringBuilder(tv);
        //checks if the current output contains (-) or not
        if (tv.contains("-")){

            builder.deleteCharAt(0);
            tv_equation.setText(builder.toString());

        }else {
            builder.insert(0,"-");
            tv_equation.setText(builder.toString());


        }
    }

    //CA Button
    private void clearAll(){
        equationValue = equation.toString();

        if (!equationValue.isEmpty() || result != -1){

            int length =  equation.length();
            equation.delete(0,length);
            tv_equation.setText("");


        }
    }
    /*
    am not really sure of the functionality of the default percentage buttons
    but that's the best i can do :D
     */
    private void percentage(){
        double percent = result /100;
        String st_res = String.valueOf(percent);
        tv_equation.setText(st_res);
    }
}
