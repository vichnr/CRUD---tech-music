import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;


public class Front {
    private Usuario usuarioLogado = null;
    HashMap<String, Usuario> usuarios = new HashMap<>();

    public Front(){
        JFrame janela_principal=new JFrame();
        JFrame janela_usuario= new JFrame();

        janela_principal.setBounds(400,100,730,460);
        janela_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela_principal.setLayout(null);
        janela_principal.setContentPane(new JLabel(new ImageIcon("cat.png")));

      ;

        JButton login_cadastro=new JButton("Login/cadastro");
        login_cadastro.setFocusPainted(false);
        login_cadastro.setBounds(230,170,275,50);
        janela_principal.add(login_cadastro);

        login_cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela_principal.setVisible(false);


                janela_usuario.setBounds(400,100,730,460);
                janela_usuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janela_usuario.setLayout(null);
                janela_usuario.setContentPane(new JLabel(new ImageIcon("cat_sem_nome.png")));

                JButton botao_voltar =new JButton("Voltar");
                botao_voltar.setFocusPainted(false);
                botao_voltar.setBounds(1,1,70,30);
                janela_usuario.add(botao_voltar);

                botao_voltar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        janela_usuario.setVisible(false);
                        janela_principal.setVisible(true);

                    }
                });

                JLabel login = new JLabel("Login");
                login.setBounds(50,30,600,70);
                login.setForeground(Color.black);
                login.setFont(new Font("Arial",Font.BOLD,56));
                janela_usuario.add(login);

                JLabel email = new JLabel("Email");
                email.setBounds(50,130,600,20);
                email.setForeground(Color.black);
                email.setFont(new Font("Arial",Font.BOLD,28));
                janela_usuario.add(email);

                JLabel senha = new JLabel("Senha");
                senha.setBounds(50,230,600,20);
                senha.setForeground(Color.black);
                senha.setFont(new Font("Arial",Font.BOLD,28));
                janela_usuario.add(senha);


                JTextField email_usuario = new JTextField();
                email_usuario.setBounds(50,160,400,35);
                janela_usuario.add(email_usuario);

                JTextField senha_usuario= new JTextField();
                senha_usuario.setBounds(50,260,400,35);

                janela_usuario.add(senha_usuario);

                JLabel nome_cadastro = new JLabel("Não possui conta?");
                nome_cadastro.setBounds(495,15,600,70);
                nome_cadastro.setForeground(Color.black);
                nome_cadastro.setFont(new Font("Arial",Font.BOLD,20));
                janela_usuario.add(nome_cadastro);

                JButton botao_entrar =new JButton("Entrar");
                botao_entrar.setFocusPainted(false);
                botao_entrar.setBounds(50,340,130,25);
                janela_usuario.add(botao_entrar);

                botao_entrar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String senhaText = senha_usuario.getText();
                        String emailText = email_usuario.getText();

                        if (usuarios.containsKey(emailText)) {
                            Usuario u = usuarios.get(emailText);

                            if (u.getSenha().equals(senhaText)) {
                                usuarioLogado = u;
                                janela_usuario.setVisible(false);
                                new DeezerTela(usuarioLogado);


                            }
                        }


                    }
                });


                JButton botao_cadastro =new JButton("Cadastrar-se");
                botao_cadastro.setFocusPainted(false);
                botao_cadastro.setBounds(500,70,170,25);
                janela_usuario.add(botao_cadastro);

                botao_cadastro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        janela_usuario.setVisible(false);
                        JFrame janela_cadastro= new JFrame();



                        janela_cadastro.setBounds(400,100,730,460);
                        janela_cadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        janela_cadastro.setLayout(null);
                        janela_cadastro.setContentPane(new JLabel(new ImageIcon("cat_sem_nome.png")));

                        JButton botao_voltar =new JButton("Voltar");
                        botao_voltar.setFocusPainted(false);
                        botao_voltar.setBounds(1,1,70,30);
                        janela_cadastro.add(botao_voltar);

                        botao_voltar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                janela_cadastro.setVisible(false);
                                janela_usuario.setVisible(true);

                            }
                        });

                        JLabel cadastro = new JLabel("Cadastro");
                        cadastro.setBounds(50,25,600,70);
                        cadastro.setForeground(Color.black);
                        cadastro.setFont(new Font("Arial",Font.BOLD,56));
                        janela_cadastro.add(cadastro);

                        JLabel email = new JLabel("Email");
                        email.setBounds(50,110,600,20);
                        email.setForeground(Color.black);
                        email.setFont(new Font("Arial",Font.BOLD,28));
                        janela_cadastro.add(email);

                        JTextField email_usuario = new JTextField();
                        email_usuario.setBounds(50,135,400,35);
                        janela_cadastro.add(email_usuario);

                        JLabel nick = new JLabel("Nome de Usuario");
                        nick.setBounds(50,180,600,32);
                        nick.setForeground(Color.black);
                        nick.setFont(new Font("Arial",Font.BOLD,28));
                        janela_cadastro.add(nick);

                        JTextField nick_name = new JTextField();
                        nick_name.setBounds(50,215,400,35);
                        janela_cadastro.add(nick_name);

                        JLabel senha = new JLabel("Senha");
                        senha.setBounds(50,260,600,30);
                        senha.setForeground(Color.black);
                        senha.setFont(new Font("Arial",Font.BOLD,28));
                        janela_cadastro.add(senha);

                        JTextField senha_usuario = new JTextField();
                        senha_usuario.setBounds(50,300,400,35);
                        janela_cadastro.add(senha_usuario);

                        JButton confirmar_cadastro =new JButton("Cadastrar-se");
                        confirmar_cadastro.setFocusPainted(false);
                        confirmar_cadastro.setBounds(50,370,170,25);
                        janela_cadastro.add(confirmar_cadastro);

                        confirmar_cadastro.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String emailText = email_usuario.getText();
                                String nickText = nick_name.getText();
                                String senhaText = senha_usuario.getText();

                                if (usuarios.containsKey(emailText)) {
                                    JOptionPane.showMessageDialog(janela_cadastro,
                                            "Esse email já está cadastrado!");
                                    return;
                                }


                                usuarios.put(emailText, new Usuario(emailText, nickText, senhaText));

                            }
                        });















                        janela_cadastro.setVisible(true);

                    }
                });


                janela_usuario.setVisible(true);

            }
        });

        janela_principal.setVisible(true);
    }
}