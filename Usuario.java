
public class Usuario {
    private String email;
    private String senha;
    private String nick;

    public Usuario(String email, String nick_name, String senha){
        this.nick =nick_name;
        this.email=email;
        this.senha=senha;

    }

    public String getEmail() {
        return email;
    }

    public String getNick() {
        return nick;
    }

    public String getSenha() {
        return senha;
    }



}
