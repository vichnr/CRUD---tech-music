import java.util.ArrayList;
import java.util.List;

public class Playlist{
    private int id;
    private String nome;
    private List<Musica>musicas;

    public Playlist(int id, String nome){
        this.id =id;
        this.nome =nome;
        this.musicas =new ArrayList<>();
    }

    public void adicionarMusica(Musica musica){
        musicas.add(musica);
    }

    public void removerMusica(Musica musica){
        musicas.remove(musica);
    }

    public int getDuracaoTotal(){
        int total =0;
        for (Musica musica:musicas) {
            total+= musica.getDuracao();
        }
        return total;
    }

    public int getId(){return id; }
    public String getNome(){return nome;}
    public List<Musica>getMusicas(){return new ArrayList<>(musicas); }

    public void mostrarMusicas(){
        if (musicas.isEmpty()){
            System.out.println("Playlist vazia");
            return;
        }

        System.out.println("Musicas na playlist '" + nome + "':");
        for (int i=0; i< musicas.size(); i++) {
            Musica musica= musicas.get(i);
            String duracao= formatarDuracao(musica.getDuracao());
            System.out.println((i + 1) + ". " +musica.getTitulo()+ " - " + musica.getArtista()+ " (" + duracao + ")");
        }
        System.out.println("Duracao total: "+ formatarDuracao(getDuracaoTotal()));
    }

    private String formatarDuracao(int segundos){
        int minutos=segundos/60;
        int segundosRestantes=segundos %60;
        return minutos + ":" + String.format("%02d", segundosRestantes);
    }


    public String toString(){
        return String.format("Playlist [%d] %s | %d músicas", id, nome, musicas.size());

    }

}
