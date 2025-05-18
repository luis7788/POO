package spotifyum.mvc;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;

import spotifyum.album.Album;
import spotifyum.exceptions.*;
import spotifyum.musica.*;
import spotifyum.planos.*;
import spotifyum.playlist.*;
import spotifyum.utilizador.Utilizador;
import spotifyum.utilizador.Historico;

/** Classe Principal onde são armazenadas as entidades e métodos que interagem com os mesmos */
public class SpotifyumModel implements Serializable {
    private volatile transient SpotifyumController  controller;

    /** Map de utilizadores usando o id do utilizador como chave */
    private Map<String, Utilizador> utilizadores;

    /** Map de playlists usando o nome da playlist como chave */
    private Map<String, Playlist> playlists;

    /** Map de musicas usando o id da musica como chave */
    private Map<String, Musica> musicas;

    /** Map de albuns usando o titulo do album como chave */
    private Map<String, Album> albuns;


    /** Construtor vazio */
    public SpotifyumModel() {
        this.utilizadores = new HashMap<>();
        this.playlists = new HashMap<>();
        this.musicas = new HashMap<>();
        this.albuns = new HashMap<>();
    }

    /**
     * Método para definir o controller.
     * 
     * @param controller Controller do Spotifyum
     */
    public void setController(SpotifyumController controller) {
        this.controller = controller;
    }

    /*
    ---------------------------------------------------------------------------------------------
    ----------------------------------------Utilizadores-----------------------------------------
    ---------------------------------------------------------------------------------------------
    */
 

    /**
     * Cria um utilizador com os dados passados como parâmetros.
     * 
     * @param id ID do utilizador
     * @param nome Nome do utilizador
     * @param email Email do utilizador
     * @param morada Morada do utilizador
     * @param plano Plano do utilizador
     * 
     * @return O Utilizador criado
     * 
     * @throws PlanoException Se o plano não for válido
     */
    public Utilizador criaUtilizador(String id, String nome, String email, String morada, String plano)
        throws PlanoException {
        
        if(id == null || nome == null || email == null || morada == null || plano == null){
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos.");
        }

        switch (plano) {
            case "PlanoFree" -> {
                return new Utilizador(id, nome, email, morada, new PlanoFree());
            }
            case "PlanoPremiumBase" -> {
                return new Utilizador(id, nome, email, morada, new PlanoPremiumBase());
            }
            case "PlanoPremiumTop" -> {
                return new Utilizador(id, nome, email, morada, new PlanoPremiumTop());
            }
            default -> throw new PlanoException("Plano inválido.");
        }
    }
    /**
     * Retorna a lista de utilizadores
     * @return Lista de utilizadores
     */
    public List<Utilizador> getUtilizadores(){
        return new ArrayList<>(this.utilizadores.values());
    }


    /**
     * Adiciona um clone de um utilizador ao map de utilizadores
     * @param u Utilizador a adicionar
     * @throws EntidadeExisteException Se o utilizador já existir
     */
    public void adicionarUtilizador(Utilizador u) throws EntidadeExisteException{
        String id = u.getId();

        if(this.utilizadores.containsKey(id)){
            throw new EntidadeExisteException("Utilizador já existe.");
        }

        this.utilizadores.put(id, u.clone());
    }

    /**
     * Retorna um clone do utilizador com o id passado como parâmetro
     * @param id ID do utilizador
     * @return Utilizador com o id passado como parâmetro
     */
    public Utilizador getUtilizador(String id){
        return this.utilizadores.get(id).clone();
    }

    /**
     * Remove o utilizador com o id passado como parâmetro
     * @param id ID do utilizador
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public void removerUtilizador(String id) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(id)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }

        this.utilizadores.remove(id);
    }

    /**
     * Verifica se o utilizador com o id passado como parâmetro existe
     * @param id ID do utilizador
     * @return true se o utilizador existir, false caso contrário
     */
    public boolean existeUtilizador(String id){
        return this.utilizadores.containsKey(id);
    }

    /*
    ---------------------------------------------------------------------------------------------
    ----------------------------------------Playlists--------------------------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Cria uma playlist com os dados passados como parâmetros.
     * 
     * @param nome Nome da playlist
     * @param musicasIds IDs das músicas da playlist
     * @param publica Se a playlist é pública ou não
     * @param autor Autor da playlist
     * @param utilizador ID do utilizador dono da lista de favoritos
     * @param tempo Tempo da playlist
     * @param genero Género da playlist
     * @param tipo Tipo da playlist
     * 
     * @return A Playlist criada
     * 
     * @throws EntidadeNaoExisteException Se a música não existir
     * @throws OpcaoException Se o tipo de playlist não for válido
     */
    public Playlist criaPlaylist(String nome, 
                                 String musicasIds, 
                                 String publica, 
                                 String autor, 
                                 String utilizador,
                                 String tempo, 
                                 String genero, 
                                 String tipo)
        throws EntidadeNaoExisteException, OpcaoException {
        
        if (nome == null || musicasIds == null || publica == null || autor == null || tipo == null) {
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos.");
        }

        List<String> listaMusicas = new ArrayList<>(Arrays.asList(musicasIds.split(";")));
        List<Musica> musicasList = new ArrayList<>();
        for(String id : listaMusicas){
            if(existeMusica(id)){
                musicasList.add(this.musicas.get(id));
            }
            else{
                throw new EntidadeNaoExisteException("Musica não existe.");
            }
        }

        boolean ePublica = (publica.equals("sim"));

        switch (tipo) {
            case "Aleatoria" -> {
                return new PlaylistAleatoria(nome, musicasList);
            }
            case "Personalizada" -> {
                return new PlaylistPersonalizada(nome, musicasList, ePublica, autor);
            }
            case "ListaFavoritos" -> {
                if (!existeUtilizador(utilizador)) {
                    throw new EntidadeNaoExisteException("Utilizador não existe.");
                }
                return new ListaFavoritos(nome, musicasList, utilizador);
            }
            case "PlaylistTempoGenero" -> {
                return new PlaylistTempoGenero(nome, musicasList, Integer.parseInt(tempo), genero);
            }
            default -> throw new OpcaoException("Tipo de playlist inválido.");
        }
    }

    /**
     * Retorna a lista de playlists
     * @return Lista de playlists
     */
    public List<Playlist> getPlaylists(){
        return new ArrayList<>(this.playlists.values());
    }


    /**
     * Adiciona um clone de uma playlist ao map de playlists
     * @param p Playlist a adicionar
     * @throws EntidadeExisteException Se a playlist já existir
     */
    public void adicionarPlaylist(Playlist p) throws EntidadeExisteException {
        String nome = p.getNome();

        if (this.playlists.containsKey(nome)) {
            throw new EntidadeExisteException("Playlist já existe.");
        }

        this.playlists.put(nome, p.clone());
    }

    /**
     * Retorna um clone da playlist com o nome passado como parâmetro
     * @param nome Nome da playlist
     * @return Playlist com o nome passado como parâmetro
     */
    public Playlist getPlaylist(String nome) {
        return this.playlists.get(nome).clone();
    }

    /**
     * Remove a playlist com o nome passado como parâmetro
     * @param nome Nome da playlist
     * @throws EntidadeNaoExisteException Se a playlist não existir
     */
    public void removerPlaylist(String nome ) throws EntidadeNaoExisteException {
        if (!this.playlists.containsKey(nome)) {
            throw new EntidadeNaoExisteException("Playlist não existe.");
        }

        this.playlists.remove(nome);
    }

    /**
     * Verifica se a playlist com o nome passado como parâmetro existe
     * @param nome Nome da playlist
     * @return true se a playlist existir, false caso contrário
     */
    public boolean existePlaylist(String nome) {
        return this.playlists.containsKey(nome);
    }

    /*
    ---------------------------------------------------------------------------------------------
    ----------------------------------------Músicas----------------------------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Cria uma música com os dados passados como parâmetros.
     * 
     * @param id ID da música
     * @param nome Nome da música
     * @param artista Artista da música
     * @param editora Editora da música
     * @param letra Letra da música
     * @param partituraString Partitura da música
     * @param genero Género da música
     * @param duracao Duração da música
     * @param tipo Tipo da música
     * 
     * @return A Música criada
     * 
     * @throws OpcaoException Se o tipo de música não for válido
     */
    public Musica criaMusica(String id,
                             String nome,
                             String artista,
                             String editora,
                             String letra,
                             String partituraString,
                             String genero,
                             String duracao,
                             String tipo) throws OpcaoException{
        if(id == null || nome == null || artista == null || editora == null || letra == null || partituraString== null || genero == null || duracao == null || tipo == null){
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos.");
        }

        List<String> partitura = new ArrayList<>(Arrays.asList(partituraString.split(";")));

        switch (tipo) {
            case "Normal" -> {
                return new Musica(id, nome, artista, editora, letra, partitura, genero, Integer.parseInt(duracao));
            }
            case "Explicita" -> {
                return new MusicaExplicita(id, nome, artista, editora, letra, partitura, genero, Integer.parseInt(duracao));
            }
            case "Multimedia" -> {
                return new MusicaMultimedia(id, nome, artista, editora, letra, partitura, genero, Integer.parseInt(duracao));
            }
            default -> throw new OpcaoException("Tipo de música inválido.");
        }
    }

    /**
     * Retorna a lista de musicas
     * @return Lista de musicas
     */
    public List<Musica> getMusicas(){
        return new ArrayList<>(this.musicas.values());
    }

    /**
     * Adiciona um clone de uma música ao map de musicas
     * @param m Música a adicionar
     * @throws EntidadeExisteException Se a música já existir
     */
    public void adicionarMusica(Musica m) throws EntidadeExisteException{
        String id = m.getId();

        if(this.musicas.containsKey(id)){
            throw new EntidadeExisteException("Música já existe.");
        }

        this.musicas.put(id,m.clone());
    }

    /**
     * Retorna um clone da música com o id passado como parâmetro
     * @param id ID da música
     * @return Música com o id passado como parâmetro
     */
    public Musica getMusica(String id){
        return this.musicas.get(id).clone();
    }

    /**
     * Remove a música com o id passado como parâmetro
     * @param id ID da música
     * @throws EntidadeNaoExisteException Se a música não existir
     */
    public void removerMusica (String id) throws EntidadeNaoExisteException{
        if(!this.musicas.containsKey(id)){
            throw new EntidadeNaoExisteException("Música não existe.");
        }

        this.musicas.remove(id);
    }

    /**
     * Verifica se a música com o id passado como parâmetro existe
     * @param id ID da música
     * @return true se a música existir, false caso contrário
     */
    public boolean existeMusica(String id){
        return this.musicas.containsKey(id);
    }

    /*
    ---------------------------------------------------------------------------------------------
    -------------------------------------------Albuns--------------------------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Cria um álbum com os dados passados como parâmetros.
     * 
     * @param titulo Título do álbum
     * @param musicasIds IDs das músicas do álbum
     * 
     * @return O Álbum criado
     * 
     * @throws EntidadeNaoExisteException Se a música não existir
     */
    public Album criaAlbum(String titulo, String musicasIds) throws EntidadeNaoExisteException{

        if(titulo == null || musicasIds == null){
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos.");
        }
        
        List<String> listaMusicas = new ArrayList<>(Arrays.asList(musicasIds.split(";")));
        List<Musica> musicasList = new ArrayList<>();
        for(String id : listaMusicas){
            if(existeMusica(id)){
                musicasList.add(this.musicas.get(id));
            }
            else{
                throw new EntidadeNaoExisteException("Musica não existe.");
            }
        }
        return new Album(titulo, musicasList);
    }

    /**
     * Retorna a lista de albuns
     * @return Lista de albuns
     */
    public List<Album> getAlbuns(){
        return new ArrayList<>(this.albuns.values());
    }

    /**
     * Adiciona um clone de um álbum ao map de albuns
     * @param a Álbum a adicionar
     * @throws EntidadeExisteException Se o álbum já existir
     */
    public void adicionarAlbum(Album a) throws EntidadeExisteException{
        String titulo = a.getTitulo();

        if(this.albuns.containsKey(titulo)){
            throw new EntidadeExisteException("Album já existe.");
        }

        this.albuns.put(titulo, a.clone());
    }

    /**
     * Retorna um clone do álbum com o título passado como parâmetro
     * @param titulo Título do álbum
     * @return Álbum com o título passado como parâmetro
     */
    public Album getAlbum(String titulo){
        return this.albuns.get(titulo).clone();
    }

    /**
     * Remove o álbum com o título passado como parâmetro
     * @param titulo Título do álbum
     * @throws EntidadeNaoExisteException Se o álbum não existir
     */
    public void removerAlbum(String titulo) throws EntidadeNaoExisteException{
        if(!this.albuns.containsKey(titulo)){
            throw new EntidadeNaoExisteException("Album não existe.");
        }

        this.albuns.remove(titulo);
    }

    /**
     * Verifica se o álbum com o título passado como parâmetro existe
     * @param titulo Título do álbum
     * @return true se o álbum existir, false caso contrário
     */
    public boolean existeAlbum(String titulo){
        return this.albuns.containsKey(titulo);
    }

    /*
    ---------------------------------------------------------------------------------------------
    ---------------------------------Opções do Menu dos Utilizadores-----------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Método que imprime as informações do utilizador com o id passado como parâmetro.
     * 
     * @param utilizadorId ID do utilizador
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public void informacoes(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        System.out.println(getUtilizador(utilizadorId).toString());
    }

    /**
     * Método que imprime o histórico do utilizador com o id passado como parâmetro.
     * 
     * @param utilizadorId ID do utilizador
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public void verHistorico(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        System.out.println(this.utilizadores.get(utilizadorId).toStringHistorico());
    }

    /**
     * Método que imprime o histórico de artistas do utilizador com o id passado como parâmetro.
     * 
     * @param utilizadorId ID do utilizador
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public void verHistoricoArtistas(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        System.out.println(this.utilizadores.get(utilizadorId).toStringHistoricoArtistas());
    }

    /**
     * Método para ouvir uma música.
     * 
     * @param utilizadorId ID do utilizador
     * @param id ID da música
     * @throws EntidadeNaoExisteException Se o utilizador ou a música não existir
     */
    public void ouvirMusica(String utilizadorId, String id) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        if(!this.musicas.containsKey(id)){
            throw new EntidadeNaoExisteException("Música não existe.");
        }
        this.utilizadores.get(utilizadorId).ouvirMusica(this.musicas.get(id));
    }

    /**
     * Método para ouvir um álbum.
     * 
     * @param utilizadorId ID do utilizador
     * @param titulo Título do álbum
     * @throws EntidadeNaoExisteException Se o utilizador ou o álbum não existir
     */
    public void ouvirAlbum(String utilizadorId, String titulo) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        if(!this.albuns.containsKey(titulo)){
            throw new EntidadeNaoExisteException("Album não existe.");
        }
        this.utilizadores.get(utilizadorId).ouvirAlbum(this.albuns.get(titulo));
    }

    /**
     * Método para ouvir uma playlist.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da playlist
     * @throws EntidadeNaoExisteException Se o utilizador ou a playlist não existir
     */
    public void ouvirPlaylist(String utilizadorId, String nome) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        if(!this.playlists.containsKey(nome)){
            throw new EntidadeNaoExisteException("Playlist não existe.");
        }
        this.utilizadores.get(utilizadorId).ouvirPlaylist(this.playlists.get(nome),this.controller);
    }

    /**
     * Método para criar uma playlist personalizada.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da playlist
     * @param musicasIds IDs das músicas da playlist
     * @param publica Se a playlist é pública ou não
     * 
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     * @throws PlanoException Se o plano do utilizador não permitir a criação de playlists personalizadas
     */
    public void criarPlaylistPersonalizada(String utilizadorId, String nome, String musicasIds, String publica) 
        throws EntidadeNaoExisteException, PlanoException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }

        if(this.utilizadores.get(utilizadorId).getPlano() instanceof PlanoFree){
            throw new PlanoException("O PlanoFree não permite esta ação.");
        }

        try{
            Playlist p = criaPlaylist(nome, musicasIds, publica, utilizadorId, "-", "-", "-", "Personalizada");
            adicionarPlaylist(p);
            Playlist p2 = this.playlists.get(nome);
            ((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).adicionarPlaylistBiblioteca(p2);

        }catch(EntidadeNaoExisteException | EntidadeExisteException | OpcaoException e){
            System.out.println("Erro ao criar a playlist: " + e.getMessage());
        }
    }

    /**
     * Método para adicionar um álbum à biblioteca do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param titulo Título do álbum
     * 
     * @throws EntidadeNaoExisteException Se o utilizador ou o álbum não existir
     * @throws PlanoException Se o plano do utilizador não permitir a adição de álbuns à biblioteca
     */
    public void adicionarAlbumBiblioteca(String utilizadorId, String titulo)
        throws EntidadeNaoExisteException,PlanoException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        if(!this.albuns.containsKey(titulo)){
            throw new EntidadeNaoExisteException("Album não existe.");
        }
        if(this.utilizadores.get(utilizadorId).getPlano() instanceof PlanoFree){
            throw new PlanoException("O PlanoFree não permite esta ação.");
        }
        Album a = this.albuns.get(titulo);
        try{
            ((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).adicionarAlbumBiblioteca(a);
        }catch(EntidadeExisteException e){
            System.out.println("Erro ao adicionar o album à biblioteca: " + e.getMessage());
        }

    }

    /**
     * Método para adicionar uma playlist à biblioteca do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da playlist
     * 
     * @throws EntidadeNaoExisteException Se o utilizador ou a playlist não existir
     * @throws PlanoException Se o plano do utilizador não permitir a adição de playlists à biblioteca
     * @throws PublicException Se a playlist não for pública
     */
    public void adicionarPlaylistBiblioteca(String utilizadorId, String nome)
        throws EntidadeNaoExisteException, PlanoException, PublicException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        if(!this.playlists.containsKey(nome)){
            throw new EntidadeNaoExisteException("Playlist não existe.");
        }
        if(this.utilizadores.get(utilizadorId).getPlano() instanceof PlanoFree){
            throw new PlanoException("O PlanoFree não permite esta ação.");
        }
        if(this.playlists.get(nome).getPublica() == false){
            throw new PublicException("A playlist não é pública.");
        }
        Playlist p = this.playlists.get(nome);
        try{
            ((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).adicionarPlaylistBiblioteca(p);
        }catch(EntidadeExisteException e){
            System.out.println("Erro ao adicionar a playlist à biblioteca: " + e.getMessage());
        }
    }

    /**
     * Método para alterar o plano do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param plano Novo plano do utilizador
     * 
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     * @throws PlanoException Se o plano não for válido
     */
    public void alterarPlano(String utilizadorId, String plano) throws EntidadeNaoExisteException, PlanoException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }

        Utilizador u = this.utilizadores.get(utilizadorId);
        switch (plano) {
            case "PlanoFree" -> u.setPlano(new PlanoFree());
            case "PlanoPremiumBase" -> u.setPlano(new PlanoPremiumBase());
            case "PlanoPremiumTop" -> u.setPlano(new PlanoPremiumTop());
            default -> throw new PlanoException("Plano inválido.");
        }
    }


    /**
     * Método para verificar se o utilizador tem o plano Premium Base.
     * 
     * @param utilizadorId ID do utilizador
     * @return true se o utilizador tiver o plano Premium Base, false caso contrário
     * 
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public boolean temPlanoPremiumBase(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        return this.utilizadores.get(utilizadorId).getPlano() instanceof PlanoPremiumBase;
    }

    /**
     * Método para verificar se o utilizador tem o plano Premium Top.
     * 
     * @param utilizadorId ID do utilizador
     * @return true se o utilizador tiver o plano Premium Top, false caso contrário
     * 
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public boolean temPlanoPremiumTop(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        return this.utilizadores.get(utilizadorId).getPlano() instanceof PlanoPremiumTop;
    }

    /**
     * Método para imprimir as playlists da biblioteca do utilizador com o id passado como parâmetro.
     * @param utilizadorId ID do utilizador
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public void verMinhasPlaylists(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        System.out.println(((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).toStringMinhasPlaylists());
    }

    /**
     * Método para imprimir os albuns da biblioteca do utilizador com o id passado como parâmetro.
     * @param utilizadorId ID do utilizador
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public void verBibliotecaAlbuns(String utilizadorId) throws EntidadeNaoExisteException{
        if(!this.utilizadores.containsKey(utilizadorId)){
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
        System.out.println(((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).toStringAlbuns());
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Método para gerar uma lista de músicas favoritas
     * com base no histórico(de músicas) e do histórico de artistas do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @return Lista de IDs de músicas favoritas
     * 
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public List<String> geraListaFavoritos(String utilizadorId) throws EntidadeNaoExisteException {
        if (!this.utilizadores.containsKey(utilizadorId)) {
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
    
        Utilizador utilizador = this.utilizadores.get(utilizadorId);
        List<Historico> historicoMusicas = utilizador.getHistorico();
        Map<String, Integer> historicoArtistas = utilizador.getHistoricoArtistas();
    
        // Contador de géneros
        Map<String, Integer> contagemGeneros = new HashMap<>();
        for (Historico h : historicoMusicas) {
            Musica m = this.musicas.get(h.getIdMusica());
            if (m != null) {
                String genero = m.getGenero();
                contagemGeneros.put(genero, contagemGeneros.getOrDefault(genero, 0) + 1);
            }
        }
    
        // Top 3 géneros mais ouvidos
        List<String> topGeneros = contagemGeneros.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    
        // Top 3 artistas mais ouvidos
        List<String> topArtistas = historicoArtistas.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    
        // Selecionar músicas que coincidam com os géneros ou artistas mais ouvidos
        List<String> candidatos = this.musicas.values().stream()
            .filter(m -> topGeneros.contains(m.getGenero()) || topArtistas.contains(m.getArtista()))
            .map(Musica::getId)
            .distinct()
            .collect(Collectors.toList());
    
        Collections.shuffle(candidatos);
        return candidatos.stream().limit(10).collect(Collectors.toList());
    }
    
    /**
     * Método para criar uma lista de favoritos com base no histórico 
     * (de músicas) e do histórico de artistas do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da lista de favoritos
     */
    public void criaListaFavoritos(String utilizadorId, String nome){
        try{
            List<String> idsSelecionados = geraListaFavoritos(utilizadorId);
            String resultado = String.join(";", idsSelecionados);
            Playlist p = criaPlaylist(nome, resultado, "", "", utilizadorId, "", "", "ListaFavoritos");
            adicionarPlaylist(p);
            Playlist p2 = this.playlists.get(nome);
            ((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).adicionarPlaylistBiblioteca(p2);


        }catch(EntidadeNaoExisteException | EntidadeExisteException | OpcaoException e){
            System.out.println("Erro ao criar a lista de favoritos: " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Método para gerar uma lista de músicas favoritas com base no histórico
     * (de músicas) e do histórico de artistas do utilizador, mas com um tempo máximo.
     * 
     * @param utilizadorId ID do utilizador
     * @param tempoMax Tempo máximo da lista
     * @return Lista de IDs de músicas favoritas
     * 
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public List<String> geraListaFavoritosTempo(String utilizadorId, int tempoMax) throws EntidadeNaoExisteException {
        if (!this.utilizadores.containsKey(utilizadorId)) {
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
    
        Utilizador utilizador = this.utilizadores.get(utilizadorId);
        List<Historico> historicoMusicas = utilizador.getHistorico();
        Map<String, Integer> historicoArtistas = utilizador.getHistoricoArtistas();
    
        // Contar ocorrências de géneros
        Map<String, Integer> contagemGeneros = new HashMap<>();
        for (Historico h : historicoMusicas) {
            Musica m = this.musicas.get(h.getIdMusica());
            if (m != null) {
                String genero = m.getGenero();
                contagemGeneros.put(genero, contagemGeneros.getOrDefault(genero, 0) + 1);
            }
        }
    
        // Obter os 3 géneros mais ouvidos
        List<String> topGeneros = contagemGeneros.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    
        // Obter os 3 artistas mais ouvidos
        List<String> topArtistas = historicoArtistas.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    
        // Selecionar músicas que correspondam aos géneros ou artistas preferidos
        List<Musica> candidatos = this.musicas.values().stream()
            .filter(m -> topGeneros.contains(m.getGenero()) || topArtistas.contains(m.getArtista()))
            .distinct()
            .collect(Collectors.toList());
    
        // Baralhar a lista para dar aleatoriedade
        Collections.shuffle(candidatos);
    
        List<String> resultado = new ArrayList<>();
        int tempoAtual = 0;
    
        for (Musica m : candidatos) {
            if (tempoAtual + m.getDuracao() <= tempoMax) {
                resultado.add(m.getId());
                tempoAtual += m.getDuracao();
            }
        }
    
        return resultado;
    }

    /**
     * Método para criar uma lista de favoritos com base no histórico
     * (de músicas) e do histórico de artistas do utilizador, mas com um tempo máximo.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da lista de favoritos
     * @param tempoMax Tempo máximo da lista
     */
    public void criaListaFavoritosTempo(String utilizadorId, String nome, int tempoMax){
        try{
            List<String> idsSelecionados = geraListaFavoritosTempo(utilizadorId, tempoMax);
            String resultado = String.join(";", idsSelecionados);
            Playlist p = criaPlaylist(nome, resultado, "", "", utilizadorId, "", "", "ListaFavoritos");
            adicionarPlaylist(p);
            Playlist p2 = this.playlists.get(nome);
            ((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).adicionarPlaylistBiblioteca(p2);


        }catch(EntidadeNaoExisteException | EntidadeExisteException | OpcaoException e){
            System.out.println("Erro ao criar a lista de favoritos: " + e.getMessage());
        }
    }
    

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Método para gerar uma lista de músicas explícitas favoritas
     * com base no histórico (de músicas) e do histórico de artistas do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @return  Lista de IDs de músicas explícitas favoritas
     * @throws EntidadeNaoExisteException Se o utilizador não existir
     */
    public List<String> geraListaFavoritosExplicita(String utilizadorId) throws EntidadeNaoExisteException {
        if (!this.utilizadores.containsKey(utilizadorId)) {
            throw new EntidadeNaoExisteException("Utilizador não existe.");
        }
    
        Utilizador utilizador = this.utilizadores.get(utilizadorId);
        List<Historico> historicoMusicas = utilizador.getHistorico();
        Map<String, Integer> historicoArtistas = utilizador.getHistoricoArtistas();
    
        // Contar ocorrências de géneros
        Map<String, Integer> contagemGeneros = new HashMap<>();
        for (Historico h : historicoMusicas) {
            Musica m = this.musicas.get(h.getIdMusica());
            if (m != null) {
                String genero = m.getGenero();
                contagemGeneros.put(genero, contagemGeneros.getOrDefault(genero, 0) + 1);
            }
        }
    
        // Top 3 géneros mais ouvidos
        List<String> topGeneros = contagemGeneros.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    
        // Top 3 artistas mais ouvidos
        List<String> topArtistas = historicoArtistas.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    
        // Candidatas: músicas explícitas que correspondam aos géneros ou artistas
        List<Musica> candidatas = this.musicas.values().stream()
            .filter(m -> m instanceof MusicaExplicita &&
                         (topGeneros.contains(m.getGenero()) || topArtistas.contains(m.getArtista())))
            .collect(Collectors.toList());
    
        // Baralhar para dar aleatoriedade
        Collections.shuffle(candidatas);
    
        // Recolher até 10 IDs
        return candidatas.stream()
            .limit(10)
            .map(Musica::getId)
            .collect(Collectors.toList());
    }
    
    /**
     * Método para criar uma lista de favoritos explícitas com base no histórico
     * (de músicas) e do histórico de artistas do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @param nome Nome da lista de favoritos
     */
    public void criaListaFavoritosExplicita(String utilizadorId, String nome){
        try{
            List<String> idsSelecionados = geraListaFavoritosExplicita(utilizadorId);
            String resultado = String.join(";", idsSelecionados);
            Playlist p = criaPlaylist(nome, resultado, "", "", utilizadorId, "", "", "ListaFavoritos");
            adicionarPlaylist(p);
            Playlist p2 = this.playlists.get(nome);
            ((PlanoPremiumBase)this.utilizadores.get(utilizadorId).getPlano()).adicionarPlaylistBiblioteca(p2);


        }catch(EntidadeNaoExisteException | EntidadeExisteException | OpcaoException e){
            System.out.println("Erro ao criar a lista de favoritos: " + e.getMessage());
        }
    }
    
    

    /*
    ---------------------------------------------------------------------------------------------
    -----------------------------Opções do Menu dos Administradores------------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Método para gerar uma lista de músicas aleatórias.
     * 
     * @param numMusicas Número de músicas a gerar
     * @return Lista de IDs de músicas aleatórias
     */
    public List<String> geraMusicasAleatorias(int numMusicas) {
        List<String> ids = new ArrayList<>(this.musicas.keySet());
        Collections.shuffle(ids);
    
        int limite = Math.min(numMusicas, ids.size());
        return new ArrayList<>(ids.subList(0, limite));
    }
    
    /**
     * Método para gerar uma lista de músicas com base no tempo máximo e género.
     * 
     * @param idsSelecionados Lista de IDs selecionados
     * @param tempoMax Tempo máximo da lista
     * @param genero Género das músicas
     * @return Tempo total das músicas selecionadas
     */
    public int geraMusicasTempoGenero(List<String> idsSelecionados, int tempoMax, String genero) {
        List<Musica> candidatas = this.musicas.values().stream()
            .filter(m -> genero.equalsIgnoreCase(m.getGenero()))
            .collect(Collectors.toList());

        Collections.shuffle(candidatas);

        int tempoTotal = 0;

        for (Musica m : candidatas) {
            int duracao = m.getDuracao();
            if (tempoTotal + duracao <= tempoMax) {
                idsSelecionados.add(m.getId());
                tempoTotal += duracao;
            }
        }

        return tempoTotal;
    }

    /**
     * Método para imprimir todos os utilizadores.
     */
    public void listarUtilizadores(){
        System.out.print(this.toStringUser());
    }

    /**
     * Método para imprimir todas as playlists.
     */
    public void listarPlaylists(){
        System.out.print(this.toStringPlaylist());
    }

    /**
     * Método para imprimir todas as músicas.
     */
    public void listarMusicas(){
        System.out.print(this.toStringMusica());
    }

    /**
     * Método para imprimir todos os álbuns.
     */
    public void listarAlbuns(){
        System.out.print(this.toStringAlbum());
    }

    /*
    ---------------------------------------------------------------------------------------------
    -------------------------------------------Queries-------------------------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Método que executa uma query com o id da mesma passado como parâmetro.
     * 
     * @param id ID da query
     */
    public void executarQuery(int id) throws OpcaoException {
        switch (id) {
            case 1:
                musicaMaisReproduzida();
                break;
            case 2:
                artistaMaisEscutado();
                break;
            case 3:
                utilizadorMaisMusicasOuvidas();
                break;
            case 4:
                utilizadorMaisPontos();
                break;
            case 5:
                tipoMusicaMaisReproduzido();
                break;
            case 6:
                numeroPlaylistsPublicas();
                break;
            case 7:
                utilizadorMaisPlaylists();
                break;
            default:
                throw new OpcaoException("Query não existe.");
        }
    }

    /**
     * Método para executar a versão da query 3 que implica um intervalo de datas.
     * 
     * @param inicio Data de início
     * @param fim Data de fim
     */
    public void executarQueryEspecial(LocalDate inicio, LocalDate fim){
        utilizadorMaisMusicasOuvidasIntervalo(inicio, fim);
    }


    /**
     * Método que determina a música mais reproduzida.
     */
    public void musicaMaisReproduzida(){
        Musica maisReproduzida = null;
        int max = 0;

        for (Musica m : this.musicas.values()) {
            if (m.getReproducoes() > max) {
                max = m.getReproducoes();
                maisReproduzida = m;
            }
        }

        if (maisReproduzida != null) {
            System.out.println("Música mais reproduzida: " + maisReproduzida.getNome() +
                               " (ID: " + maisReproduzida.getId() + ", " +
                               max + " reproduções)");
        } else {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    /**
     * Método que determina o artista mais escutado.
     */
    public void artistaMaisEscutado(){
        Map<String, Integer> numReproducoes = new HashMap<>();

        for (Musica m : this.musicas.values()) {
            String artista = m.getArtista();
            int reproducoes = m.getReproducoes();

            numReproducoes.put(artista, numReproducoes.getOrDefault(artista, 0) + reproducoes);
        }

        String resultado = numReproducoes.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> "Artista mais escutado: " + entry.getKey() +
                          " (" + entry.getValue() + " reproduções)")
            .orElse("Nenhum artista encontrado.");

        System.out.println(resultado);
    }

    /**
     * Método que determina o utilizador com mais músicas ouvidas.
     */
    public void utilizadorMaisMusicasOuvidas(){
        int max = 0;
        Utilizador utilizadorMax = null;

        for (Utilizador u : this.utilizadores.values()) {
            if (u.getNumMusicas() > max) {
                max = u.getNumMusicas();
                utilizadorMax = u;
            }
        }

        if (utilizadorMax != null) {
            System.out.println("Utilizador com mais músicas ouvidas: " +
                               utilizadorMax.getId() + " (" + max + " músicas)");
        } else {
            System.out.println("Nenhum utilizador encontrado.");
        }
    }

    /**
     * Método que determina o utilizador com mais pontos.
     */
    public void utilizadorMaisPontos(){
        String id = null;
        double max = 0;

        for (Utilizador u : this.utilizadores.values()) {
            if (u.getPontos() > max) {
                max = u.getPontos();
                id = u.getId();
            }
        }

        if (id != null) {
            System.out.println("O utilizador com mais pontos é: " + id + " (" + max + " pontos)");
        } else {
            System.out.println("Nenhum utilizador encontrado.");
        }
    }

    /**
     * Método que determina o tipo de música mais reproduzido.
     */
    public void tipoMusicaMaisReproduzido(){
        Map<String, Integer> reproducoesPorGenero = new HashMap<>();

        for (Musica m : this.musicas.values()) {
            String genero = m.getGenero();
            int reproducoes = m.getReproducoes();

            reproducoesPorGenero.put(genero,
                reproducoesPorGenero.getOrDefault(genero, 0) + reproducoes);
        }

        String generoMaisReproduzido = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : reproducoesPorGenero.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                generoMaisReproduzido = entry.getKey();
            }
        }

        if (generoMaisReproduzido != null) {
            System.out.println("O tipo de música mais reproduzido é: " +
                               generoMaisReproduzido + " (" + max + " reproduções)");
        } else {
            System.out.println("Não há dados de reprodução disponíveis.");
        }
    }

    /**
     * Método que determina o número de playlists públicas.
     */
    public void numeroPlaylistsPublicas(){
        int contador = 0;

          for (Playlist p : this.playlists.values()) {
               if (p.getPublica()) {
                    contador++;
               }
          }

          System.out.println("Número de playlists públicas: " + contador);
    }

    /**
     * Método que determina o utilizador com mais playlists.
     */
    public void utilizadorMaisPlaylists(){
        int max = 0;
        Utilizador utilizadorMax = null;

        for (Utilizador u : this.utilizadores.values()) {
            if (!(u.getPlano() instanceof PlanoFree)) {
                PlanoPremiumBase plano = (PlanoPremiumBase) u.getPlano();
                int numPlaylists = plano.getMinhasPlaylist().size();

                if (numPlaylists > max) {
                    max = numPlaylists;
                    utilizadorMax = u;
                }
            }
        }

        if (utilizadorMax != null) {
            System.out.println("Utilizador com mais playlists: " +
                utilizadorMax.getId() + " (" + max + " playlists)");
        } else {
            System.out.println("Nenhum utilizador premium com playlists encontrado.");
        }
    }

    /**
     * Método que determina o utilizador com mais músicas ouvidas dentro de um período de tempo.
     * 
     * @param inicio Data de início
     * @param fim Data de fim
     */
    public void utilizadorMaisMusicasOuvidasIntervalo(LocalDate inicio, LocalDate fim) {
        int max = 0;
        Utilizador utilizadorMax = null;
    
        for (Utilizador u : this.utilizadores.values()) {
            int contador = 0;
    
            for (Historico h : u.getHistorico()) {
                LocalDate data = h.getDataReproducao();
                if ((data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim))) {
                    contador++;
                }
            }
    
            if (contador > max) {
                max = contador;
                utilizadorMax = u;
            }
        }
    
        if (utilizadorMax != null) {
            System.out.println("Utilizador com mais músicas ouvidas no intervalo [" + 
                inicio + " a " + fim + "]: " + utilizadorMax.getId() + " (" + max + " músicas)");
        } else {
            System.out.println("Nenhum utilizador com músicas ouvidas no intervalo indicado.");
        }
    }
    
    /*
    ---------------------------------------------------------------------------------------------
    -------------------------------------------toString------------------------------------------
    ---------------------------------------------------------------------------------------------
    */  

    /**
     * Método que devolve uma representação textual das informações do SpotifyUM.
     * 
     * @return String com os dados do SpotifyUM
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpotifyUM{\n");
        sb.append(this.toStringUser()).append("\n");
        sb.append(this.toStringPlaylist()).append("\n");
        sb.append(this.toStringMusica()).append("\n");
        sb.append(this.toStringAlbum()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Método que devolve uma representação textual dos utilizadores do SpotifyUM.
     * 
     * @return String com os dados dos utilizadores
     */
    public String toStringUser(){
        StringBuilder sb = new StringBuilder();
        sb.append("[UTILIZADORES]\n");
        sb.append("\n");
        for (Utilizador u : this.utilizadores.values()) {
            sb.append(u.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Método que devolve uma representação textual das playlists do SpotifyUM.
     * 
     * @return String com os dados das playlists
     */
    public String toStringPlaylist(){
        StringBuilder sb = new StringBuilder();
        sb.append("[PLAYLISTS]\n");
        sb.append("\n");
        for (Playlist p : this.playlists.values()) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Método que devolve uma representação textual das músicas do SpotifyUM.
     * 
     * @return String com os dados das músicas
     */
    public String toStringMusica(){
        StringBuilder sb = new StringBuilder();
        sb.append("[MUSICAS]\n");
        sb.append("\n");
        for (Musica m : this.musicas.values()) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Método que devolve uma representação textual dos álbuns do SpotifyUM.
     * 
     * @return String com os dados dos álbuns
     */
    public String toStringAlbum(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ALBUNS]\n");
        sb.append("\n");
        for (Album a : this.albuns.values()) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    /*
    ---------------------------------------------------------------------------------------------
    ----------------------------------------Load and Save----------------------------------------
    ---------------------------------------------------------------------------------------------
    */

    /**
     * Método que salva o SpotifyUM num ficheiro.
     * 
     * @param filePath Caminho do ficheiro
     */
    public void saveSpotifyUM(String filePath){
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filePath))) {
            o.writeObject(this);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o SpotifyUM: " + e.getMessage());
        }
    }

    /**
     * Método que carrega o SpotifyUM de um ficheiro.
     * 
     * @param filePath Caminho do ficheiro
     */
    public void loadSpotifyUM(String filePath){
        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(filePath))) {
            SpotifyumModel spotify = (SpotifyumModel) o.readObject();
            this.utilizadores = spotify.utilizadores;
            this.playlists = spotify.playlists;
            this.musicas = spotify.musicas;
            this.albuns = spotify.albuns;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o SpotifyUM: " + e.getMessage());
        }
    }
}
