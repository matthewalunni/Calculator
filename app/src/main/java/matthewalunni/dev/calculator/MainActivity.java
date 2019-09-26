package matthewalunni.dev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    public double OutputTextViewValue = 0;
    public String InputTextViewValue = "";
    private static final Logger _Logger = Logger.getLogger( MainActivity.class.getName() );
    public TextView TvOutput;
    public TextView TvInput;
    public String numberOne = "";
    public String numberTwo = "";
    public Boolean firstInput = true;
    public double result = Double.MIN_VALUE;
    public String function = "";
    public int functionCount = 0;
    public Boolean first = true;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView TvSquared = findViewById(R.id.TvSquared);
        TvSquared.setText(Html.fromHtml("x<sup>2</sup>"));
        TvOutput = findViewById(R.id.TvOutput);
        TvInput = findViewById(R.id.TvInput);
        SetInputAndOutput(InputTextViewValue, OutputTextViewValue);

    }

    //region Listeners
    public void TvClearOnClick(View v) {
        try {
            numberOne = "";
            numberTwo = "";
            first = true;
            firstInput = true;
            OutputTextViewValue = 0;
            InputTextViewValue = "";
            SetInputAndOutput(InputTextViewValue, OutputTextViewValue);
        }
        catch (Exception ex) {
            _Logger.log(Level.ALL, ex.toString());
            TvInput.setText("Error");
        }

    }

    public void TvSquaredOnClick(View v) {
        DoFunction("^2");
    }
    public void TvOneOnClick(View v) {
        DoNumber("1");
    }
    public void TvTwoOnClick(View v) {
        DoNumber("2");
    }
    public void TvThreeOnClick(View v) {
        DoNumber("3");
    }
    public void TvFourOnClick(View v) {
        DoNumber("4");
    }
    public void TvFiveOnClick(View v) {
        DoNumber("5");
    }
    public void TvSixOnClick(View v) {
        DoNumber("6");
    }
    public void TvSevenOnClick(View v) {
        DoNumber("7");
    }
    public void TvEightOnClick(View v) {
        DoNumber("8");
    }
    public void TvNineOnClick(View v) {
        DoNumber("9");
    }
    public void TvZeroOnClick(View v) {
        DoNumber("0");
    }

    public void TvAddOnClick(View v) {
        DoFunction("+");
    }
    public void TvMultiplyOnClick(View v){
        DoFunction("*");
    }
    public void TvSubtractOnClick(View v){
        DoFunction(("-"));
    }
    public void TvDivideOnClick(View v) {
        DoFunction("/");
    }
    public void TvEqualsOnClick(View v) {
        try {
            if(functionCount > 1 || InputTextViewValue.contains("(") || InputTextViewValue.contains(")")) {
                result = Helper.Evaluate(InputTextViewValue);
            }
            else {
                if (first) {
                    first = false;
                    //numberTwo = input;
                }
                else {
                    numberOne = Double.toString(result);

                }

                ChooseFunction(function);
                numberTwo = "";
            }

            InputTextViewValue = "";
            TvInput.setText(InputTextViewValue);
            TvOutput.setText(Double.toString(result));
            functionCount = 0;
        }
        catch (Exception ex) {
            _Logger.log(Level.ALL, ex.toString());
            TvInput.setText("Error");
        }

    }
    public void TvDecimalOnClick(View v) {
        DoNumber(".");
    }

    public void  TvPlusMinusOnClick(View v) {
        try {
            if (firstInput) {
                if(numberOne.contains("-")) {
                    //remove all instances of -
                    numberOne = numberOne.replace("-", "");
                }
                else {
                    numberOne = "-" + numberOne;
                }
                TvInput.setText("");
                InputTextViewValue = numberOne;
            }
            else {

                if(numberTwo.contains("-")) {
                    //remove all instances of -
                    numberTwo = numberTwo.replace("-", "");
                }
                else {
                    numberTwo = "-" + numberTwo;
                }

                //add result to string instead of numberone if equals has already been pushed
                if (result == Double.MIN_VALUE) {
                    InputTextViewValue = "";
                    InputTextViewValue = numberOne + " " + function + " " + numberTwo;
                }
                else {
                    InputTextViewValue = "";
                    InputTextViewValue = result + " " + function + " " + numberTwo;
                }
            }

            TvInput.setText(InputTextViewValue);
        }
        catch (Exception ex) {
            _Logger.log(Level.ALL, ex.toString());
            TvInput.setText("Error");
        }

    }
    public void TvPercentOnClick(View v) {


    }


    //endregion Listeners

    //region Methods
    /** this method is used to add a number to the UI mainly**/
    public void DoNumber(String number) {
        try {
            if (firstInput) {
                numberOne += number;
            }
            else {
                //update numberTwo number here
                numberTwo += number;
            }

            InputTextViewValue = InputTextViewValue + number;
            TvInput.setText(InputTextViewValue);
        }
        catch (Exception ex) {
            _Logger.log(Level.ALL, ex.toString());
            TvInput.setText("Error");
        }
    }

    /** this method sets the input and output displays**/
    public void SetInputAndOutput(String input, double output) {
        try {
            TvInput.setText(input);
            TvOutput.setText(Double.toString(output));
        }
        catch (Exception ex) {
            _Logger.log(Level.ALL, ex.toString());
            TvInput.setText("Error");
        }

    }

    /** this method performs a basic function on the calculator **/
    public void DoFunction(String _function){
        try {
            if (firstInput) firstInput = false;
            function = _function;
            InputTextViewValue = InputTextViewValue + " " + function + " ";
            TvInput.setText(InputTextViewValue);
            functionCount++;
        }
        catch (Exception ex) {
            _Logger.log(Level.ALL, ex.toString());
            TvInput.setText("Error");
        }

    }

    /** this method performs the most basic functions**/
    public void ChooseFunction(@org.jetbrains.annotations.NotNull String afunction) {

        try {
            functionCount++;
            switch (afunction){
                case "+": {
                    result = Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
                    break;
                }
                case "-": {
                    result =  Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);
                    break;
                }
                case "x": {
                    result =  Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);
                    break;
                }
                case "/": {
                    result =  Double.parseDouble(numberOne) / Double.parseDouble(numberTwo);
                    break;
                }
                case "^2": {
                    if (result == Double.MIN_VALUE && numberOne.equals("")) {
                        result = 0;
                    }
                    else if (numberTwo.equals("")){
                        result = Double.parseDouble(numberOne)*Double.parseDouble(numberOne);
                    }
                    else {
                        result =  Double.parseDouble(numberTwo) * Double.parseDouble(numberTwo);
                    }
                    break;
                }
                default: {
                    _Logger.log(Level.ALL, "Case statement didn't catch", afunction);
                    result =  -1;
                    break;
                }
            }
        }
        catch (Exception e) {
            result = -1;
        }


    }

    //endregion Methods

}
