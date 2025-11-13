import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeezerAPI {

    public List<Musica> buscarMusicasArtista(String artista){
        return buscar("artist:\""+artista +"\"");
    }


    public List<Musica> buscarMusicaEspecifica(String musica){
        return buscar(musica);
    }


    public List<Musica> buscarMusicasAlbum(String artista,String album) {
        return buscar("artist:\""+artista +"\" album:\""+ album +"\"");
    }


    private List<Musica> buscar(String query){
        List<Musica>musicas =new ArrayList<>();


        try {
            String urlString = "https://api.deezer.com/search?q=" +query.replace(" ", "%20");
            URL url= new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner=new Scanner(conn.getInputStream());
            String response =scanner.useDelimiter("\\A").next();
            scanner.close();

            musicas =extrairMusicas(response);

        } catch (Exception e){
            System.out.println("Erro: " + e.toString());
        }

        return musicas;
    }

    private List<Musica> extrairMusicas(String json) {
        List<Musica> musicas = new ArrayList<>();

        String[] titulos =json.split("\"title\":\"");
        String[] artistas =json.split("\"name\":\"");
        String[] duracoes =json.split("\"duration\":");

        int count = 0;
        for(int i =1; i< titulos.length && count <10; i++){
            try {
                String titulo = titulos[i].split("\"")[0];
                String artista = artistas[i].split("\"")[0];
                String duracaoStr = duracoes[i].split(",")[0];
                int duracao = Integer.parseInt(duracaoStr);

                if (!titulo.contains("\\u")&& !artista.contains("\\u")){
                    Musica musica =new Musica(count + 1, titulo, artista, duracao, "");
                    musicas.add(musica);
                    count++;
                }
            } catch(Exception e){
            }
        }
        return musicas;
    }
}