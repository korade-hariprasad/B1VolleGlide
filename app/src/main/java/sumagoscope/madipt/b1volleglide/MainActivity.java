package sumagoscope.madipt.b1volleglide;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> list;
    RecyclerView recyclerView;
    ProductListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        //RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        getListFromServer();
    }

    private void getListFromServer() {

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://dummyjson.com/products?select=title,price,thumbnail,category";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("mytag","onResponse");
                try {
                    JSONObject mainObject=new JSONObject(response);
                    JSONArray jsonArray=mainObject.getJSONArray("products");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Product product=new Product();
                        product.setTitle(jsonObject.getString("title"));
                        product.setThumbnail(jsonObject.getString("thumbnail"));
                        product.setCategory(jsonObject.getString("category"));
                        product.setPrice(jsonObject.getDouble("price"));
                        list.add(product);
                    }
                    adapter=new ProductListAdapter(list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.d("mytag",e.getMessage(),e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("mytag",error.getMessage(),error);
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }
}