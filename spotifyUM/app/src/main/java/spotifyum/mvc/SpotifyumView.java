package spotifyum.mvc;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import spotifyum.menu.*;
import spotifyum.musica.Musica;

/**
 * Classe responsável pela interação com o utilizador.
 * Esta classe apresenta menus e opções para o utilizador interagir com a aplicação.
 */
public class SpotifyumView {

    /** Controller do spotifyUM */
    private SpotifyumController controller;

    /** Scanner para ler a entrada do utilizador */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Construtor da classe SpotifyumView.
     * @param controller Controller do spotifyUM
     */
    public SpotifyumView(SpotifyumController controller){
        this.controller = controller;
    }

    //---------------------------------LOGIN------------------------------------

    /**
     * Método para fazer login como administrador.
     */
    public void loginAdministrador(){
        Menu menuAdministrador = criaMenuAdministrador();
        menuAdministrador.run();
    }

    /**
     * Método para fazer login como utilizador.
     * 
     * Verifica se o utilizador existe e, se sim, cria um menu para o utilizador.
     */
    public void loginUtilizador(){
        System.out.print("Introduza o seu ID de utilizador > ");
        String utilizadorId = scanner.nextLine();
        if(!controller.existeUtilizador(utilizadorId)){
            System.out.println("Erro no login: Utilizador não existe.");
            return;
        }
        Menu menuUtilizador = criaMenuUtilizador(utilizadorId);
        menuUtilizador.run();
    }

    /*
    ----------------------------------------------------------------------------
    ---------------------------Opcoes do Utilizador-----------------------------
    ----------------------------------------------------------------------------
    */

    /**
     * Método para mostrar as informações do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void informacoes(String utilizadorId){
        controller.informacoes(utilizadorId);
    }

    /**
     * Método para ouvir uma música.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void ouviMusica(String utilizadorId){
        System.out.print("Introduza o ID da música que deseja ouvir > ");
        String musicaId = scanner.nextLine();
        controller.ouvirMusica(utilizadorId, musicaId);
    }

    /**
     * Método para ouvir um álbum.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void ouvirAlbum(String utilizadorId){
        System.out.print("Introduza o título do álbum que deseja ouvir > ");
        String titulo = scanner.nextLine();
        controller.ouvirAlbum(utilizadorId, titulo);
    }

    /**
     * Método para ouvir uma playlist.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void ouvirPlaylist(String utilizadorId){
        System.out.print("Introduza o nome da playlist que deseja ouvir > ");
        String nome = scanner.nextLine();
        controller.ouvirPlaylist(utilizadorId, nome);
    }

    /**
     * Método para criar uma playlist personalizada.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void criarPlaylistPersonalizada(String utilizadorId){
        System.out.print("Introduza o nome da playlist personalizada > ");
        String nome = scanner.nextLine();
        System.out.print("Introduza os IDs das músicas (separados por ;) > ");
        String musicasIds = scanner.nextLine();
        System.out.print("A playlist é pública? (sim/nao) > ");
        String publica = scanner.nextLine();
        controller.criarPlaylistPersonalizada(utilizadorId, nome, musicasIds, publica);
    }

    /**
     * Método para gerar uma lista de favoritos.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void gerarListaFavoritos(String utilizadorId){
        Menu menuListaFavoritos = criaMenuListaFavoritos(utilizadorId);
        menuListaFavoritos.run();
    }

    /**
     * Método para criar uma lista de favoritos.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void criaListaFavoritos(String utilizadorId){
        System.out.print("Introduza o nome da playlist > ");
        String nome = scanner.nextLine();
        controller.criaListaFavoritos(utilizadorId, nome);
    }

    /**
     * Método para criar uma lista de favoritos com tempo máximo.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void criaListaFavoritosTempo(String utilizadorId){
        System.out.print("Introduza o nome da playlist > ");
        String nome = scanner.nextLine();
        System.out.print("Introduza o tempo máximo (em minutos) > ");
        int tempoMaximo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        controller.criaListaFavoritosTempo(utilizadorId, nome, tempoMaximo*60);
    }

    /**
     * Método para criar uma lista de favoritos só com músicas explícitas.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void criaListaFavoritosExplicita(String utilizadorId){
        System.out.print("Introduza o nome da playlist > ");
        String nome = scanner.nextLine();
        controller.criaListaFavoritosExplicita(utilizadorId, nome);
    }

    /**
     * Método para adicionar um álbum à biblioteca do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void adicionarAlbumBiblioteca(String utilizadorId){
        System.out.print("Introduza o título do álbum que deseja adicionar > ");
        String titulo = scanner.nextLine();
        controller.adicionarAlbumBiblioteca(utilizadorId, titulo);
    }

    /**
     * Método para adicionar uma playlist à biblioteca do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void adicionarPlaylistBiblioteca(String utilizadorId){
        System.out.print("Introduza o nome da playlist que deseja adicionar > ");
        String nome = scanner.nextLine();
        controller.adicionarPlaylistBiblioteca(utilizadorId, nome);
    }

    /**
     * Método para alterar o plano de subscrição do utilizador.
     * 
     * @param utilizadorId ID do utilizador
     */
    public void alterarPlano(String utilizadorId){
        System.out.print("Introduza o novo plano de subscrição (PlanoFree | PlanoPremiumBase | PlanoPremiumTop) > ");
        String plano = scanner.nextLine();
        controller.alterarPlano(utilizadorId, plano);
    }

    /*
    ----------------------------------------------------------------------------
    ---------------------------Opcoes do Administrador--------------------------
    ----------------------------------------------------------------------------
    */

    /**
     * Método para adicionar um utilizador.
     */
    public void adicionarUtilizador(){
        System.out.print("ID > ");
        String id = scanner.nextLine();
        System.out.print("Nome > ");
        String nome = scanner.nextLine();
        System.out.print("Email > ");
        String email = scanner.nextLine();
        System.out.print("Morada > ");
        String morada = scanner.nextLine();
        System.out.print("Plano (PlanoFree | PlanoPremiumBase | PlanoPremiumTop) > ");
        String plano = scanner.nextLine();
        controller.adicionarUtilizador(id, nome, email, morada, plano);
    }

    /**
     * Método para adicionar uma música.
     */
    public void adicionarMusica(){
        System.out.print("ID > ");
        String id = scanner.nextLine();
        System.out.print("Nome > ");
        String nome = scanner.nextLine();
        System.out.print("Artista > ");
        String artista = scanner.nextLine();
        System.out.print("Editora > ");
        String editora = scanner.nextLine();
        System.out.print("Letra > ");
        String letra = scanner.nextLine();
        System.out.print("Partitura (separada por ;) > ");
        String partitura = scanner.nextLine();
        System.out.print("Género > ");
        String genero = scanner.nextLine();
        System.out.print("Duração (em segundos) > ");
        String duracao = scanner.nextLine();
        System.out.print("Tipo (Normal | Explicita | Multimedia) > ");
        String tipo = scanner.nextLine();
        controller.adicionarMusica(id, nome, artista, editora, letra, partitura, genero, duracao, tipo);
    }

    /**
     * Método para adicionar um álbum.
     */
    public void adicionarAlbumAdmin(){
        System.out.print("Título > ");
        String titulo = scanner.nextLine();
        System.out.print("IDs das músicas (separados por ;) > ");
        String musicasIds = scanner.nextLine();
        controller.adicionarAlbumAdmin(titulo, musicasIds);
    }

    /**
     * Método para criar um menu de opções para criar uma playlist.
     */
    public void adicionarPlaylist(){
        Menu menuPlaylist = criaMenuPlaylist();
        menuPlaylist.run();
    }

    /**
     * Método para gerar uma playlist aleatória.
     */
    public void gerarPlaylistAleatoria(){
        System.out.print("Nome da playlist > ");
        String nome = scanner.nextLine();
        System.out.print("Número de músicas > ");
        int numMusicas = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        controller.gerarPlaylistAleatoria(nome, numMusicas);
    }

    /**
     * Método para gerar uma playlist com tempo máximo e género.
     */
    public void gerarPlaylistTempoGenero(){
        System.out.print("Nome da playlist > ");
        String nome = scanner.nextLine();
        System.out.print("Tempo máximo (em minutos) > ");
        int tempoMaximo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Género > ");
        String genero = scanner.nextLine();
        controller.gerarPlaylistTempoGenero(nome, tempoMaximo*60, genero);
    }

    /**
     * Método para remover um utilizador.
     */
    public void removerUtilizador(){
        System.out.print("ID do utilizador a remover > ");
        String id = scanner.nextLine();
        controller.removerUtilizador(id);
    }

    /**
     * Método para remover uma música.
     */
    public void removerMusica(){
        System.out.print("ID da música a remover > ");
        String id = scanner.nextLine();
        controller.removerMusica(id);
    }

    /**
     * Método para remover um álbum.
     */
    public void removerAlbum(){
        System.out.print("Título do álbum a remover > ");
        String titulo = scanner.nextLine();
        controller.removerAlbum(titulo);
    }

    /**
     * Método para remover uma playlist.
     */
    public void removerPlaylist(){
        System.out.print("Nome da playlist a remover > ");
        String nome = scanner.nextLine();
        controller.removerPlaylist(nome);
    }

    /**
     * Método que cria um menu com as opções de estatísticas.
     */
    public void verEstatisticas(){
        Menu menuEstatisticas = criaMenuEstatisticas();
        menuEstatisticas.run();
    }

    /**
     * Método para criar o menu de opções adicionais da query 3.
     */
    public void escolheOpcaoQuery(){
        Menu menuOpcaoQuery = criaMenuOpcaoQuery();
        menuOpcaoQuery.run();
    }

    /**
     * Método para ler um período de tempo para executar a query 3.
     */
    public void lerPeriodo(){
        System.out.print("Introduza a data de início (AAAA-MM-DD) > ");
        String dataInicio = scanner.nextLine();
        System.out.print("Introduza a data de fim (AAAA-MM-DD) > ");
        String dataFim = scanner.nextLine();
        try{
            LocalDate inicio = LocalDate.parse(dataInicio);
            LocalDate fim = LocalDate.parse(dataFim);
            controller.executarQueryEspecial(inicio, fim);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato AAAA-MM-DD.");
        }
    }

    /*
    ----------------------------------------------------------------------------
    ------------------------------Criação de Menus------------------------------
    ----------------------------------------------------------------------------
    */

    /**
     * Método para criar um menu de opções para a query 3.
     * 
     * @return Menu com as opções da query 3
     */
    public Menu criaMenuOpcaoQuery(){
        List<MenuOpcao> menuOpcaoQuery = new ArrayList<>();
        menuOpcaoQuery.add(new MenuOpcao("Desde sempre", () -> controller.executaQuery(3)));
        menuOpcaoQuery.add(new MenuOpcao("Num determinado período", () -> lerPeriodo()));
        return new Menu(menuOpcaoQuery);
    }

    /**
     * Método para criar um menu de opções para a lista de favoritos.
     * 
     * @param utilizadorId ID do utilizador
     * @return Menu com as opções da lista de favoritos
     */
    public Menu criaMenuListaFavoritos(String utilizadorId){
        List<MenuOpcao> menuListaFavoritos = new ArrayList<>();
        menuListaFavoritos.add(new MenuOpcao("Lista de Favoritos", () -> criaListaFavoritos(utilizadorId)));
        menuListaFavoritos.add(new MenuOpcao("Lista de Favoritos com Tempo", () -> criaListaFavoritosTempo(utilizadorId)));
        menuListaFavoritos.add(new MenuOpcao("Lista de Favoritos Explícita", () -> criaListaFavoritosExplicita(utilizadorId)));
        return new Menu(menuListaFavoritos);
    }

    /**
     * Método para criar um menu de opções para estatísticas.
     * 
     * @return Menu com as opções de estatísticas
     */
    public Menu criaMenuEstatisticas(){
        List<MenuOpcao> menuEstatisticas = new ArrayList<>();
        menuEstatisticas.add(new MenuOpcao("Música mais reproduzida", () -> controller.executaQuery(1)));
        menuEstatisticas.add(new MenuOpcao("Artista mais escutado", () -> controller.executaQuery(2)));
        menuEstatisticas.add(new MenuOpcao("Utilizador que mais músicas ouviu", () -> escolheOpcaoQuery()));
        menuEstatisticas.add(new MenuOpcao("Utilizador com mais pontos", () -> controller.executaQuery(4)));
        menuEstatisticas.add(new MenuOpcao("Tipo de música mais reproduuzido", () -> controller.executaQuery(5)));
        menuEstatisticas.add(new MenuOpcao("Número de playlists públicas", () -> controller.executaQuery(6)));
        menuEstatisticas.add(new MenuOpcao("Utilizador com mais playlists", () -> controller.executaQuery(7)));
        return new Menu(menuEstatisticas);
    }

    /**
     * Método para criar um menu de opções para criar uma playlist.
     * 
     * @return Menu com as opções de criação de playlist
     */
    public Menu criaMenuPlaylist(){
        List<MenuOpcao> menuPlaylist = new ArrayList<>();
        menuPlaylist.add(new MenuOpcao("Gerar Playlist Aleatória", () -> gerarPlaylistAleatoria()));
        menuPlaylist.add(new MenuOpcao("Gerar Playlist por Tempo e Genero", () -> gerarPlaylistTempoGenero()));
        return new Menu(menuPlaylist);
    }

    /**
     * Método para criar um menu de opções para o administrador.
     * 
     * @return Menu com as opções do administrador
     */
    public Menu criaMenuAdministrador(){
        List<MenuOpcao> menuAdministrador = new ArrayList<>();
        menuAdministrador.add(new MenuOpcao("Adicinoar Utilizador", () -> adicionarUtilizador()));
        menuAdministrador.add(new MenuOpcao("Adicionar Música", () -> adicionarMusica()));
        menuAdministrador.add(new MenuOpcao("Adicionar Álbum", () -> adicionarAlbumAdmin()));
        menuAdministrador.add(new MenuOpcao("Adicionar Playlist", () -> adicionarPlaylist()));
        menuAdministrador.add(new MenuOpcao("Remover Utilizador", () -> removerUtilizador()));
        menuAdministrador.add(new MenuOpcao("Remover Música", () -> removerMusica()));
        menuAdministrador.add(new MenuOpcao("Remover Álbum", () -> removerAlbum()));
        menuAdministrador.add(new MenuOpcao("Remover Playlist", () -> removerPlaylist()));
        menuAdministrador.add(new MenuOpcao("Listar Utilizadores", () -> controller.listarUtilizadores()));
        menuAdministrador.add(new MenuOpcao("Listar Músicas", () -> controller.listarMusicas()));
        menuAdministrador.add(new MenuOpcao("Listar Álbuns", () -> controller.listarAlbuns()));
        menuAdministrador.add(new MenuOpcao("Listar Playlists", () -> controller.listarPlaylists()));
        menuAdministrador.add(new MenuOpcao("Ver Estatísticas", () -> verEstatisticas()));
        return new Menu(menuAdministrador);
    }

    /**
     * Método para criar um menu de opções para o utilizador.
     * 
     * @param utilizadorId ID do utilizador
     * @return Menu com as opções do utilizador
     */
    public Menu criaMenuUtilizador(String utilizadorId){
        List<MenuOpcao> menuUtilizador = new ArrayList<>();
        menuUtilizador.add(new MenuOpcao("Informações do Utilizador", () -> informacoes(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ver histórico", () -> controller.verHistorico(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ver histórico de Artistas", () -> controller.verHistoricoArtistas(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ouvir Música", () -> ouviMusica(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ouvir Álbum", () -> ouvirAlbum(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ouvir Playlist", () -> ouvirPlaylist(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Criar Playlist Personalizada", () -> controller.temPlanoPremiumBase(utilizadorId) ,() -> criarPlaylistPersonalizada(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Gerar Lista de Favoritos", () -> controller.temPlanoPremiumTop(utilizadorId),() -> gerarListaFavoritos(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Adicionar Álbum à Biblioteca", () -> controller.temPlanoPremiumBase(utilizadorId),() -> adicionarAlbumBiblioteca(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Adicionar Playlist à Biblioteca", () -> controller.temPlanoPremiumBase(utilizadorId),() -> adicionarPlaylistBiblioteca(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ver as minhhas playlists", () -> controller.temPlanoPremiumBase(utilizadorId), () -> controller.verMinhasPlaylists(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Ver biblioteca de albuns", () -> controller.temPlanoPremiumBase(utilizadorId), () -> controller.verBibliotecaAlbuns(utilizadorId)));
        menuUtilizador.add(new MenuOpcao("Alterar o plano de subscrição", () -> alterarPlano(utilizadorId)));
        return new Menu(menuUtilizador);
    }

    /**
     * Método para criar o menu principal.
     * 
     * @return Menu com as opções principais
     */
    public Menu criaMenuPrincipal(){
        List<MenuOpcao> menuPrincipal = new ArrayList<>();
        menuPrincipal.add(new MenuOpcao("Entrar como Administrador", () -> loginAdministrador()));
        menuPrincipal.add(new MenuOpcao("Entrar como Utilizador", () -> loginUtilizador()));
        menuPrincipal.add(new MenuOpcao("Guardar estado da aplicação", () -> guardarEstado()));
        menuPrincipal.add(new MenuOpcao("Carregar um estado da aplicação", () -> carregarEstado()));
        return new Menu(menuPrincipal);
    }

    /**
     * Método para iniciar a aplicação.
     * 
     * Este método cria o menu principal e inicia a execução do mesmo.
     */
    public void run(){
        Menu menuPrincipal = criaMenuPrincipal();
        menuPrincipal.run();
    }

    /*
    ----------------------------------------------------------------------------
    ------------------------------------Extras----------------------------------
    ----------------------------------------------------------------------------
    */
    /**
     * Método para guardar o estado da aplicação.
     * 
     * O utilizador é solicitado a introduzir o nome do ficheiro onde o estado será guardado.
     */
    public void guardarEstado(){
        System.out.print("Introduza o nome do ficheiro para guardar o estado da aplicação > ");
        String nomeFicheiro = scanner.nextLine();
        controller.guardarEstado(nomeFicheiro);
        System.out.println("Estado da aplicação guardado no ficheiro: " + nomeFicheiro);
    }

    /**
     * Método para carregar o estado da aplicação.
     * 
     * O utilizador é solicitado a introduzir o nome do ficheiro de onde o estado será carregado.
     */
    public void carregarEstado(){
        System.out.print("Introduza o nome do ficheiro para carregar o estado da aplicação > ");
        String nomeFicheiro = scanner.nextLine();
        controller.carregarEstado(nomeFicheiro);
        System.out.println("Estado da aplicação carregado do ficheiro: " + nomeFicheiro);
    }

    /**
     * Método para pedir uma ação ao utilizador.
     * 
     * @param m Música a ser reproduzida
     * @return Ação escolhida pelo utilizador
     */
    public int pedirAcao(Musica m){
        System.out.println("Música a ser reproduzida: " + m.getNome());
        System.out.println("Escolha uma ação:");
        System.out.println("1 -> Ouvir");
        System.out.println("2 -> Avançar (⏭ )");
        System.out.println("3 -> Retroceder (⏮ )");
        System.out.print("> ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        return escolha;
    }

    /**
     * Método para pedir ao utilizador se deseja ouvir em modo aleatório.
     * 
     * @return 1 se sim, 2 se não
     */
    public int pedirAleatorio(){
        System.out.println("Quer ouvir em modo aleatório?");
        System.out.println("1 -> Sim");
        System.out.println("2 -> Não");
        System.out.print("> ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        return escolha;
    }
}
