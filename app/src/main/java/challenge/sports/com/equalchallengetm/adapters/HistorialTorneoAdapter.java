package challenge.sports.com.equalchallengetm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import challenge.sports.com.equalchallengetm.R;
import challenge.sports.com.equalchallengetm.modelo.HistorialTorneo;

/**
 * Created by danslans on 23/08/16.
 */
public class HistorialTorneoAdapter extends RecyclerView.Adapter<HistorialTorneoAdapter.ViewHolder> {
    private List<HistorialTorneo> list;
    private Context context;
    public HistorialTorneoAdapter(List<HistorialTorneo> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_historial_torneo,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.torneo= list.get(position);
        holder.nombre.setText(list.get(position).getNombre());
        holder.lugar.setText(list.get(position).getLugar());
        holder.fecha.setText(list.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nombre,fecha,lugar;
        public HistorialTorneo torneo;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre= (TextView) itemView.findViewById(R.id.nombreWinner);
            fecha= (TextView) itemView.findViewById(R.id.fechaWinner);
            lugar= (TextView) itemView.findViewById(R.id.lugar);
        }
    }
}
