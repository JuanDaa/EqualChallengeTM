package challenge.sports.com.equalchallengetm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EliminatoriaFragment extends Fragment {
    private LinearLayout layout;
    private TextView textView;
    private ListView listView;
    private RequestQueue requestQueue;
    int id=4;

    public EliminatoriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_eliminatoria, container, false);
        requestQueue= Volley.newRequestQueue(getActivity());
         datos();
        listView= (ListView) view.findViewById(R.id.listView);
       /* layout= (LinearLayout) view.findViewById(R.id.datos);
        textView = new TextView(getActivity());
        textView.setText("daniel");
        textView.setId(id);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(textView);*/
        return view;
    }
    private void datos(){
       final List<String> grupos= new ArrayList<>();
      JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Url.url1, null, new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject jsonObject) {
              try {
                  JSONObject object= jsonObject.getJSONObject("matches");
                  JSONArray partidos = object.names();

                  for (int i=0;i<object.length();i++){
                        String datos= partidos.getString(i);
                      char array= datos.charAt(i);
                      System.out.println("data "+array);
                  }
              } catch (JSONException e) {
                  System.out.println("error "+e);
              }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError volleyError) {

          }
      });
        requestQueue.add(request);
    }
    private void adapter(List grupo){
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,grupo);
        listView.setAdapter(adapter);
    }
}
