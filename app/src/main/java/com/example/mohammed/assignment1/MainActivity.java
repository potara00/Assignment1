package com.example.mohammed.assignment1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //widgets in the application
    private Button btnReset;
    private Button btnCheck;
    private EditText txtNumber;

    //logic variable
    private int randomNumber;
    private int guessedNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheck = (Button) findViewById(R.id.btnGuess);
        btnReset = (Button) findViewById(R.id.btnReset);
        txtNumber = (EditText) findViewById(R.id.txtNumber);

        generateRandomNumber();


    }

    /**
     * generates a new integer between 0(inclusive) and 1000(exclusive)
     */
    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(1000);
    }

    /**
     * check if the input number is greater, smaller or equal to the random number
     */

    public void checkNumberAction(View v) {
        try{
        //if user has empty input, do nothing.
        if(txtNumber.getText().equals(""))
            return;
        guessedNumber = Integer.parseInt(txtNumber.getText().toString());
        if (guessedNumber == randomNumber) {
            Toast.makeText(getApplicationContext(),"Congratulations! You've Won.",Toast.LENGTH_SHORT).show();
            txtNumber.setText("");
        } else if (isClose()) {
            Toast.makeText(getApplicationContext(),"You're getting very close!",Toast.LENGTH_SHORT).show();
        } else if (guessedNumber > randomNumber) {
            Toast.makeText(getApplicationContext(),"Nope, it's Smaller than this.",Toast.LENGTH_SHORT).show();
        } else if (guessedNumber < randomNumber) {
            Toast.makeText(getApplicationContext(),"Nope, it's Greater than this.",Toast.LENGTH_SHORT).show();
        }}catch(Exception e){
            System.out.print("Something Went Wrong, probably empty input");
        }

    }

    /**
     * @return true if the input number is very close to the random number
     * (the value/distance between the guessed and the right answer is 5 or less)
     */
    private boolean isClose() {
        return (guessedNumber < (randomNumber + 5) && guessedNumber > (randomNumber - 5));

    }

    /**
     * restart game, generates a new random number, emptying out the input field.
     */
    public void restartAction(View v) {
        Toast.makeText(getApplicationContext(),"Reset! New Number Generated.",Toast.LENGTH_SHORT).show();
        txtNumber.setText("");
        generateRandomNumber();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
