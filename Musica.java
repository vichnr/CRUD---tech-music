import java.io.Serializable;

public class Musica implements Serializable {
    private int id;
    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    public Musica(int id, String titulo, String artista, int duracao, String genero){
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getArtista(){
        return artista;
    }

    public int getDuracao(){
        return duracao;
    }

    public String getGenero(){
        return genero;
    }

    public String toString(){
        return "Musica{" +
                "id=" + id+
                ", titulo='" + titulo + '\''+
                ", artista='" + artista + '\'' +
                ", duracao=" + duracao +
                ", genero='" + genero + '\''+
                '}';
    }
}