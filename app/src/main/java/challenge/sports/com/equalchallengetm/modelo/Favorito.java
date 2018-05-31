package challenge.sports.com.equalchallengetm.modelo;

/**
 * Created by danslans on 22/08/16.
 */
public class Favorito {
    private String img;

    public Favorito(String img, String descripcion) {
        this.img = img;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String descripcion;
}
