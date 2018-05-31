package challenge.sports.com.equalchallengetm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import challenge.sports.com.equalchallengetm.adapters.HistorialTorneoAdapter;
import challenge.sports.com.equalchallengetm.modelo.HistorialTorneo;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialTorneoFragment extends Fragment {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private ArrayList<HistorialTorneo> list= new ArrayList<>();
    private HistorialTorneoAdapter adapter;

    public HistorialTorneoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_historial_torneo, container, false);
        requestQueue= Volley.newRequestQueue(getActivity());
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerHT);
        consulta();
        return view;
    }

    private void consulta(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter= new HistorialTorneoAdapter(list,getContext().getApplicationContext());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, Url.url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                   JSONObject object= jsonObject.getJSONObject("info");
                    String nombre= object.getString("winner");
                    String fecha = object.getString("date");
                    String lugar= object.getString("place");
                    list.add(new HistorialTorneo(nombre,fecha,lugar));
                    recyclerView.setAdapter(adapter);
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
