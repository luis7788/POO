package spotifyum.utilizador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spotifyum.album.Album;
import spotifyum.exceptions.PlanoException;
import spotifyum.exceptions.PublicException;
import spotifyum.musica.Musica;
import spotifyum.mvc.SpotifyumController;
import spotifyum.planos.PlanoPremiumTop;
import spotifyum.planos.PlanoSubscricao;
import spotifyum.playlist.Playlist;

/** Um utilizador do SpotifyUM */
public class Utilizador implements Serializable {
    
    /** ID único do utilizador */
    private String id;

    /** Nome do utilizador */
    private String nome;

    /** Email do utilizador */
    private String email;

    /** Morada do utilizador */
    private String morada;

    /** Pontos acumulados pelo utilizador ao reproduzir músicas */
    private double pontos;

    /** Plano de subscrição do utilizador */
    private PlanoSubscricao plano;

    /** Indica se o utilizador já recebeu o bónus de 100 pontos do plano Premium Top */
    private boolean recebeuBonusTop = false;

    /** Número de músicas ouvidas pelo utilizador */
    private int numMusicas;

    /** Lista de históricos de reprodução do utilizador com Id da música e data*/
    private List<Historico> historico;

    /** Mapa que associa artistas a números de reproduções ouvidos pelo utilizador */
    private Map<String, Integer> historicoArtistas;

    /** 
     * Construtor vazio 
     * 
     * Cria um utilizador com ID, nome, morada e email vazios.
     * Inicializa os pontos a 0.0 e o plano de subscrição a null.
     * 
     * @return O Utilizador criado
    */
    public Utilizador(){
        this.id = "";
        this.nome = "";
        this.morada = "";
        this.email = "";
        this.pontos = 0.0;
        this.plano = null;
        this.numMusicas = 0;
        this.historico = new ArrayList<>();
        this.historicoArtistas = new HashMap<>();
    }

    /** 
     * Construtor parametrizado
     * 
     * Cria um utilizador com os dados fornecidos.
     * 
     * @param id ID do utilizador
     * @param nome Nome do utilizador
     * @param email Email do utilizador
     * @param morada Morada do utilizador
     * @param plano Plano de subscrição do utilizador
     * @return O Utilizador criado
     */
    public Utilizador(String id, String nome, String email, String morada, PlanoSubscricao plano){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.pontos = 0.0;
        setPlano(plano);
        this.numMusicas = 0;
        this.historico = new ArrayList<>();
        this.historicoArtistas = new HashMap<>();
    }

    /** 
     * Construtor de cópia 
     * 
     * Cria um novo Utilizador com os mesmos dados que o Utilizador passado como parâmetro.
     * 
     * @param umUtilizador Utilizador a copiar
     * @return O Utilizador criado
    */
    public Utilizador(Utilizador umUtilizador){
        this.id = umUtilizador.getId();
        this.nome = umUtilizador.getNome();
        this.email = umUtilizador.getEmail();
        this.morada = umUtilizador.getMorada();
        this.pontos = umUtilizador.getPontos();
        this.plano = umUtilizador.getPlano();
        this.numMusicas = umUtilizador.getNumMusicas();
        this.historico = umUtilizador.getHistorico();
        this.historicoArtistas = umUtilizador.getHistoricoArtistas();
    }

    /**
     * Método que devolve o ID do utilizador.
     * 
     * @return ID do utilizador 
     */
    public String getId(){
        return this.id;
    }

    /**
     * Método que devolve o nome do utilizador.
     * 
     * @return Nome do utilizador
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Método que devolve o email do utilizador.
     * 
     * @return Email do utilizador
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Método que devolve a morada do utilizador.
     * 
     * @return Morada do utilizador
     */
    public String getMorada(){
        return this.morada;
    }
   
    /**
     * Método que devolve os pontos do utilizador.
     * 
     * @return Pontos do utilizador
     */
    public double getPontos(){
        return this.pontos;
    }

    /**
     * Método que devolve o plano de subscrição do utilizador.
     * 
     * @return Plano de subscrição do utilizador
     */
    public PlanoSubscricao getPlano(){
        return this.plano;
    }

    /**
     * Método que devolve o número de músicas ouvidas pelo utilizador.
     * 
     * @return Número de músicas ouvidas pelo utilizador
     */
    public int getNumMusicas(){
        return this.numMusicas;
    }

    /**
     * Método que devolve o histórico de reprodução do utilizador.
     * 
     * @return Lista de históricos de reprodução do utilizador
     */
    public List<Historico> getHistorico(){
        return new ArrayList<>(this.historico);
    }

    /**
     * Método que devolve o histórico de artistas do utilizador.
     * 
     * @return Mapa que associa artistas a números de reproduções ouvidos pelo utilizador
     */
    public Map<String, Integer> getHistoricoArtistas(){
        return new HashMap<>(this.historicoArtistas);
    }

    /**
     * Método que define o ID do utilizador.
     * 
     * @param novoId Novo ID do utilizador
     */
    public void setId(String novoId){
        this.id = novoId;
    }

    /**
     * Método que define o nome do utilizador.
     * 
     * @param novoNome Novo nome do utilizador
     */
    public void setNome(String novoNome){
        this.nome = novoNome;
    }

    /**
     * Método que define o email do utilizador.
     * 
     * @param novoEmail Novo email do utilizador
     */
    public void setEmail(String novoEmail){
        this.email = novoEmail;
    }

    /**
     * Método que define a morada do utilizador.
     * 
     * @param novaMorada Nova morada do utilizador
     */
    public void setMorada(String novaMorada){
        this.morada = novaMorada;
    }

    /**
     * Método que define os pontos do utilizador.
     * 
     * @param novosPontos Novos pontos do utilizador
     */
    public void setPontos(double novosPontos){
        this.pontos = novosPontos;
    }

    /**
     * Método que define o plano de subscrição do utilizador.
     * 
     * @param novoPlano Novo plano de subscrição do utilizador
     */
    public void setPlano(PlanoSubscricao novoPlano){
        this.plano = novoPlano;
        if(novoPlano instanceof PlanoPremiumTop && !recebeuBonusTop){
            this.pontos += 100;
            recebeuBonusTop = true;
        }
    }

    /**
     * Método que define o número de músicas ouvidas pelo utilizador.
     * 
     * @param novoNumMusicas Novo número de músicas ouvidas pelo utilizador
     */
    public void setNumMusicas(int novoNumMusicas){
        this.numMusicas = novoNumMusicas;
    }

    /**
     * Método que define o histórico de reprodução do utilizador.
     * 
     * @param novoHistorico Novo histórico de reprodução do utilizador
     */
    public void setHistorico(List<Historico> novoHistorico){
        this.historico = new ArrayList<>(novoHistorico);
    }

    /**
     * Método que define o histórico de artistas do utilizador.
     * 
     * @param novoHistoricoArtistas Novo histórico de artistas do utilizador
     */
    public void setHistoricoArtistas(Map<String, Integer> novoHistoricoArtistas){
        this.historicoArtistas = new HashMap<>(novoHistoricoArtistas);
    }

    /**
     * Método que devolve uma cópia do utilizador.
     * 
     * @return Cópia do utilizador
     */
    @Override
    public Utilizador clone(){
        return new Utilizador(this);
    }

    /**
     * Método que devolve uma representação textual das informações do utilizador.
     * 
     * @return String com os dados do utilizador
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("[UTILIZADOR]\n");
        sb.append("Id: ");
        sb.append(this.id).append("\n");
        sb.append("Nome: ");
        sb.append(this.nome).append("\n");
        sb.append("Email: ");
        sb.append(this.email).append("\n");
        sb.append("Morada: ");
        sb.append(this.morada).append("\n");
        sb.append("Pontos: ");
        sb.append(this.pontos).append("\n");
        sb.append("Plano: ");
        sb.append(this.plano.toString()).append("\n");
        return sb.toString();
    }

    /**
     * Método que devolve uma representação textual do histórico de reprodução do utilizador.
     * 
     * @return String com o histórico de reprodução do utilizador
     */
    public String toStringHistorico() {
        StringBuilder sb = new StringBuilder();
        for(Historico h : this.historico){
            sb.append(h.toString());
        }
        return sb.toString();
    }

    /**
     * Método que devolve uma representação textual do histórico de artistas do utilizador.
     * 
     * @return String com o histórico de artistas do utilizador
     */
    public String toStringHistoricoArtistas() {
        StringBuilder sb = new StringBuilder();
    
        for (Map.Entry<String, Integer> entry : historicoArtistas.entrySet()) {
            sb.append("Artista: \"")
              .append(entry.getKey())
              .append("\" -> NumReproducoes: \"")
              .append(entry.getValue())
              .append("\"\n");
        }
        return sb.toString();
    }
    
    /**
     * Método que verifica se dois utilizadores são iguais.
     * 
     * @param o Objeto a comparar com o utilizador
     * @return true se os utilizadores forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        Utilizador u = (Utilizador) o;
        return this.id.equals(u.getId()) &&
               this.nome.equals(u.getNome()) &&
               this.email.equals(u.getEmail()) &&
               this.morada.equals(u.getMorada()) &&
               this.pontos == u.getPontos() &&
               this.plano.equals(u.getPlano());
    };

    /**
     * Método que atualiza os pontos do utilizador com base no plano de subscrição.
     * 
     * @param plano Plano de subscrição do utilizador
     */
    public void atualizarPontos(){
        double ganhos = plano.calcularPontos(this);
        this.pontos += ganhos;
    }

    /**
     * Método que atualiza o número de músicas ouvidas pelo utilizador.
     */
    public void atualizarNumMusicas(){
        this.numMusicas++;
    }

    /**
     * Método que atualiza o histórico de reprodução do utilizador.
     * 
     * @param idMusica ID da música ouvida
     */
    public void atualizarHistorico(String idMusica) {
        Historico h = new Historico(idMusica);
        this.historico.add(h);
    }

    /**
     * Método que atualiza o histórico de artistas do utilizador.
     * 
     * @param artista Nome do artista ouvido
     */
    public void atualizarHistoricoArtistas(String artista) {
        this.historicoArtistas.merge(artista, 1, Integer::sum);
    }

    /**
     * Método que troca o plano de subscrição do utilizador.
     * 
     * @param novoPlano Novo plano de subscrição do utilizador
     */
    public void trocaPlano(PlanoSubscricao novoPlano){
        setPlano(novoPlano);
    }

    /**
     * Método que ouve uma música.
     * 
     * @param m Música a ouvir
     */
    public void ouvirMusica(Musica m){
        m.reproduzir();
        this.atualizarPontos();
        this.atualizarNumMusicas();
        this.atualizarHistorico(m.getId());
        this.atualizarHistoricoArtistas(m.getArtista());
    }

    /**
     * Método que ouve um álbum.
     * 
     * @param a Álbum a ouvir
     */
    public void ouvirAlbum(Album a){
        a.reproduzir(this);
    }

    /**
     * Método que ouve uma playlist.
     * 
     * @param p Playlist a ouvir
     * @param c Controlador do SpotifyUM
     */
    public void ouvirPlaylist(Playlist p, SpotifyumController c){
        try{
            this.plano.reproduzPlaylist(p, this, c);
        }catch(PlanoException | PublicException e){
            System.out.println("Erro ao reproduzir playlist: " + e.getMessage());
        }
    }
}
