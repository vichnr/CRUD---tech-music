import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Front {
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
                janela_principal.dispose();


                janela_usuario.setBounds(400,100,730,460);
                janela_usuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janela_usuario.setLayout(null);
                janela_usuario.setContentPane(new JLabel(new ImageIcon("cat_sem_nome.png")));

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
                nome_cadastro.setBounds(50,312,600,70);
                nome_cadastro.setForeground(Color.black);
                nome_cadastro.setFont(new Font("Arial",Font.BOLD,20));
                janela_usuario.add(nome_cadastro);


                JButton botao_cadastro =new JButton("Cadastrar-se");
                botao_cadastro.setFocusPainted(false);
                botao_cadastro.setBounds(50,370,170,25);
                janela_usuario.add(botao_cadastro);


                janela_usuario.setVisible(true);

            }
        });

        janela_principal.setVisible(true);
    }
}