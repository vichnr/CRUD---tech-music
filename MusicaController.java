import java.util.ArrayList;
import java.util.List;

public class MusicaController{
    private List<Musica>musicas;
    private int nextId;

    public MusicaController(){
        this.musicas=new ArrayList< >();
        this.nextId =1;


    }
    public void adicionarMusica(String titulo, String artista, int duracao, String genero){
        Musica musica =new Musica(nextId, titulo, artista, duracao, genero);
        musicas.add(musica);
        nextId++;
    }


    public List<Musica> listarMusicas(){
        return new ArrayList<>(musicas);
    }

    public Musica buscarMusicaPorId(int id) {
        for (Musica musica : musicas){
            if (musica.getId() == id){
                return musica;
            }
        }
        return null;
    }

    public List<Musica> buscarMusicasPorArtista(String artista){
        List<Musica> resultado = new ArrayList<>();
        for (Musica musica : musicas){
            if (musica.getArtista().toLowerCase().contains(artista.toLowerCase())) {
                resultado.add(musica);
            }
        }
        return resultado;
    }

    public List<Musica> buscarMusicasPorTitulo(String titulo){
        List<Musica> resultado=new ArrayList<>();
        for (Musica musica : musicas){
            if (musica.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                resultado.add(musica);
            }
        }
        return resultado;
    }

    public List<Musica> buscarMusicasPorGenero(String genero){
        List<Musica> resultado = new ArrayList<>();
        for (Musica musica : musicas) {
            if (musica.getGenero().toLowerCase().contains(genero.toLowerCase())){
                resultado.add(musica);
            }
        }
        return resultado;
    }

    public boolean atualizarMusica(int id, String novoTitulo, String novoArtista, int novaDuracao, String novoGenero){
        Musica musica=buscarMusicaPorId(id);
        if (musica != null){

            musicas.remove(musica);
            Musica musicaAtualizada= new Musica(id, novoTitulo, novoArtista, novaDuracao, novoGenero);
            musicas.add(musicaAtualizada);
            return true;
        }
        return false;
    }


    public boolean removerMusica(int id){
        Musica musica=buscarMusicaPorId(id);
        if(musica != null){
            musicas.remove(musica);
            return true;
        }
        return false;
    }


    public int getTotalMusicas(){
        return musicas.size();
    }


    public boolean existeMusica(int id){
        return buscarMusicaPorId(id)!= null;
    }

    public void limparTodasMusicas(){
        musicas.clear();
        nextId = 1;
    }
}