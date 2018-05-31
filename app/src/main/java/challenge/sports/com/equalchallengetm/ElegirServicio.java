package challenge.sports.com.equalchallengetm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ElegirServicio extends AppCompatActivity {
    private ImageButton btnSaqueJ1,btnSaqueJ2;
   public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public static final String NOM_SHARED = "challenge.sports.com.score.Instantaneo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_servicio);

        instancias();
    }

    private void instancias() {
        preferences = getSharedPreferences(NOM_SHARED,MODE_PRIVATE);
        editor = preferences.edit();

        btnSaqueJ1 = (ImageButton) findViewById(R.id.btnSaqueJ1);
        btnSaqueJ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("ServicioJ1",true);
                editor.putBoolean("ServicioJ2",false);
                editor.commit();
               finish();
            }
        });
        btnSaqueJ2= (ImageButton) findViewById(R.id.btnSaqueJ2);
        btnSaqueJ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("ServicioJ2",true);
                editor.putBoolean("ServicioJ1",false);
                editor.commit();
                finish();
            }
        });
    }

}
