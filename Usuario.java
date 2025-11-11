import java.util.HashMap;

public class Usuario {
    private static HashMap<String, Usuario> usuarios= new HashMap<>();
    private String email;
    private String senha;
    private String nick_name;

    public Usuario(String email, String nick_name, String senha){
        this.nick_name =nick_name;
        this.email=email;
        this.senha=senha;

    }

    public void setUsuarios(){

    }

}
