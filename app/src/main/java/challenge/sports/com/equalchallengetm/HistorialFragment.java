package challenge.sports.com.equalchallengetm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {
    private Fragment fragment;
    private Button mTorneos,hTorneos;
    public HistorialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_historial, container, false);
         mTorneos= (Button) view.findViewById(R.id.mTorneos);
        hTorneos= (Button) view.findViewById(R.id.hTorneos);
        try {
            fragment= new MisTorneosFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag,fragment).commit();
            color(1);
            mTorneos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment= new MisTorneosFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag,fragment).commit();
                    color(1);

                }
            });
            hTorneos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment= new HistorialTorneoFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag,fragment).commit();
                    color(2);
                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(),""+e,Toast.LENGTH_SHORT).show();
        }

        return view;
    }
private void color(int b){
    switch (b){
        case 1:
            mTorneos.setBackground(getResources().getDrawable(R.drawable.color_on));
            hTorneos.setBackground(getResources().getDrawable(R.drawable.boton_off));

            break;
        case 2:
            mTorneos.setBackground(getResources().getDrawable(R.drawable.boton_off));
            hTorneos.setBackground(getResources().getDrawable(R.drawable.color_on));
            break;
    }

}

}
