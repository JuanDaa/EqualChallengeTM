package challenge.sports.com.equalchallengetm.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by danslans on 22/08/16.
 */
public class Deportes{
    private System img;
    private System texto;

    public Deportes(System img, System texto) {
        this.img = img;
        this.texto = texto;
    }

    public System getImg() {
        return img;
    }

    public void setImg(System img) {
        this.img = img;
    }

    public System getTexto() {
        return texto;
    }

    public void setTexto(System texto) {
        this.texto = texto;
    }
}
