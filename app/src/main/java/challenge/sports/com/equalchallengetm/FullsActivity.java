package challenge.sports.com.equalchallengetm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullsActivity extends AppCompatActivity {
    private Button button;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fulls);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),MenuLog.class);
                startActivity(intent);
            }
        });
        RelativeLayout relativeLayout= (RelativeLayout)findViewById(R.id.activity);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        y= event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float finalY = event.getY();
                        if(y>finalY){
                            if(y>1600){
                                Intent intent= new Intent(getApplicationContext(),MenuLog.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"up ",Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }
                return true;
            }
        });


    }

}
