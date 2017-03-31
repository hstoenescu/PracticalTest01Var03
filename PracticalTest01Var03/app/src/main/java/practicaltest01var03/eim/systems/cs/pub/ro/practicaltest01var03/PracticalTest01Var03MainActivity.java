package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private Button displayInfoButton = null;
    private Button navigateToSecondaryActivityButton = null;
    private CheckBox checkBox1 = null;
    private CheckBox checkBox2 = null;
    private EditText text1 = null;
    private EditText text2 = null;
    private EditText text3 = null;


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            /* initializare string-uri cu null si verificam daca checkbox-urile sunt tick-uite */
            String str1 = "", str2 = "";

            if (checkBox1.isChecked())
                str1 = text1.getText().toString();

            if (checkBox2.isChecked())
                str2 = text2.getText().toString();

            switch(view.getId()) {
                // in cazul in care apasam pe butam, punem pe text3 cele 2 msj concatenate
                case R.id.display_information:
                    // in cazul in care e vid punem doar un string null
                    String textForToast = "";
                    if (!checkBox1.isChecked()) {
                        if (!textForToast.isEmpty())
                            textForToast += "\n";
                        textForToast += "Checkbox1 not enabled (text1 not null)";
                    }
                    if (!checkBox2.isChecked()) {
                        if (!textForToast.isEmpty())
                            textForToast += "\n";
                        textForToast += "Checkbox2 not enabled (text2 not null)";
                    }

                    if (! textForToast.isEmpty())
                        showToast(textForToast);

                    text3.setText(String.valueOf(str1 + " " + str2));
                    break;
                case R.id.navsec:
                    Intent intent = new Intent (getApplicationContext(),PracticalTest01Var03SecondaryActivity.class);
                    intent.putExtra("text1", str1);
                    intent.putExtra("text2", str2);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }

    }

    protected void showToast (String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

            // obtinere referinte la butoane + adaugare listener-i
            displayInfoButton = (Button) findViewById(R.id.display_information);
            navigateToSecondaryActivityButton = (Button) findViewById(R.id.navsec);
            displayInfoButton.setOnClickListener(buttonClickListener);
            navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);

            // get checkboxes
            checkBox1 = (CheckBox) findViewById(R.id.checkbox);
            checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

            // get text
            text1 = (EditText) findViewById(R.id.text1);
            text2 =(EditText) findViewById(R.id.text2);
            text3 = (EditText) findViewById(R.id.text3);

            // init pt texte
            text3.setText(String.valueOf(""));

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    // salvare si restaurare controale
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("text1")) {
            text1.setText(savedInstanceState.getString("text1"));
        } else {
            text1.setText(String.valueOf(""));
        }
        if (savedInstanceState.containsKey("text2")) {
            text2.setText(savedInstanceState.getString("text2"));
        } else {
            text2.setText(String.valueOf(""));
        }
        if (savedInstanceState.containsKey("text3")) {
            text3.setText(savedInstanceState.getString("text3"));
        }
         else {
            text3.setText(String.valueOf(""));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("text1", text1.getText().toString());
        savedInstanceState.putString("text2", text2.getText().toString());
        savedInstanceState.putString("text3", text3.getText().toString());
    }
}
