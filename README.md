
Este projeto está sendo desenvolvido pelas alunas: Crislany Victória e Joana Clemente, como requisito para obtenção de nota na matéria de Laboratório de Programção, orientado pelo professor Marcos Vinícius Silva Bento do curso de Ciência da computação na instituição AFYA CENTRO localizada com Maceió - AL. 

PDF do relatório sobre o projeto Tech Music solicitado pelo professor Marcos Vinicius (anexado para download):
[Baixar o relatório em PDF](https://drive.google.com/file/d/1IsQHm91mgZk3xL1M8Y9sFoQSEUDDtrG3/view?usp=sharing)


DIAGRAMA DE CLASSES UML DO CÓDIGO
[Acessar Diagrama UML](https://lucid.app/lucidchart/d947edcd-7129-4d5d-b542-37aa4531b709/edit?viewport_loc=-1528%2C-165%2C6024%2C3208%2CHWEp-vi-RSFO&invitationId=inv_536f0bbf-5cf0-4e89-b8d0-d732052e08ff)
-> Este diagrama UML representando a estrutura completa de classes do Tech Music, desenvolvido no Lucidchart.



O projeto TECH MUSIC foi desenvolvido para funcionar como um aplicativo de música,tendo como inspiração o Deezer, permitindo que o usuário se cadastre,faça login, pesquise músicas e visualize informações obtidas pela API da Deezer. A interface gráfica simula a experiência de um app musical, com navegação entre músicas, álbuns e playlists, e tudo isso de uma forma controla e organizada.

---------------TECH MUSIC--------------

A estrutura do projeto foi feita da seguinte maneira:

-O main.java,é o ponto de entrada,resposável por iniciar a aplicação e chamar a interface gráfica de login/cadastro o Front.java.

-No Front.java, o usuario terá que cadastrar-se para poder entrar no sistema pelo sistema de login, depois de entrar o usuario será redirecionado a Deezertela.java, responsável por exibir as músicas disponíveis.

-O arquivo DeezerAPI.ja tem a responsabilidade de realizar a comunicação com a API pública da Deezer para enviar as informções solicitadas pelo usuario para Deezertela.java

-Os arquivos Musica.java, Album.java, Playlist.java e Usuario.java, cada classe contém seus próprios atributos (como nome da música, nome do álbum, nome da playlist, dados do usuário etc.) e possui um controller correspondente, que gerenciam a listagem, atualização e remoção de dados.

-O design escolhido foi uma foto do pinterest e depois editada para se encaixar no layout do projeto.
