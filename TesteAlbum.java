import java.util.List;

public class TesteAlbum {
    public static void main(String[] args) {
        AlbumController controller= new AlbumController();
        DeezerAPI deezer= new DeezerAPI();

        List<Album> albunsAPI=deezer.buscarAlbunsArtista("madonna");

        for(Album album: albunsAPI) {

            if(album.getArtista().toLowerCase().contains("madonna")) {
                controller.criarAlbum(album.getTitulo(), album.getArtista(), "Pop");
            }
        }

        //ele só vai mostrar os albuns mais populares, n todos
        for(Album album :controller.listarAlbuns()){
            System.out.println(album.getTitulo()+ " - " + album.getArtista());
        }

    }
}