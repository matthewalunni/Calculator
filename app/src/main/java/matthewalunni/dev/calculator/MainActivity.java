package matthewalunni.dev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    public double OutputTextViewValue = 0;
    public String InputTextViewValue = "";
    private static final Logger _Logger = Logger.getLogger( MainActivity.class.getName() );
    public TextView TvOutput;
    public TextView TvInput;
    public double numberOne = 0;
    public double numberTwo = 0;
    public Boolean firstInput = true;
    public double result = 0;
    public String function = "";
    public Boolean first = true;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TvOutput = findViewById(R.id.TvOutput);
        TvInput = findViewById(R.id.TvInput);
        SetInputAndOutput(InputTextViewValue, OutputTextViewValue);
    }

    //region Listeners
    public void TvClearOnClick(View v) {
        OutputTextViewValue = 0;
        InputTextViewValue = "";
        SetInputAndOutput(InputTextViewValue, OutputTextViewValue);
    }


    public void TvOneOnClick(View v) {
        DoNumber(1);
    }
    public void TvTwoOnClick(View v) {
        DoNumber(2);
    }

    public void TvThreeOnClick(View v) {
        DoNumber(3);
    }

    public void TvFourOnClick(View v) {
        DoNumber(4);
    }
    public void TvFiveOnClick(View v) {
        DoNumber(5);
    }
    public void TvSixOnClick(View v) {
        DoNumber(6);
    }
    public void TvSevenOnClick(View v) {
        DoNumber(7);
    }
    public void TvEightOnClick(View v) {
        DoNumber(8);
    }
    public void TvNineOnClick(View v) {
        DoNumber(9);
    }
    public void TvZeroOnClick(View v) {
        DoNumber(0);
    }

    public void TvAddOnClick(View v) {
        DoFunction("+");
    }

    public void TvMultiplyOnClick(View v){
        DoFunction("x");
    }

    public void TvSubtractOnClick(View v){
        DoFunction(("-"));
    }

    public void TvDivideOnClick(View v) {
        DoFunction("/");
    }

    public void TvEqualsOnClick(View v) {
        if (first) {
            first = false;
            //numberTwo = input;
        }
        else {
            numberOne = result;
        }

        ChooseFunction(function);
        InputTextViewValue = "";
        TvInput.setText(InputTextViewValue);
        TvOutput.setText(Double.toString(result));
    }

    //endregion Listeners

    //region Methods
    public void DoNumber(double number) {
        if (firstInput) {
            numberOne = number;
            firstInput = false;
        }
        else {

            //update numberTwo number here
            numberTwo = number;
        }

        InputTextViewValue = InputTextViewValue + " " + number;
        TvInput.setText(InputTextViewValue);
    }

    public void SetInputAndOutput(String input, double output) {
        TvInput.setText(input);
        TvOutput.setText(Double.toString(output));
    }

    public void DoFunction(String _function){
        function = _function;
        InputTextViewValue = InputTextViewValue + " " + function;
        TvInput.setText(InputTextViewValue);
    }

    public void ChooseFunction(@org.jetbrains.annotations.NotNull String afunction) {
        switch (afunction){
            case "+": {
                result = numberOne + numberTwo;
                break;
            }
            case "-": {
                result =  numberOne - numberTwo;
                break;
            }
            case "x": {
                result =  numberOne * numberTwo;
                break;
            }
            case "/": {
                result =  numberOne / numberTwo;
                break;
            }
            default: {
                _Logger.log(Level.ALL, "Case statement didnt catch", afunction);
                result =  -1;
                break;
            }
        }

    }
    //endregion Methods

}
