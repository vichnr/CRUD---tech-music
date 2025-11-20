import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeezerTela extends JFrame {

    private DeezerAPI api = new DeezerAPI();
    private Usuario usuario;
    private PlaylistController playlistController;
    private MusicaController musicaController;
    private AlbumController albumController;
    private JList<String> playlistsList;
    private DefaultListModel<String> playlistsModel;
    private JTextField campoBusca;
    private JButton buscarBtn;
    private JList<String> resultadosList;
    private DefaultListModel<String> resultadosModel;
    private JButton novaPlaylistBtn;
    private JButton adicionarMusicaBtn;
    private JButton excluirPlaylistBtn;
    private JButton excluirMusicaBtn;
    private JList<String> musicasPlaylistList;
    private DefaultListModel<String> musicasPlaylistModel;
    private JLabel playlistSelecionadaLabel;
    private Playlist playlistAtual;


    public DeezerTela(Usuario usuario){
        this.usuario= usuario;
        this.playlistController= new PlaylistController();
        this.musicaController= new MusicaController();
        this.albumController= new AlbumController();

        setTitle("Tech Music - Bem-vindo, " + usuario.getNick() + "!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        try {
            ImageIcon icon= new ImageIcon("cat_sem_nome.png");
            setIconImage(icon.getImage());
        } catch (Exception e){
        }
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
    }

    private void inicializarComponentes(){
        playlistsModel= new DefaultListModel<>();
        playlistsList = new JList<>(playlistsModel);
        campoBusca= new JTextField(20);
        buscarBtn= new JButton("Buscar");
        resultadosModel= new DefaultListModel<>();
        resultadosList= new JList<>(resultadosModel);
        novaPlaylistBtn= new JButton("Nova Playlist");
        musicasPlaylistModel = new DefaultListModel<>();
        musicasPlaylistList =new JList<>(musicasPlaylistModel);
        adicionarMusicaBtn= new JButton("Adicionar à Playlist");
        excluirPlaylistBtn= new JButton("Excluir Playlist");
        excluirMusicaBtn = new JButton("Remover Música");
        playlistSelecionadaLabel = new JLabel("Selecione uma playlist:", JLabel.CENTER);
        carregarPlaylists();
    }

    private void configurarEventos() {
        buscarBtn.addActionListener(e -> buscarMusicas());
        campoBusca.addActionListener(e -> buscarMusicas());
        novaPlaylistBtn.addActionListener(e -> criarNovaPlaylist());
        playlistsList.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                visualizarPlaylistSelecionada();
            }
        });
        adicionarMusicaBtn.addActionListener(e -> adicionarMusicaPlaylist());
        excluirPlaylistBtn.addActionListener(e -> excluirPlaylistSelecionada());
        excluirMusicaBtn.addActionListener(e -> excluirMusicaSelecionada());
    }
    private void visualizarPlaylistSelecionada() {
        int index= playlistsList.getSelectedIndex();
        if (index != -1) {
            List<Playlist> playlistsUsuario = playlistController.listarPlaylists(usuario.getEmail());
            playlistAtual = playlistsUsuario.get(index);
            playlistSelecionadaLabel.setText("Playlist: " + playlistAtual.getNome());
            carregarMusicasPlaylist(playlistAtual);
        }
    }
    private void carregarMusicasPlaylist(Playlist playlist) {
        musicasPlaylistModel.clear();
        List<Musica> musicas = playlist.getMusicas();

        for (int i = 0; i< musicas.size(); i++) {
            Musica musica= musicas.get(i);
            String duracao= formatarDuracao(musica.getDuracao());
            musicasPlaylistModel.addElement((i + 1) + ". " + musica.getTitulo() +
                    " - " + musica.getArtista() + " (" + duracao + ")");
        }

        if (musicas.isEmpty()) {
            musicasPlaylistModel.addElement("Playlist vazia");
        }
    }

    private void adicionarMusicaPlaylist() {
        if (playlistAtual == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist primeiro!");
            return;
        }

        int musicaIndex = resultadosList.getSelectedIndex();
        if (musicaIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma música nos resultados!");
            return;
        }

        try {
            String termo = campoBusca.getText().trim();
            List<Musica>resultados = api.buscarMusicaEspecifica(termo);

            if (musicaIndex< resultados.size()) {
                Musica musicaSelecionada= resultados.get(musicaIndex);
                playlistController.adicionarMusica(usuario.getEmail(), playlistAtual.getId(), musicaSelecionada);
                carregarMusicasPlaylist(playlistAtual);
                carregarPlaylists();
                JOptionPane.showMessageDialog(this, "Música adicionada à playlist!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar música: " + ex.getMessage());
        }
    }

    private void excluirPlaylistSelecionada() {
        if (playlistAtual== null) {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para excluir!");
            return;
        }

        int confirm= JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir a playlist '" + playlistAtual.getNome() + "'?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm== JOptionPane.YES_OPTION) {
            playlistController.excluirPlaylist(usuario.getEmail(), playlistAtual.getId());
            carregarPlaylists();
            playlistAtual= null;
            playlistSelecionadaLabel.setText("Selecione uma playlist");
            musicasPlaylistModel.clear();
            JOptionPane.showMessageDialog(this, "Playlist excluída com sucesso!");
        }
    }

    private void excluirMusicaSelecionada() {
        if (playlistAtual== null) {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist primeiro!");
            return;
        }

        int musicaIndex= musicasPlaylistList.getSelectedIndex();
        if (musicaIndex== -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma música para remover!");
            return;
        }

        int confirm= JOptionPane.showConfirmDialog(this,
                "Remover esta música da playlist?",
                "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

        if (confirm== JOptionPane.YES_OPTION) {
            playlistController.removerMusicaPorPosicao(usuario.getEmail(), playlistAtual.getId(), musicaIndex);
            carregarMusicasPlaylist(playlistAtual);
            carregarPlaylists();
            JOptionPane.showMessageDialog(this, "Música removida da playlist!");
        }
    }

    private void criarNovaPlaylist() {
        String nomePlaylist= JOptionPane.showInputDialog(this,
                "Digite o nome da nova playlist:", "Nova Playlist", JOptionPane.QUESTION_MESSAGE);

        if (nomePlaylist!= null && !nomePlaylist.trim().isEmpty()) {
            Playlist novaPlaylist= playlistController.criarPlaylist(usuario.getEmail(), nomePlaylist.trim());
            carregarPlaylists();
            JOptionPane.showMessageDialog(this, "Playlist '" + nomePlaylist + "' criada com sucesso!");
        }
    }

    private void buscarMusicas() {
        String termo= campoBusca.getText().trim();
        if (!termo.isEmpty()) {
            try {
                System.out.println("Buscando: " + termo);
                List<Musica> resultados= api.buscarMusicaEspecifica(termo);

                resultadosModel.clear();
                for (Musica musica: resultados) {
                    String duracao= formatarDuracao(musica.getDuracao());
                    resultadosModel.addElement(musica.getTitulo() + " - " +
                            musica.getArtista() + " (" + duracao + ")");
                }

                if (resultados.isEmpty()) {
                    resultadosModel.addElement("Nenhum resultado encontrado");
                }

            } catch (Exception ex) {
                resultadosModel.clear();
                resultadosModel.addElement("Erro na busca: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private String formatarDuracao(int segundos) {
        int minutos= segundos / 60;
        int segundosRestantes= segundos % 60;
        return minutos + ":" + String.format("%02d", segundosRestantes);
    }

    private void carregarPlaylists() {
        playlistsModel.clear();
        List<Playlist> playlists= playlistController.listarPlaylists(usuario.getEmail());

        for (Playlist playlist: playlists) {
            playlistsModel.addElement(playlist.getNome() +
                    " (" + playlist.getMusicas().size() + " músicas)");
        }
    }

    private void configurarLayout() {
        setLayout(new BorderLayout());

        JPanel mainPanel= new JPanel(new GridLayout(1, 3, 10, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel playlistsPanel= new JPanel(new BorderLayout());
        playlistsPanel.setBorder(BorderFactory.createTitledBorder("MINHAS PLAYLISTS"));

        JPanel topoPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
        topoPanel.add(novaPlaylistBtn);
        topoPanel.add(excluirPlaylistBtn);

        JScrollPane playlistsScroll= new JScrollPane(playlistsList);

        playlistsPanel.add(topoPanel, BorderLayout.NORTH);
        playlistsPanel.add(playlistsScroll, BorderLayout.CENTER);

        JPanel musicasPanel= new JPanel(new BorderLayout());
        musicasPanel.setBorder(BorderFactory.createTitledBorder("MÚSICAS DA PLAYLIST"));

        musicasPanel.add(playlistSelecionadaLabel, BorderLayout.NORTH);

        JScrollPane musicasScroll= new JScrollPane(musicasPlaylistList);
        musicasPanel.add(musicasScroll, BorderLayout.CENTER);

        JPanel botoesMusicasPanel= new JPanel(new FlowLayout());
        botoesMusicasPanel.add(excluirMusicaBtn);
        musicasPanel.add(botoesMusicasPanel, BorderLayout.SOUTH);

        JPanel resultadosPanel= new JPanel(new BorderLayout());
        resultadosPanel.setBorder(BorderFactory.createTitledBorder("RESULTADOS DA BUSCA"));

        JScrollPane resultadosScroll= new JScrollPane(resultadosList);
        resultadosPanel.add(resultadosScroll, BorderLayout.CENTER);

        JPanel botoesResultadosPanel= new JPanel(new FlowLayout());
        botoesResultadosPanel.add(adicionarMusicaBtn);
        resultadosPanel.add(botoesResultadosPanel, BorderLayout.SOUTH);

        mainPanel.add(playlistsPanel);
        mainPanel.add(musicasPanel);
        mainPanel.add(resultadosPanel);

        JPanel terminalPanel=
                new JPanel(new FlowLayout());
        terminalPanel.setBorder(BorderFactory.createTitledBorder("Buscar Músicas"));
        terminalPanel.add(campoBusca);
        terminalPanel.add(buscarBtn);

        add(mainPanel, BorderLayout.CENTER);
        add(terminalPanel, BorderLayout.SOUTH);
    }



}
