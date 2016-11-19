package com.example.nochem.afm;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;


public class MainActivity extends Activity {

    private EditText input;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    input = (EditText) findViewById(R.id.editText);

    public void save(){
        input.getText();
        //This is going to be the main loop.
    }


}
