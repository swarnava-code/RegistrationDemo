package zavetech.reg.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void view(View view) {
        Intent i=new Intent(getApplicationContext(), ShowActivity.class);
        startActivity(i);
    }
    public void reg(View view) {
        Intent i=new Intent(getApplicationContext(), regActivity.class);
        startActivity(i);
    }
}