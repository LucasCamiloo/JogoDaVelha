
package jogodavelha;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lucas.dcamilo
 */
public class JogoDaVelha {
/** ******************** */
//DOCUMENTAÇÃO E IMPORTANTES CONSIDERAÇÕES
//*********************************************************************/
//VARIÁVEIS DO PROJETO                                                 /
//*********************************************************************/
//tabuleiro: Matriz de strings que faz o tabuleiro do jogo da velha
//player1 e player2: int - Armazenam a pontuação dos jogadores.
//escolha: int - Armazena o modo de jogo escolhido pelo usuário.
//numeroAleatorio: int - Armazena um número aleatório gerado para auxiliar nas jogadas da máquina.
//vencedor: boolean - Indica se houve um vencedor no jogo.
//maquina: boolean - Indica se é a vez da máquina jogar.
//linha e coluna: int - Armazenam as coordenadas da última jogada realizada.
//jogador: int - Indica qual jogador deve jogar a seguir (0 para o jogador 1 ou 2 para a máquina).
//posicaoValida: boolean - Indica se a posição escolhida para a jogada é válida. 
//*********************************************************************/
// FUNÇOES DO PROJETO                                                  /
//*********************************************************************/
//main(): Ponto de entrada do programa. Inicializa o tabuleiro e chama a função imprimeMenuPrincipal().
//imprimeMenuPrincipal(): Exibe o menu principal do jogo, solicita a escolha do modo de jogo e encaminha para o modo selecionado.
//modoJogador(): Implementa o modo de jogo para dois jogadores USUARIO VS USUARIO.
//modoFacil(): Implementa o modo de jogo contra a máquina no modo facil (apenas jogadas aleatorias).
//aleatorio(): Retorna um número aleatório entre 0 e 2.
//verificaVencedor(): Verifica se há um vencedor no jogo, tanto na horizontal, vertical quanto nas diagonais.
//imprimePontuacao(): Imprime a pontuação dos jogadores e pergunta se deseja continuar jogando.
//verificaVelha(): Verifica se houve empate (velha) no jogo.
//jogar(): Realiza uma jogada no tabuleiro.
//jogadaUsuario(): Realiza uma jogada do usuário no tabuleiro.
//leiaCoordenadaColuna(): Lê a coordenada da coluna escolhida pelo jogador.
//leiaCoordenadaLinha(): Lê a coordenada da linha escolhida pelo jogador.
//posicaoValida(): Verifica se a posição escolhida é válida para a jogada.
//inicializarTabuleiro(): Inicializa o tabuleiro do jogo zerando todas as posições.
//imprimirTabuleiro(): Imprime o tabuleiro do jogo na tela.




     public static void main(String[] args) {
        String[][] tabuleiro = new String[3][3];

        imprimeMenuPrincipal(tabuleiro);

    }

    public static void imprimeMenuPrincipal(String[][] tabuleiro) {
        int player1 = 0;
        int player2 = 0;
        System.out.println("      _                                  \n"
                + "     | | ___   __ _  ___                 \n"
                + "  _  | |/ _ \\ / _` |/ _ \\                \n"
                + " | |_| | (_) | (_| | (_) |               \n"
                + "  \\___/ \\___/ \\__, |\\___/                \n"
                + "      _       |___/   __   _ _           \n"
                + "   __| | __ _  \\ \\   / /__| | |__   __ _ \n"
                + "  / _` |/ _` |  \\ \\ / / _ \\ | '_ \\ / _` |\n"
                + " | (_| | (_| |   \\ V /  __/ | | | | (_| |\n"
                + "  \\__,_|\\__,_|    \\_/ \\___|_|_| |_|\\__,_|\n"
                + "                                         ");
        System.out.println("Selecione o modelo de jogo que voce deseja jogar.\n1- Jogador vs Jogador\n2- Jogador vs Maquina (Facil)\n3- Jogador vs Maquina (Dificil)");
        Scanner input = new Scanner(System.in);
        int escolha = input.nextInt();
        switch (escolha) {
            case 1:
                modoJogador(tabuleiro, player1, player2, escolha);
                break;
            case 2:
                modoFacil(tabuleiro, player1, player2, escolha);
                break;
            case 3:
                modoDificil(tabuleiro, player1, player2, escolha);
                break;
            default:
                System.out.println("Nenhuma opcao valida foi selecionada");

        }
    }

    public static void modoJogador(String[][] tabuleiro, int player1, int player2, int escolha) {
        int numeroAleatorio = 0;
        boolean vencedor = false;
        boolean maquina = false;
        int linha = 0;
        int coluna = 0;
        int jogador = 0;
        boolean posicaoValida = false;
        inicializarTabuleiro(tabuleiro);
        while (vencedor == false) {
            if (jogador == 0) {

                System.out.println(" |  _ \\| | __ _ _   _  ___ _ __  / |\n"
                        + " | |_) | |/ _` | | | |/ _ \\ '__| | |\n"
                        + " |  __/| | (_| | |_| |  __/ |    | |\n"
                        + " |_|   |_|\\__,_|\\__, |\\___|_|    |_|\n"
                        + "                |___/               ");
                jogador = 1;
            } else {
                System.out.println(" |  _ \\| | __ _ _   _  ___ _ __  |___ \\ \n"
                        + " | |_) | |/ _` | | | |/ _ \\ '__|   __) |\n"
                        + " |  __/| | (_| | |_| |  __/ |     / __/ \n"
                        + " |_|   |_|\\__,_|\\__, |\\___|_|    |_____|\n"
                        + "                |___/                   ");
                jogador = 0;
            }
            imprimirTabuleiro(tabuleiro);
            while (posicaoValida == false) {
                coluna = leiaCoordenadaColuna(coluna, maquina, numeroAleatorio);
                linha = leiaCoordenadaLinha(linha, maquina, numeroAleatorio);

                posicaoValida = posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);
            }
            posicaoValida = false;
            vencedor = verificaVencedor(tabuleiro, vencedor, jogador, player1, player2, escolha, maquina);
            vencedor = verificaVelha(tabuleiro, vencedor, player1, player2, escolha);

        }
    }

    public static void modoFacil(String[][] tabuleiro, int player1, int player2, int escolha) {
        int numeroAleatorio = 0;
        boolean vencedor = false;
        int linha = 0;
        int coluna = 0;
        int jogador = 1;
        boolean maquina = false;
        boolean posicaoValida = false;
        inicializarTabuleiro(tabuleiro);

        while (!vencedor) {
            if (jogador == 1) {
                System.out.println("        _                         _ \n"
                        + "  _ __ | | __ _ _   _  ___ _ __  / |\n"
                        + " | '_ \\| |/ _` | | | |/ _ \\ '__| | |\n"
                        + " | |_) | | (_| | |_| |  __/ |    | |\n"
                        + " | .__/|_|\\__,_|\\__, |\\___|_|    |_|\n"
                        + " |_|            |___/               ");
                System.out.println("Sua vez de jogar:");
                imprimirTabuleiro(tabuleiro);
                maquina = false;
                while (posicaoValida == false) {
                    coluna = leiaCoordenadaColuna(coluna, maquina, numeroAleatorio);
                    linha = leiaCoordenadaLinha(linha, maquina, numeroAleatorio);

                    posicaoValida = posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);
                }
                posicaoValida = false;
                vencedor = verificaVencedor(tabuleiro, vencedor, jogador, player1, player2, escolha, maquina);
                vencedor = verificaVelha(tabuleiro, vencedor, player1, player2, escolha);
                jogador = 0;
            } else {
                System.out.println("        _                         ____  \n"
                        + "  _ __ | | __ _ _   _  ___ _ __  |___ \\ \n"
                        + " | '_ \\| |/ _` | | | |/ _ \\ '__|   __) |\n"
                        + " | |_) | | (_| | |_| |  __/ |     / __/ \n"
                        + " | .__/|_|\\__,_|\\__, |\\___|_|    |_____|\n"
                        + " |_|            |___/                   ");
                System.out.println("Vez da máquina:");
                imprimirTabuleiro(tabuleiro);
                maquina = true;
                jogadaMaquinaFacil(tabuleiro, jogador, posicaoValida, maquina);
                vencedor = verificaVencedor(tabuleiro, vencedor, jogador, player1, player2, escolha, maquina);
                vencedor = verificaVelha(tabuleiro, vencedor, player1, player2, escolha);
                jogador = 1;
            }
        }
    }

    public static void jogadaMaquinaFacil(String[][] tabuleiro, int jogador, boolean posicaoValida, boolean maquina) {

        int coluna = aleatorio();
        int linha = aleatorio();
        posicaoValida = posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);

    }

    public static void modoDificil(String[][] tabuleiro, int player1, int player2, int escolha) {
        int numeroAleatorio = 0;
        boolean vencedor = false;
        int linha = 0;
        int coluna = 0;
        int jogador = 1;
        boolean maquina = false;
        boolean posicaoValida = false;
        inicializarTabuleiro(tabuleiro);

        while (!vencedor) {
            if (jogador == 1) {
                System.out.println("        _                         _ \n"
                        + "  _ __ | | __ _ _   _  ___ _ __  / |\n"
                        + " | '_ \\| |/ _` | | | |/ _ \\ '__| | |\n"
                        + " | |_) | | (_| | |_| |  __/ |    | |\n"
                        + " | .__/|_|\\__,_|\\__, |\\___|_|    |_|\n"
                        + " |_|            |___/               ");
                System.out.println("Sua vez de jogar:");
                imprimirTabuleiro(tabuleiro);
                maquina = false;
                while (posicaoValida == false) {
                    coluna = leiaCoordenadaColuna(coluna, maquina, numeroAleatorio);
                    linha = leiaCoordenadaLinha(linha, maquina, numeroAleatorio);

                    posicaoValida = posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);
                }
                posicaoValida = false;
                vencedor = verificaVencedor(tabuleiro, vencedor, jogador, player1, player2, escolha, maquina);
                vencedor = verificaVelha(tabuleiro, vencedor, player1, player2, escolha);
                jogador = 0;
            } else {
                System.out.println("        _                         ____  \n"
                        + "  _ __ | | __ _ _   _  ___ _ __  |___ \\ \n"
                        + " | '_ \\| |/ _` | | | |/ _ \\ '__|   __) |\n"
                        + " | |_) | | (_| | |_| |  __/ |     / __/ \n"
                        + " | .__/|_|\\__,_|\\__, |\\___|_|    |_____|\n"
                        + " |_|            |___/                   ");
                System.out.println("Vez da máquina:");
                imprimirTabuleiro(tabuleiro);
                maquina = true;
                jogadaMaquinaDificil(tabuleiro, jogador, posicaoValida, maquina);
                vencedor = verificaVencedor(tabuleiro, vencedor, jogador, player1, player2, escolha, maquina);
                vencedor = verificaVelha(tabuleiro, vencedor, player1, player2, escolha);
                jogador = 1;
            }
        }
    }

    public static void jogadaMaquinaDificil(String[][] tabuleiro, int jogador, boolean posicaoValida, boolean maquina) {
        int linha = 0;
        int coluna = 0;
        maquina = true;
        //Valida se tem chance de ganhar
        while (maquina == true) {
            if (linha == 0 && coluna == 0) {
                for (int i = 0; i < 3; i++) {
                    //Verifica se tem chance de ganhar na Horizontal
                    if (tabuleiro[0][i] == ("X") && tabuleiro[1][i] == ("X") && tabuleiro[2][i] == (" ")) {
                        linha = i;
                        coluna = 2;

                    } else if (tabuleiro[0][i] == ("X") && tabuleiro[1][i] == (" ") && tabuleiro[2][i] == ("X")) {
                        linha = i;
                        coluna = 1;

                    } else if (tabuleiro[0][i] == (" ") && tabuleiro[1][i] == ("X") && tabuleiro[2][i] == ("X")) {
                        linha = i;
                        coluna = 0;
                    }
                }
            }

            if (linha == 0 && coluna == 0) {
                for (int i = 0; i < 3; i++) {
                    //Verifica se vai ganhar na Vertical
                    if (tabuleiro[i][0] == ("X") && tabuleiro[i][1] == ("X") && tabuleiro[i][2] == (" ")) {
                        linha = 2;
                        coluna = i;

                    } else if (tabuleiro[i][0] == ("X") && tabuleiro[i][1] == (" ") && tabuleiro[i][2] == ("X")) {
                        linha = 1;
                        coluna = i;

                    } else if (tabuleiro[i][0] == (" ") && tabuleiro[i][1] == ("X") && tabuleiro[i][2] == ("X")) {
                        linha = 0;
                        coluna = i;
                    }
                }
            }

            if (linha == 0 && coluna == 0) {
                //Verifica se o Jogador vai ganhar na Diagonal
                if (tabuleiro[0][0] == ("X") && tabuleiro[1][1] == ("X") && tabuleiro[2][2] == (" ")) {
                    linha = 2;
                    coluna = 2;
                } else if (tabuleiro[0][0] == ("X") && tabuleiro[1][1] == (" ") && tabuleiro[2][2] == ("X")) {
                    linha = 1;
                    coluna = 1;
                } else if (tabuleiro[0][0] == (" ") && tabuleiro[1][1] == ("X") && tabuleiro[2][2] == ("X")) {
                    linha = 0;
                    coluna = 0;

                } else if (tabuleiro[2][0] == ("X") && tabuleiro[1][1] == ("X") && tabuleiro[0][2] == (" ")) {
                    linha = 0;
                    coluna = 2;

                } else if (tabuleiro[2][0] == ("X") && tabuleiro[1][1] == (" ") && tabuleiro[0][2] == ("X")) {
                    linha = 1;
                    coluna = 1;

                } else if (tabuleiro[2][0] == (" ") && tabuleiro[1][1] == ("X") && tabuleiro[0][2] == ("X")) {
                    linha = 2;
                    coluna = 0;
                }
            }

            //Impede jogador de ganhar
            if (linha == 0 && coluna == 0) {
                for (int i = 0; i < 3; i++) {
                    //Verifica se o Jogador vai ganhar na Horizontal
                    if (tabuleiro[0][i] == ("O") && tabuleiro[1][i] == ("O") && tabuleiro[2][i] == (" ")) {
                        linha = i;
                        coluna = 2;

                    } else if (tabuleiro[0][i] == ("O") && tabuleiro[1][i] == (" ") && tabuleiro[2][i] == ("O")) {
                        linha = i;
                        coluna = 1;

                    } else if (tabuleiro[0][i] == (" ") && tabuleiro[1][i] == ("O") && tabuleiro[2][i] == ("O")) {
                        linha = i;
                        coluna = 0;
                    }
                }
            }

            if (linha == 0 && coluna == 0) {
                for (int i = 0; i < 3; i++) {
                    //Verifica se o Jogador vai ganhar na Vertical
                    if (tabuleiro[i][0] == ("O") && tabuleiro[i][1] == ("O") && tabuleiro[i][2] == (" ")) {
                        linha = 2;
                        coluna = i;

                    } else if (tabuleiro[i][0] == ("O") && tabuleiro[i][1] == (" ") && tabuleiro[i][2] == ("O")) {
                        linha = 1;
                        coluna = i;

                    } else if (tabuleiro[i][0] == (" ") && tabuleiro[i][1] == ("O") && tabuleiro[i][2] == ("O")) {
                        linha = 0;
                        coluna = i;
                    }
                }
            }

            if (linha == 0 && coluna == 0) {
                //Verifica se o Jogador vai ganhar na Diagonal
                if (tabuleiro[0][0] == ("O") && tabuleiro[1][1] == ("O") && tabuleiro[2][2] == (" ")) {
                    linha = 2;
                    coluna = 2;
                } else if (tabuleiro[0][0] == ("O") && tabuleiro[1][1] == (" ") && tabuleiro[2][2] == ("O")) {
                    linha = 1;
                    coluna = 1;
                } else if (tabuleiro[0][0] == (" ") && tabuleiro[1][1] == ("O") && tabuleiro[2][2] == ("O")) {
                    linha = 0;
                    coluna = 0;

                } else if (tabuleiro[2][0] == ("O") && tabuleiro[1][1] == ("O") && tabuleiro[0][2] == (" ")) {
                    linha = 0;
                    coluna = 2;

                } else if (tabuleiro[2][0] == ("O") && tabuleiro[1][1] == (" ") && tabuleiro[0][2] == ("O")) {
                    linha = 1;
                    coluna = 1;

                } else if (tabuleiro[2][0] == (" ") && tabuleiro[1][1] == ("O") && tabuleiro[0][2] == ("O")) {
                    linha = 2;
                    coluna = 0;
                }
            }
            //Depois de passar por todos os verificadores ele checa se bate com alguma das possíveis jogadas que a maquina dificil pode fazer , caso nada for 
            //possivel fazer, ele parte para a aleatoriedade
            if (linha != 0 || coluna != 0) {
                posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);
                maquina = false;
            }
            //Modo aleatório, caso nenhuma condição seja atendida
            if (linha == 0 && coluna == 0) {
                posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);
                maquina = false;
            }
            maquina = false;
        }

    }

    public static int aleatorio() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public static boolean verificaVencedor(String[][] tabuleiro, boolean vencedor, int jogador, int player1, int player2, int escolha, boolean maquina) {

        //verifica se tem ganhadores
        for (int i = 0; i < 3; i++) {

            //VE SE TEM ALGUM GANHADOR NA HORIZONTAL
            if (tabuleiro[0][i] == ("X") && tabuleiro[1][i] == ("X") && tabuleiro[2][i] == ("X")) {
                imprimirTabuleiro(tabuleiro);

                imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
                return true;
            } else if (tabuleiro[0][i] == ("O") && tabuleiro[1][i] == ("O") && tabuleiro[2][i] == ("O")) {
                imprimirTabuleiro(tabuleiro);

                imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            //VE SE TEM ALGUM GANHADOR NA VERTICAL
            if (tabuleiro[i][0] == ("X") && tabuleiro[i][1] == ("X") && tabuleiro[i][2] == ("X")) {
                imprimirTabuleiro(tabuleiro);

                imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
            } else if (tabuleiro[i][0] == ("O") && tabuleiro[i][1] == ("O") && tabuleiro[i][2] == ("O")) {
                imprimirTabuleiro(tabuleiro);

                imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
            }
        }

        //VE SE TEM ALGUM GANHADOR NA DIAGONAL PARA O X
        if (tabuleiro[0][0] == ("X") && tabuleiro[1][1] == ("X") && tabuleiro[2][2] == ("X")) {
            imprimirTabuleiro(tabuleiro);

            imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
        } else if (tabuleiro[2][0] == ("X") && tabuleiro[1][1] == ("X") && tabuleiro[0][2] == ("X")) {
            imprimirTabuleiro(tabuleiro);

            imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
        }
        if (tabuleiro[0][0] == ("O") && tabuleiro[1][1] == ("O") && tabuleiro[2][2] == ("O")) {
            imprimirTabuleiro(tabuleiro);

            imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
        } else if (tabuleiro[2][0] == ("O") && tabuleiro[1][1] == ("O") && tabuleiro[0][2] == ("O")) {
            imprimirTabuleiro(tabuleiro);

            imprimePontuacao(jogador, tabuleiro, player1, player2, escolha, maquina, vencedor);
        }
        return false;

    }

    public static void imprimePontuacao(int jogador, String[][] tabuleiro, int player1, int player2, int escolha, boolean maquina, boolean vencedor) {
        int i = 0;
        boolean acaba = false;
        if (player1 == 2) {
            System.out.println("*********************FIM DE JOGO*********************\n O PLAYER 1 VENCEU");
            System.out.println("                            __                   \n"
                    + "    ____  ____ __________ _/ /_  ___  ____  _____\n"
                    + "   / __ \\/ __ `/ ___/ __ `/ __ \\/ _ \\/ __ \\/ ___/\n"
                    + "  / /_/ / /_/ / /  / /_/ / /_/ /  __/ / / (__  ) \n"
                    + " / .___/\\__,_/_/   \\__,_/_.___/\\___/_/ /_/____/  \n"
                    + "/_/                                              ");
            acaba = true;
            vencedor = true;
            System.out.println("\nDigite 2 caso queira voltar ao menu principal");
            Scanner input = new Scanner(System.in);
            i = input.nextInt();

        }
        if (player2 == 2) {
            System.out.println("*********************FIM DE JOGO*********************\n O PLAYER 2 VENCEU");
            System.out.println("                            __                   \n"
                    + "    ____  ____ __________ _/ /_  ___  ____  _____\n"
                    + "   / __ \\/ __ `/ ___/ __ `/ __ \\/ _ \\/ __ \\/ ___/\n"
                    + "  / /_/ / /_/ / /  / /_/ / /_/ /  __/ / / (__  ) \n"
                    + " / .___/\\__,_/_/   \\__,_/_.___/\\___/_/ /_/____/  \n"
                    + "/_/                                              ");
            acaba = true;
            vencedor = true;
            System.out.println("\nDigite 2 caso queira voltar ao menu principal");
            Scanner input = new Scanner(System.in);
            i = input.nextInt();
        }
        if (i == 2) {
            imprimeMenuPrincipal(tabuleiro);
        }
        if (jogador == 1) {
            player1++;
            System.out.println("O player 1 fez um ponto!!!");
        } else {
            player2++;
            if (maquina == true) {
                System.out.println("A maquina fez um ponto!");
            } else {
                System.out.println("O player 2 fez um ponto!!");
            }
        }
        System.out.println("******PONTUACAO******");
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
        System.out.println("********************");
        System.out.println("\nDeseja continuar?\n1- Sim\n2-Nao");
        Scanner input = new Scanner(System.in);
        i = input.nextInt();
        if (i == 1) {
            switch (escolha) {
                case 1:
                    modoJogador(tabuleiro, player1, player2, escolha);
                    break;
                case 2:
                    modoFacil(tabuleiro, player1, player2, escolha);
                    break;

                case 3:
                    modoDificil(tabuleiro, player1, player2, escolha);
                    break;
            }

        } else {
            imprimeMenuPrincipal(tabuleiro);
            player1 = 0;
            player2 = 0;
        }

    }

   public static boolean verificaVelha(String[][] tabuleiro, boolean vencedor, int player1, int player2, int escolha) {
        int espacosPreenchidos = 0;
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (" " == tabuleiro[l][c]) {

                } else {
                    espacosPreenchidos++;
                }

            }
        }
        if (espacosPreenchidos == 9) {
            vencedor = true;
            System.err.println("DEU VELHA, nenhum ponto foi atribuido a nenhum jogador e o jogo se inicializara novamente.");
            switch (escolha) {
                case 1:
                    modoJogador(tabuleiro, player1, player2, escolha);
                    break;
                case 2:
                    modoFacil(tabuleiro, player1, player2, escolha);
                    break;

                case 3:
                    modoDificil(tabuleiro, player1, player2, escolha);
                    break;
            }

        }
        return vencedor;
    }

    public static boolean posicaoValida(int coluna, int linha, String[][] tabuleiro, int jogador, boolean posicaoValida, boolean maquina) {
        if (tabuleiro[coluna][linha] == " ") {
            jogadaUsuario(coluna, linha, tabuleiro, jogador);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + "Posicao preenchida");
            return true;
        } else {
            if (maquina == true) {
                coluna = aleatorio();
                linha = aleatorio();
                posicaoValida = posicaoValida(coluna, linha, tabuleiro, jogador, posicaoValida, maquina);
                return posicaoValida;
            } else {
                System.out.println("\n\n\n\n\n" + "Posicao ja esta preenchida. Tente em outro lugar.");
                imprimirTabuleiro(tabuleiro);
                return false;
            }
        }
    }

    public static void jogar(int coluna, int linha, String[][] tabuleiro, String XouO) {

        tabuleiro[coluna][linha] = XouO;

    }

    public static void jogadaUsuario(int coluna, int linha, String[][] tabuleiro, int jogador) {
        String XouO = " ";

        if (jogador == 0) {
            XouO = "X";
            jogar(coluna, linha, tabuleiro, XouO);

        } else {
            XouO = "O";
            jogar(coluna, linha, tabuleiro, XouO);
        }

    }

    public static int leiaCoordenadaColuna(int coluna, boolean maquina, int numeroAleatorio) {
        if (maquina == true) {
            coluna = numeroAleatorio;
            return coluna;
        } else {
            System.out.println("Selecione a coluna que voce deseja jogar. A B ou C.");
            System.out.print("Resposta: ");
            Scanner input = new Scanner(System.in);
            String posicao = input.next();
            switch (posicao) {
                case "A":
                    coluna = 0;
                    break;
                case "B":
                    coluna = 1;
                    break;
                case "C":
                    coluna = 2;
                    break;
                case "a":
                    coluna = 0;
                    break;
                case "b":
                    coluna = 1;
                    break;
                case "c":
                    coluna = 2;
                    break;
                default:
                    System.err.println("POSICAO INVALIDA.");

                    break;
            }
            return coluna;
        }
    }

    public static int leiaCoordenadaLinha(int linha, boolean maquina, int numeroAleatorio) {
        if (maquina == true) {
            linha = numeroAleatorio;
            return linha;
        } else {
            System.out.println("Selecione a linha que voce deseja jogar. 1 2 ou 3.");
            System.out.print("Resposta: ");
            Scanner input = new Scanner(System.in);
            String posicao = input.next();
            switch (posicao) {
                case "1":
                    linha = 0;
                    break;
                case "2":
                    linha = 1;
                    break;
                case "3":
                    linha = 2;
                    break;
                default:
                    System.err.println("POSICAO INVALIDA.");

                    break;
            }
            return linha;

        }
    }

    public static void inicializarTabuleiro(String[][] tabuleiro) {

        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                tabuleiro[l][c] = " ";

            }
        }
        System.err.println("Fazendo o tabuleiro...");

    }

    public static void imprimirTabuleiro(String[][] tabuleiro) {
        int col = 0;

        System.out.println("  A  B  C");

        for (int l = 0; l < 3; l++) {
            switch (col) {
                case 0:
                    System.out.print("1");
                    break;
                case 1:
                    System.out.print("2");
                    break;
                case 2:
                    System.out.print("3");
                    break;
            }

            for (int c = 0; c < 3; c++) {

                System.out.printf("[" + tabuleiro[c][l] + "]");
            }
            System.out.println();
            col++;
        }
    }

}
    

