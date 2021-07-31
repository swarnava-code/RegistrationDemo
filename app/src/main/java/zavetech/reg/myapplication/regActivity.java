package zavetech.reg.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class regActivity extends AppCompatActivity {

    String genderStr="", nameStr="", mobileStr="", emailStr="", addressStr="";//photo
    Button submitBtn;
    TextView nameTv, mobileTv, emailTv, addressTv;
    private int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    private Uri filepath;
    private Bitmap bitmap;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        submitBtn = findViewById(R.id.submitBtn);

        nameTv = findViewById(R.id.name);
        mobileTv = findViewById(R.id.mobile);
        emailTv = findViewById(R.id.email);
        addressTv = findViewById(R.id.address);
        imageView = findViewById(R.id.image);


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

    public void ShowFileChooser(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

            filepath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView.setImageBitmap(bitmap);
                tv.setText(filepath.toString());
                // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
            } catch (Exception ex) {

            }
        }
    }
}