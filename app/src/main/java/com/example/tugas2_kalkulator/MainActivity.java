package com.example.tugas2_kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
    }

    public void onDigit(View view) {
        if (isNewOperation) {
            currentInput.setLength(0);  // Clear input for new operations
            isNewOperation = false;
        }
        currentInput.append(((TextView) view).getText().toString());
        textViewResult.setText(currentInput.toString());
    }

    public void onOperator(View view) {
        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
            operator = ((TextView) view).getText().toString();
            currentInput.setLength(0);
            textViewResult.setText("");
        }
    }

    public void onEqual(View view) {
        if (operator.isEmpty() || currentInput.length() == 0) {
            return;  // No operation to perform
        }

        double secondOperand = Double.parseDouble(currentInput.toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    textViewResult.setText("Error");  // Cannot divide by zero
                    return;
                }
                break;
        }

        textViewResult.setText(String.valueOf(result));
        operator = "";
        isNewOperation = true;
    }

    public void onClear(View view) {
        currentInput.setLength(0);
        textViewResult.setText("0");
        firstOperand = 0;
        operator = "";
        isNewOperation = true;
    }
}