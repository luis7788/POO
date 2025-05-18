package spotifyum.menu;

import java.util.List;
import java.util.Scanner;

/**
 * Classe Menu
 * Esta classe é responsável por exibir um menu com opções e executar ações baseadas na escolha do utilizador.
 */
public class Menu {
    /** Lista de oções de um menu */
    private List<MenuOpcao> opcoes;

    /** Scanner para leitura de entradas do utilizador */
    private Scanner scanner = new Scanner(System.in);


    /**
     * Construtor parameterizado
     * 
     * Cria um menu com a lista de opções passada por argumento.
     * @param opcoes Lista de opções do menu
     * 
     * @return O menu criado
     */
    public Menu(List<MenuOpcao> opcoes){
        this.opcoes = opcoes;
    }

    /**
     * Método responsável por exibir todas as opções do menu.
     */
    public void exibirMenu(){
        System.out.println();
        for(int i = 0; i < this.opcoes.size(); i++){
            MenuOpcao opcao = this.opcoes.get(i);
            System.out.println((i+1) + " -> " + (opcao.isPreCondicaoValida() ? opcao.getNomeOpcao() : "(indisponível)"));
        }
        System.out.println("0 -> Sair");
        System.out.println();
    }

    /**
     * Método responsável por ler a escolha do utilizador.
     * 
     * @return Escoha do utilizador
     */
    public int getEscolha(){
        int escolha;
        System.out.print("Escolha uma das opções > ");
        try{
            String line = scanner.nextLine();
            escolha = Integer.parseInt(line);
        }catch(NumberFormatException e){
            escolha = -1;
        }
        if(escolha < 0 || escolha > this.opcoes.size()){
            System.out.println("Opção inválida.");
            escolha = -1;
        }
        return escolha;
    }
    /**
     * Método responsável por executar o menu.
     * 
     * Este método exibe o menu e executa a ação correspondente à escolha do utilizador.
     */
    public void run(){
        int escolha;
        do{
            exibirMenu();
            escolha = getEscolha();
            if(escolha > 0 && !this.opcoes.get(escolha - 1).isPreCondicaoValida()){
                System.out.println("Opção indisponível.");
            } else if(escolha > 0){
                this.opcoes.get(escolha - 1).executa();
            }
        }while(escolha != 0);
    }
}
