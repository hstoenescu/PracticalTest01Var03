package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {


    private EditText text1 = null;
    private EditText text2 = null;
    private Button okButton = null;
    private Button cancelButton = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.button2:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        text1 = (EditText) findViewById(R.id.text1);
        text2 = (EditText) findViewById(R.id.text2);
        Intent intent = getIntent();
        if (intent != null && ( intent.getExtras().containsKey("text1") || (intent.getExtras().containsKey("text2")))) {
            String txt1 = String.valueOf(intent.getIntExtra("text1", -1));
            String txt2 = String.valueOf(intent.getIntExtra("text2", -1));
            text1.setText(String.valueOf(txt1));
            text2.setText(String.valueOf(txt2));
        }

        //  listener pe butoane
        okButton = (Button)findViewById(R.id.button);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.button2);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}
