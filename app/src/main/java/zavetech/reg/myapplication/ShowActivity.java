package zavetech.reg.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    TextView nameTv, mobileTv, emailTv, addressTv, genderTv, imageTv, textView;

    String[] idArr = new String[100];
    String[] nameArr = new String[100];
    String[] mobileArr = new String[100];
    String[] emailArr = new String[100];
    String[] addressArr = new String[100];
    String[] genderArr = new String[100];
    String[] imageArr = new String[100];

    String jsonStr="";
    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        textView = findViewById(R.id.json);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                new ShowActivity.GetApiCall().execute();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r,500);

    }

    private class GetApiCall extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall("https://swarnava.delgradecorporation.in/project1/get_users_details.php");
            try {

                JSONArray parentArray = new JSONArray(jsonStr);

                length = parentArray.length();

                for(int i=0; i<length; i++){

                    JSONObject temp = parentArray.getJSONObject(i);

                    idArr[i] = temp.getString("id");
                    nameArr[i] = temp.getString("name");
                    mobileArr[i] = temp.getString("mobile");
                    emailArr[i] = temp.getString("email");
                    genderArr[i] = temp.getString("gender");
                    addressArr[i] = temp.getString("address");
                    imageArr[i] = temp.getString("photo_upload");

                }

            }catch (JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            jsonStr = "\n\n## Converting json to table...\n\n"+jsonStr+"\n\n## Converting json to table...\n\n";
            textView.setText(jsonStr);

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    String table = "";
                    for(int i=0; i<length; i++){
                        table += nameArr[i]+"\n"+mobileArr[i]+"\n"+emailArr[i]+"\n"+genderArr[i]+"\n"+addressArr[i]+"\n"+imageArr[i]+"\n__________________\n";
                    }
                    textView.setText(table);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r,2000);


            if (textView.getText() == null || textView.getText().equals("")) {
                Toast.makeText(ShowActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                //informUser("Something Error");
            }

        }
    }

}