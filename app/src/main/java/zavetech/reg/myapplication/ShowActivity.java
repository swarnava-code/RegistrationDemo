package zavetech.reg.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView nameTv, mobileTv, emailTv, addressTv, genderTv, imageTv;

    String[] idArr = new String[100];
    String[] nameArr = new String[100];
    String[] mobileArr = new String[100];
    String[] emailArr = new String[100];
    String[] addressArr = new String[100];
    String[] genderArr = new String[100];
    String[] imageArr = new String[100];

    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        new ShowActivity.asyncTask().execute();

    }



    private class asyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //before execution
            // we can interact with frontend(GUI) here
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String url="https://api.themoviedb.org/3/movie/top_rated?api_key=8265bd1679663a7ea12ac168da84d2e8&language=en-US&page=1";

            StringRequest stringRequest = new StringRequest
                    (StringRequest.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {

                                        JSONObject parentObject = new JSONObject(response);



                                        //JSONArray result=parentObject.getJSONArray("results");
                                        //parentObject.get

                                        length = parentObject.length();

                                        for(int i=0; i<length; i++){

                                            JSONObject temp = parentObject.getJSONObject(""+i);

                                            idArr[i] = temp.getString("id");
                                            nameArr[i] = temp.getString("name");
                                            mobileArr[i] = temp.getString("mobile");
                                            emailArr[i] = temp.getString("email");
                                            genderArr[i] = temp.getString("gender");
                                            addressArr[i] = temp.getString("photo_upload");
                                            imageArr[i] = temp.getString("photo_upload");

                                        }

                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

            RequestQueue requestQueue= Volley.newRequestQueue(ShowActivity.this);
            requestQueue.add(stringRequest);

            CustomAdapter cadapter = new CustomAdapter();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lv.setAdapter(cadapter);
                }
            });

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //for update
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //execute this after backgroud task completed
            // we can interact with frontend(GUI) here
        }

    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.single_element, null);

            nameTv = view.findViewById(R.id.name);
            mobileTv = view.findViewById(R.id.mobile);
            emailTv = view.findViewById(R.id.email);
            addressTv = view.findViewById(R.id.address);
            genderTv = view.findViewById(R.id.gender);
            imageTv = view.findViewById(R.id.image);

            nameTv.setText(nameArr[i]);
            mobileTv.setText(mobileArr[i]);
            emailTv.setText(emailArr[i]);
            addressTv.setText(addressArr[i]);
            genderTv.setText(genderArr[i]);
            imageTv.setText(imageArr[i]);

            return view;

        }
    }

}