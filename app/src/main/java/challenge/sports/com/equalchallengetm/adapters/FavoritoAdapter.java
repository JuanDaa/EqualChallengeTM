package challenge.sports.com.equalchallengetm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import challenge.sports.com.equalchallengetm.R;
import challenge.sports.com.equalchallengetm.modelo.Favorito;

/**
 * Created by danslans on 22/08/16.
 */
public class FavoritoAdapter extends RecyclerView.Adapter<FavoritoAdapter.ViewHolder> {
    private List<Favorito> list;
    private Context context;

    public FavoritoAdapter(List<Favorito> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favorito,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.favorito= list.get(position);
        holder.textdescrip.setText(list.get(position).toString());
        Glide.with(context).load(""+list.get(position).getImg()).into(holder.imgDepFavotito);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imgDepFavotito;
        private TextView textdescrip;
        private Favorito favorito;
        public ViewHolder(View itemView) {
            super(itemView);
            imgDepFavotito= (ImageButton) itemView.findViewById(R.id.imgFavorito);
            textdescrip= (TextView) itemView.findViewById(R.id.textFavorito);
        }
    }
}
