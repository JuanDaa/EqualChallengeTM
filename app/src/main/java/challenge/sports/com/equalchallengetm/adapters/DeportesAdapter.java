package challenge.sports.com.equalchallengetm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import challenge.sports.com.equalchallengetm.R;
import challenge.sports.com.equalchallengetm.modelo.Deportes;

/**
 * Created by danslans on 22/08/16.
 */
public class DeportesAdapter extends RecyclerView.Adapter<DeportesAdapter.ViewHolder> {
    private Context context;
    private final List<Deportes> list;

    public DeportesAdapter(Context context, List<Deportes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_disciplinas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.deportes= list.get(position);
        holder.nombreDeporte.setText(list.get(position).toString());
        Glide.with(context).load(""+list.get(position).getImg()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
         return  list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreDeporte;
        public ImageButton imagen;
        public Deportes deportes;
        public ViewHolder(View itemView) {
            super(itemView);
            nombreDeporte= (TextView) itemView.findViewById(R.id.txtDeporte);
            imagen= (ImageButton) itemView.findViewById(R.id.img);

        }
    }
}
