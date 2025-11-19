import java.util.*;
import java.io.*;

public class UsuarioController{
    private HashMap<String, Usuario>usuarios;
    private Usuario usuarioLogado;
    private static final String ARQUIVO_USUARIOS= "usuarios.dat";

    public UsuarioController() {
        this.usuarios= new HashMap<>();
        this.usuarioLogado= null;
        carregarUsuarios();
    }

    public boolean cadastrar(String email, String nick, String senha) {
        if (usuarios.containsKey(email)) {
            return false;
        }
        usuarios.put(email,new Usuario(email, nick, senha));
        salvarUsuarios();
        return true;
    }

    public boolean login(String email, String senha) {
        Usuario usuario= usuarios.get(email);
        if (usuario!= null && usuario.getSenha().equals(senha)) {
            usuarioLogado= usuario;
            return true;
        }
        return false;
    }

    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }

    private void salvarUsuarios() {
        try (ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(ARQUIVO_USUARIOS))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    private void carregarUsuarios() {
        try (ObjectInputStream in= new ObjectInputStream(new FileInputStream(ARQUIVO_USUARIOS))) {
            usuarios= (HashMap<String, Usuario>) in.readObject();
        } catch (FileNotFoundException e) {

            usuarios= new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            usuarios=new HashMap<>();
        }
    }
}