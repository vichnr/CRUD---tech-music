import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class PlaylistController{
    private static Map<String, List<Playlist>> playlistsPorUsuario = new HashMap<>();
    private static Map<String, Integer> nextIds = new HashMap<>();
    private static final String ARQUIVO_PLAYLISTS = "playlists.dat";

    static {
        carregarPlaylists();
    }

    @SuppressWarnings("unchecked")
    private static void carregarPlaylists() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_PLAYLISTS))) {
            playlistsPorUsuario = (Map<String, List<Playlist>>) ois.readObject();
            nextIds = (Map<String, Integer>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void salvarPlaylists() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_PLAYLISTS))) {
            oos.writeObject(playlistsPorUsuario);
            oos.writeObject(nextIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlaylistController(){

    }

    public Playlist criarPlaylist(String emailUsuario, String nome){
        List<Playlist> playlistsUsuario = playlistsPorUsuario
                .computeIfAbsent(emailUsuario, k -> new ArrayList<>());
        int nextId = nextIds.getOrDefault(emailUsuario, 1);
        Playlist playlist = new Playlist(nextId, nome);
        playlistsUsuario.add(playlist);

        nextIds.put(emailUsuario, nextId + 1);
        salvarPlaylists();
        return playlist;
    }

    public void adicionarMusica(String emailUsuario, int playlistId, Musica musica){
        Playlist playlist = buscarPlaylistPorId(emailUsuario, playlistId);
        if (playlist != null){
            playlist.adicionarMusica(musica);
            salvarPlaylists();
        }
    }

    public boolean removerMusicaPorPosicao(String emailUsuario, int playlistId, int posicao){
        Playlist playlist = buscarPlaylistPorId(emailUsuario, playlistId);
        if (playlist != null && posicao >= 0 && posicao < playlist.getMusicas().size()){
            Musica musica = playlist.getMusicas().get(posicao);
            playlist.removerMusica(musica);
            salvarPlaylists();
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
                salvarPlaylists();
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