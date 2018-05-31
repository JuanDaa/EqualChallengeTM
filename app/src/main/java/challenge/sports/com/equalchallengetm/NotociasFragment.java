package challenge.sports.com.equalchallengetm;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotociasFragment extends Fragment {
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;

    public NotociasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notocias, container, false);
        requestQueue= Volley.newRequestQueue(getActivity());
        traerDisciplina();
        return view;
    }
    private void traerDisciplina(){
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, Url.url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject objet= jsonObject.getJSONObject("info");
                        String idGanador= objet.getString("winnerId");
                     JSONObject grupos= objet.getJSONObject("setup");
                        System.out.println("ganador "+idGanador+"grupos "+grupos );




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
