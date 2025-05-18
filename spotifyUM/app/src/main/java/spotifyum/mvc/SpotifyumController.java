package spotifyum.mvc;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import spotifyum.album.Album;
import spotifyum.exceptions.*;
import spotifyum.musica.Musica;
import spotifyum.playlist.Playlist;
import spotifyum.utilizador.Utilizador;

/**
 * Controlador do SpotifyUM.
 * Responsável por ser a ponte entre o model e a view.
 */
public class SpotifyumController {
    /** Model do SpotifyUm */
    private SpotifyumModel model;

    /** View do SpotifyUm */
    private SpotifyumView view;

    /**
     * Construtor do controlador do SpotifyUM.
     * @param model O modelo do SpotifyUM
     */
    public SpotifyumController(SpotifyumModel model){
        this.model = model;
    }

    /**
     * Define a view do controlador.
     * @param view A view do SpotifyUM
     */
    public void setView(SpotifyumView view){
        this.view = view;
    }

    /*
    ----------------------------------------------------------------------------
    ---------------------------Opcoes do Utilizador-----------------------------
    ----------------------------------------------------------------------------
    */

    /**
     * Verifica se um utilizador existe.
     * 
     * @param id ID do utilizador
     * @return true se o utilizador existir, false caso contrário
     */
    public boolean existeUtilizador(String id){
        return model.existeUtilizador(id);
    }

    /**
     * Mostra as informações de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void informacoes(String utilizadorId){
        try{
            model.informacoes(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao obter informações do utilizador: " + e.getMessage());
        }
    }

    /**
     * Mostra o histórico (de músicas) de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void verHistorico(String utilizadorId){
        try{
            model.verHistorico(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ver o histórico: " + e.getMessage());
        }
    }

    /**
     * Mostra o histórico de artistas de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void verHistoricoArtistas(String utilizadorId){
        try{
            model.verHistoricoArtistas(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ver o histórico de artistas: " + e.getMessage());
        }
    }

    /**
     * Reproduz uma música.
     * 
     * @param utilizadorId ID do utilizador que reproduz a música
     * @param musicaId Música a reproduzir
     */
    public void ouvirMusica(String utilizadorId, String musicaId){
        try{
            model.ouvirMusica(utilizadorId, musicaId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ouvir a música: " + e.getMessage());
        }
    }

    /**
     * Reproduz um álbum.
     * 
     * @param utilizadorId ID do utilizador que reproduz o álbum
     * @param albumId Álbum a reproduzir
     */
    public void ouvirAlbum(String utilizadorId, String albumId){
        try{
            model.ouvirAlbum(utilizadorId, albumId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ouvir o álbum: " + e.getMessage());
        }
    }

    /**
     * Reproduz uma playlist.
     * 
     * @param utilizadorId ID do utilizador que reproduz a playlist
     * @param playlistId Playlist a reproduzir
     */
    public void ouvirPlaylist(String utilizadorId, String playlistId){
        try{
            model.ouvirPlaylist(utilizadorId, playlistId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ouvir a playlist: " + e.getMessage());
        }
    }

    /**
     * Método para criar uma playlist personalizada.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da playlist
     * @param muicasIds IDs das músicas a adicionar à playlist
     * @param publica Indica se a playlist é pública
     */
    public void criarPlaylistPersonalizada(String utilizadorId, String nome, String muicasIds, String publica){
        try{
            model.criarPlaylistPersonalizada(utilizadorId, nome, muicasIds, publica);
        }catch(EntidadeNaoExisteException | PlanoException e){
            System.out.println("Erro ao criar a playlist personalizada: " + e.getMessage());
        }
    }

    /**
     * Método para adicionar um álbum à biblioteca de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param titulo Título do álbum
     */
    public void adicionarAlbumBiblioteca(String utilizadorId, String titulo){
        try{
            model.adicionarAlbumBiblioteca(utilizadorId, titulo);
        }catch(EntidadeNaoExisteException | PlanoException e){
            System.out.println("Erro ao adicionar o álbum: " + e.getMessage());
        }
    }

    /**
     * Método para adicionar uma playlist à biblioteca de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da playlist
     */
    public void adicionarPlaylistBiblioteca(String utilizadorId, String nome){
        try{
            model.adicionarPlaylistBiblioteca(utilizadorId, nome);
        }catch(EntidadeNaoExisteException | PlanoException | PublicException e){
            System.out.println("Erro ao adicionar a playlist à biblioteca: " + e.getMessage());
        }
    }

    /**
     * Método para alterar o plano de subscrição de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param plano Novo plano de subscrição
     */
    public void alterarPlano(String utilizadorId, String plano){
        try{
            model.alterarPlano(utilizadorId, plano);
        }catch(EntidadeNaoExisteException | PlanoException e){
            System.out.println("Erro ao alterar o plano: " + e.getMessage());
        }
    }
    
    /**
     * Método para verificar se um utilizador tem um plano premium base.
     * 
     * @param utilizadorId ID do utilizador
     * @return true se o utilizador tiver um plano premium base, false caso contrário
     */
    public boolean temPlanoPremiumBase(String utilizadorId){
        try{
            return model.temPlanoPremiumBase(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao verificar o plano: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para verificar se um utilizador tem um plano premium top.
     * 
     * @param utilizadorId ID do utilizador
     * @return true se o utilizador tiver um plano premium top, false caso contrário
     */
    public boolean temPlanoPremiumTop(String utilizadorId){
        try{
            return model.temPlanoPremiumTop(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao verificar o plano: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para mostrar as playlists da biblioteca de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void verMinhasPlaylists(String utilizadorId){
        try{
            model.verMinhasPlaylists(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ver as playlists: " + e.getMessage());
        }
    }

    /**
     * Método para mostrar os álbuns da biblioteca de um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void verBibliotecaAlbuns(String utilizadorId){
        try{
            model.verBibliotecaAlbuns(utilizadorId);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao ver a biblioteca de álbuns: " + e.getMessage());
        }
    }

    /**
     * Método para criar uma lista de favoritos para um utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da lista de favoritos
     */
    public void criaListaFavoritos(String utilizadorId, String nome){
        model.criaListaFavoritos(utilizadorId, nome);
    }

    /**
     * Método para criar uma lista de favoritos com base no tempo máximo.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da lista de favoritos
     * @param tempoMax Tempo máximo da lista de favoritos
     */
    public void criaListaFavoritosTempo(String utilizadorId, String nome, int tempoMax){
        model.criaListaFavoritosTempo(utilizadorId, nome, tempoMax);
    }

    /**
     * Método para criar uma lista de favoritos só com músicas explícitas.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da lista de favoritos
     */
    public void criaListaFavoritosExplicita(String utilizadorId, String nome){
        model.criaListaFavoritosExplicita(utilizadorId, nome);
    }

    /*
    ----------------------------------------------------------------------------
    --------------------------Opcoes do Administrador---------------------------
    ----------------------------------------------------------------------------
    */

    /**
     * Método para adicionar um utilizador.
     * 
     * @param id ID do utilizador
     * @param nome Nome do utilizador
     * @param email Email do utilizador
     * @param morada Morada do utilizador
     * @param plano Plano de subscrição do utilizador
     */
    public void adicionarUtilizador(String id, String nome, String email, String morada, String plano){
        try{
            Utilizador u = model.criaUtilizador(id, nome, email, morada, plano);
            model.adicionarUtilizador(u);
        }catch(PlanoException | EntidadeExisteException e){
            System.out.println("Erro ao adicionar o utilizador: " + e.getMessage());
        }
    }

    /**
     * Método para adicionar uma música.
     * 
     * @param id ID da música
     * @param nome Nome da música
     * @param artista Artista da música
     * @param editora Editora da música
     * @param letra Letra da música
     * @param partitura Partitura da música
     * @param genero Gênero da música
     * @param duracao Duração da música
     * @param tipo Tipo da música
     */
    public void adicionarMusica(String id, 
                                String nome, 
                                String artista, 
                                String editora, 
                                String letra, 
                                String partitura, 
                                String genero, 
                                String duracao, 
                                String tipo){
        
        try{
            Musica m = model.criaMusica(id, nome, artista, editora, letra, partitura, genero, duracao, tipo);
            model.adicionarMusica(m);
        }catch(OpcaoException | EntidadeExisteException e){
            System.out.println("Erro ao adicionar a música: " + e.getMessage());
        }
    }

    /**
     * Método para adicionar um álbum.
     * 
     * @param titulo Título do álbum
     * @param musicasIds IDs das músicas do álbum
     */
    public void adicionarAlbumAdmin(String titulo, String musicasIds){
        try{
            Album a = model.criaAlbum(titulo, musicasIds);
            model.adicionarAlbum(a);
        }catch(EntidadeExisteException | EntidadeNaoExisteException e){
            System.out.println("Erro ao adicionar o álbum: " + e.getMessage());
        }
    }

    /**
     * Método para gerar uma playlist aleatória.
     * 
     * @param nome Nome da playlist
     * @param numMusicas Número de músicas na playlist
     */
    public void gerarPlaylistAleatoria(String nome, int numMusicas) {
        List<String> arrayIds = model.geraMusicasAleatorias(numMusicas);
    
        String resultado = String.join(";", arrayIds);
    
        try {
            Playlist p = model.criaPlaylist(nome, resultado, "", "", "", "", "", "Aleatoria");
            model.adicionarPlaylist(p);
        } catch (EntidadeNaoExisteException | EntidadeExisteException | OpcaoException e) {
            System.out.println("Erro ao criar a playlist aleatória: " + e.getMessage());
        }
    }
    
    /**
     * Método para gerar uma playlist com base no tempo máximo e género.
     * 
     * @param nome Nome da playlist
     * @param tempoMax Tempo máximo da playlist
     * @param genero Género da playlist
     */
    public void gerarPlaylistTempoGenero(String nome, int tempoMax, String genero){
        List<String> idsSelecionados = new ArrayList<>();
        int tempoTotal = model.geraMusicasTempoGenero(idsSelecionados, tempoMax, genero);

        String resultado = String.join(";", idsSelecionados);

        try{
            Playlist p = model.criaPlaylist(nome, resultado, "", "", "", String.valueOf(tempoTotal), genero, "PlaylistTempoGenero");
            model.adicionarPlaylist(p);
        }catch(EntidadeNaoExisteException | EntidadeExisteException | OpcaoException e){
            System.out.println("Erro ao criar a playlist de tempo e género: " + e.getMessage());
        }
    }

    /**
     * Método para remover um utilizador do SpotifyUM.
     * 
     * @param id ID do utilizador a remover
     */
    public void removerUtilizador(String id){
        try{
            model.removerUtilizador(id);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao remover o utilizador: " + e.getMessage());
        }
    }

    /**
     * Método para remover uma música do SpotifyUM.
     * 
     * @param id ID da música a remover
     */
    public void removerMusica(String id){
        try{
            model.removerMusica(id);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao remover a música: " + e.getMessage());
        }
    }

    /**
     * Método para remover um álbum do SpotifyUM.
     * 
     * @param titulo Título do álbum a remover
     */
    public void removerAlbum(String titulo){
        try{
            model.removerAlbum(titulo);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao remover o álbum: " + e.getMessage());
        }
    }

    /**
     * Método para remover uma playlist do SpotifyUM.
     * 
     * @param nome Nome da playlist a remover
     */
    public void removerPlaylist(String nome){
        try{
            model.removerPlaylist(nome);
        }catch(EntidadeNaoExisteException e){
            System.out.println("Erro ao remover a playlist: " + e.getMessage());
        }
    }

    /**
     * Método para executar uma query conforme o número fornecido.
     * 
     * @param query Número da query a executar
     */
    public void executaQuery(int query){
        try{
            model.executarQuery(query);
        }catch(OpcaoException e){
            System.out.println("Erro ao executar a query: " + e.getMessage());
        }
    }

    /**
     * Método para executar uma query especial com base nas datas fornecidas.
     * 
     * @param inicio Data de início
     * @param fim Data de fim
     */
    public void executarQueryEspecial(LocalDate inicio, LocalDate fim){
        model.executarQueryEspecial(inicio, fim);
    }

    /**
     * Método para listar os utilizadores do SpotifyUM.
     */
    public void listarUtilizadores(){
        model.listarUtilizadores();
    }

    /**
     * Método para listar as músicas do SpotifyUM.
     */
    public void listarMusicas(){
        model.listarMusicas();
    }

    /**
     * Método para listar os álbuns do SpotifyUM.
     */
    public void listarAlbuns(){
        model.listarAlbuns();
    }

    /**
     * Método para listar as playlists do SpotifyUM.
     */
    public void listarPlaylists(){
        model.listarPlaylists();
    }
    
    /*
    ----------------------------------------------------------------------------
    ------------------------------------Extras----------------------------------
    ----------------------------------------------------------------------------
    */

    /**
     * Método para perguntar ao utilizador se quer ouvir uma música,
     * avançar para a próxima ou retornar à música anterior.
     * 
     * @param m Música a reproduzir
     * @return Ação escolhida pelo utilizador
     */
    public int pedirAcao(Musica m){
        int reply = view.pedirAcao(m);
        return reply;
    }

    /**
     * Método para perguntar ao utilizador se quer ouvir uma música,
     * em modo aleatório.
     * 
     * @return Ação escolhida pelo utilizador
     */
    public int pedirAleatorio(){
        int reply = view.pedirAleatorio();
        return reply;
    }

    /**
     * Método para guardar o estado atual do spotifyUM.
     * 
     * @param nomeFicheiro Nome do ficheiro para guardar o estado
     */
    public void guardarEstado(String nomeFicheiro){
            model.saveSpotifyUM(nomeFicheiro);
       
    }

    /**
     * Método para carregar o estado do spotifyUM a partir de um ficheiro.
     * 
     * @param nomeFicheiro Nome do ficheiro a carregar
     */
    public void carregarEstado(String nomeFicheiro){
            model.loadSpotifyUM(nomeFicheiro);
    }
}
