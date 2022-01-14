package com.example.test_php_android;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private  ProgressBar loadingPB ;
    EditText etid , etName  , etDegree;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etid = findViewById(R.id.etUsername);
        etName = findViewById(R.id.etPassword);
        btnLogin =findViewById(R.id.btnLogin);
        etDegree = findViewById(R.id.et_degree);
        loadingPB = findViewById(R.id.idLoadingPB);


       btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //postDataUsingVolley(etUsername.getText().toString(), etPassword.getText().toString());
                insertData();

            }
        });



    }

    private void insertData() {
        String id = etid.getText().toString().trim();
        String name  = etName.getText().toString().trim();
        String degree = etDegree.getText().toString().trim();
        if(id.isEmpty()){
            Toast.makeText(this, "Enter id ", Toast.LENGTH_SHORT).show(); return;
        } else if(name.isEmpty()){
            Toast.makeText(this, "Enter name ", Toast.LENGTH_SHORT).show(); return;
        } else if(degree.isEmpty()){
            Toast.makeText(this, "Enter Degree  ", Toast.LENGTH_SHORT).show();return;
        } else {

            postDataUsingVolley(id,name,degree);

        }



    }

    // this is for login
    private void postDataUsingVolley( String id,  String name  ,   String degree) {

        // url to post our data
        String url = "http://192.168.1.7/api/login.php";
        // loading pogress bar this is optional
        loadingPB.setVisibility(View.VISIBLE);

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                loadingPB.setVisibility(View.GONE);

               /* // on below line we are displaying a success toast message.
                //Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are passing our response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    String result = respObj.getString("result");
                    String username = respObj.getString("uname");

                    // we just toast the value we got from API, 1 for success, 0 otherwise
                    Toast.makeText(MainActivity.this, "result is " + result + ", Hello " + username, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                if(response.equalsIgnoreCase("inserting  is done")){
                    Toast.makeText(MainActivity.this, "Data added Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("id", id);
                params.put("name", name);
                params.put("degree", degree);
                // put some code for verfication that the post came from your mobile app
                /*params.put("login", "1234");*/

                // at last we are
                // returning our params.
                return params;
            }
        };

        // below line is to make
        // a json object request.
        queue.add(request);
    }
}