import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PlaylistController{
    private Map<String, List<Playlist>> playlistsPorUsuario;
    private Map<String, Integer> nextIds;

    public PlaylistController(){
        this.playlistsPorUsuario = new HashMap<>();
        this.nextIds = new HashMap<>();
    }


    public Playlist criarPlaylist(String emailUsuario, String nome){

        List<Playlist> playlistsUsuario = playlistsPorUsuario
                .computeIfAbsent(emailUsuario, k -> new ArrayList<>());
        int nextId = nextIds.getOrDefault(emailUsuario, 1);
        Playlist playlist = new Playlist(nextId, nome);
        playlistsUsuario.add(playlist);

        nextIds.put(emailUsuario, nextId + 1);
        return playlist;
    }


    public void adicionarMusica(String emailUsuario, int playlistId, Musica musica){

        Playlist playlist = buscarPlaylistPorId(emailUsuario, playlistId);
        if (playlist!= null){
            playlist.adicionarMusica(musica);
        }
    }

    public boolean removerMusicaPorPosicao(String emailUsuario, int playlistId, int posicao){

        Playlist playlist = buscarPlaylistPorId(emailUsuario, playlistId);
        if (playlist!= null && posicao>= 0 && posicao <playlist.getMusicas().size()){
            Musica musica= playlist.getMusicas().get(posicao);
            playlist.removerMusica(musica);
            return true;
        }
        return false;
    }

    public boolean excluirPlaylist(String emailUsuario, int playlistId){

        List<Playlist> playlistsUsuario = playlistsPorUsuario.get(emailUsuario);
        if (playlistsUsuario != null) {
            Playlist playlist = buscarPlaylistPorId(emailUsuario, playlistId);
            if (playlist != null) {
                playlistsUsuario.remove(playlist);
                return true;
            }
        }
        return false;
    }

    public Playlist buscarPlaylistPorId(String emailUsuario, int id){

        List<Playlist> playlistsUsuario = playlistsPorUsuario.get(emailUsuario);
        if (playlistsUsuario != null) {
            for (Playlist playlist : playlistsUsuario) {
                if (playlist.getId() == id) {
                    return playlist;
                }
            }
        }
        return null;
    }

    public List<Playlist> listarPlaylists(String emailUsuario){
        return new ArrayList<>(playlistsPorUsuario.getOrDefault(emailUsuario, new ArrayList<>()));

    }


}