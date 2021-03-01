package com.example.exampleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    protected boolean equally = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String parseResultText (){
        TextView resText = (TextView)findViewById(R.id.Result);
        return resText.getText().toString();
    }

    public void setResultText (String text){
        TextView resText = (TextView)findViewById(R.id.Result);
        resText.setText(text);
        if (text.contains("=")) equally = true;
    }

    public void onButtonClick (View v){ // 0-9, +, -, *, /
        if (equally) {
            setResultText("");
            equally = false;
        }
        setResultText(parseResultText()+((TextView)v).getText().toString());
    }

    public void onDelClick (View v){
        String previousText = parseResultText();
        if (previousText.isEmpty()) return;
        setResultText(previousText.substring(0,previousText.length()-1));
        if (previousText.contains("=")) equally = true;
        else equally = false;
    }

    public void onClearClick (View v){
        setResultText("");
        TextView debugText = (TextView)findViewById((R.id.Debug));
        debugText.setText("");
    }

    public void onResultClick (View v) {
        try {
            setResultText(parseResultText() + "=" + ReversePolishNotation.calculateExpression(parseResultText()));
        }catch (Exception er){
            TextView debugText = (TextView)findViewById((R.id.Debug));
            debugText.setText(er.toString());
        }

    }
}