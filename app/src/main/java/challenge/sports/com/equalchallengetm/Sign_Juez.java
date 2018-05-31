package challenge.sports.com.equalchallengetm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Map;

import challenge.sports.com.equalchallengetm.damm.Damm;
import challenge.sports.com.equalchallengetm.modelo.HistorialTorneo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sign_Juez extends Fragment {
    private Button btnIngresoJuez;
    private EditText OTP;
    private Damm damm = new Damm();
    private RequestQueue requestQueue;
   // private final ArrayList<String> arrayList = new ArrayList<>();
    private String[][] datos;

    public Sign_Juez() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign__juez, container, false);
        requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        btnIngresoJuez = (Button) view.findViewById(R.id.btnIngresoJuez);
        OTP = (EditText) view.findViewById(R.id.etxOtp);
        clave();
        btnIngresoJuez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar(OTP.getText().toString());
            }
        });
        return view;
    }

    private void clave() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url.url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject object = jsonObject.getJSONObject("juez");
                    JSONArray clave= (JSONArray) object.get("clave");
                    JSONArray partido= (JSONArray) object.get("idPartidos");
                    if(clave.length()==partido.length()){
                        datos=new String[2][clave.length()];
                        for(int i=0;i<datos.length;i++){
                            for (int c=0;c<clave.length();c++){
                                if(i==0){
                                    datos[i][c]=clave.getString(c);
                                }else {
                                    datos[i][c]=partido.getString(c);
                                }
                            }
                        }
                    }
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

    private void validar(String clave) {
        for (int f=0;f<2;f++){
            for (int c=0;c<datos[f].length;c++){
                String n=datos[f][c];
                if(n.equals(clave)){
                    if (damm.validate(clave)){
                        String partido= partido(f,c);
                        Intent intent = new Intent(getContext().getApplicationContext(), Score_Board.class);
                        intent.putExtra("id",partido);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(),"No es un numero valido",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    private String partido(int f, int c){
            f=f+1;
            return datos[f][c];
    }
}