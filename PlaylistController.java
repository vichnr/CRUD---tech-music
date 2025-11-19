import java.util.ArrayList;
import java.util.List;

public class PlaylistController{
    private List<Playlist>playlists;
    private int nextId;

    public PlaylistController(){
        this.playlists=new ArrayList<>();
        this.nextId =1;
    }

    public Playlist criarPlaylist(String nome){
        Playlist playlist= new Playlist(nextId,nome);
        playlists.add(playlist);
        nextId++;
        return playlist;
    }

    public void adicionarMusica(int playlistId,Musica musica){
        Playlist playlist= buscarPlaylistPorId(playlistId);
        if (playlist!= null){
            playlist.adicionarMusica(musica);
        }
    }

    public boolean removerMusicaPorPosicao(int playlistId, int posicao){
        Playlist playlist= buscarPlaylistPorId(playlistId);
        if (playlist!= null && posicao>= 0 && posicao <playlist.getMusicas().size()){
            Musica musica= playlist.getMusicas().get(posicao);
            playlist.removerMusica(musica);
            return true;
        }
        return false;
    }

    public boolean excluirPlaylist(int playlistId){
        Playlist playlist= buscarPlaylistPorId(playlistId);
        if (playlist!= null) {
            playlists.remove(playlist);
            return true;
        }
        return false;
    }

    public Playlist buscarPlaylistPorId(int id){
        for (Playlist playlist: playlists) {
            if (playlist.getId()== id) {
                return playlist;
            }
        }
        return null;
    }
    public List<Playlist> listarPlaylists(){
        return new ArrayList<>(playlists);
    }


}