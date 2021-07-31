package zavetech.reg.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class regActivity extends AppCompatActivity {

    String genderStr="", nameStr="", mobileStr="", emailStr="", addressStr="";//photo
    Button submitBtn;
    TextView nameTv, mobileTv, emailTv, addressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        submitBtn = findViewById(R.id.submitBtn);

        nameTv = findViewById(R.id.name);
        mobileTv = findViewById(R.id.mobile);
        emailTv = findViewById(R.id.email);
        addressTv = findViewById(R.id.address);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nameStr = nameTv.getText().toString();
                mobileStr = mobileTv.getText().toString();
                emailStr = emailTv.getText().toString();
                addressStr = addressTv.getText().toString();

                if(genderStr.length()>0 && nameStr.length()>0 &&  mobileStr.length()>0 &&  emailStr.length()>0 &&  addressStr.length()>0) {// )
                    Toast.makeText(regActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void gender(View view) {
        RadioButton rb = (RadioButton)view;
        genderStr = rb.getText().toString();
    }
}