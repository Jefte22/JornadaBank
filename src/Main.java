import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int menu = 0;

    String[][] usuarios = mockUsuarios();

    int numConta = 4;

    while ( true ) {
      System.out.println("----------------- BANCO MENU -----------------");
      System.out.println("Opções:");
      System.out.println("1 - Registro de usuarios");
      System.out.println("2 - Criação de conta");
      System.out.println("3 - Depositar valor");
      System.out.println("4 - Sacar valor");
      System.out.println("5 - Transferir valor");
      System.out.println("6 - Listar usuários");
      System.out.println("7 - Sair");
      menu = sc.nextInt();

      // Finaliza o sistema
      if ( menu == 7 ) {
        System.out.println("Finalizando Sistema.");
        break;
      }

      if ( menu == 6 ) {
        System.out.println("Lista de usuários e dados: ");
        listarUsuarios(usuarios);
      }

      if ( menu == 1 ) {
        // Cria um vetor com os 6 slots, para armazenar a linha da matriz usuarios.
        String [] novoRegistro = new String[6];

        System.out.println("Registro de usuário");
        System.out.println("Favor preencha os dados solicitados abaixo: ");

        System.out.println("Nome Completo: ");
        sc.nextLine();
        novoRegistro[0] = sc.nextLine();

        System.out.println("CPF: ");
        novoRegistro[1] = sc.nextLine();

        System.out.println("E-mail: ");
        novoRegistro[2] = sc.nextLine();

        System.out.println("Senha: ");
        novoRegistro[3] = sc.nextLine();

        usuarios = adicionarSlot(usuarios, novoRegistro); // insere os valores na matriz
      }

      if ( menu == 2 ) {
        System.out.println("Criação de conta");
        System.out.println("Digite o CPF do usuário a ser criada a conta");
        sc.nextLine();
        String cpf = sc.nextLine();

        int validaCpf = buscaUsuarioCpf(usuarios, cpf); // busca por cpf na matriz
        if ( validaCpf == 0 ) {
          System.out.println("O cpf informado não foi encontrado");
        } else {
          usuarios[validaCpf][4] = Integer.toString(numConta++); // Cria e insere valor na matriz, as contas são criadas com numeros incrementados
          usuarios[validaCpf][5] = Integer.toString(0); // Inicia a conta com o valor 0

          System.out.println("Sua conta foi criada com sucesso!");
          System.out.println("O numero da conta criada é: " + usuarios[validaCpf][4]);
        }
      }

      if ( menu == 3 ) {
        System.out.println("Depósito");
        System.out.println("Digite a conta destino: ");
        sc.nextLine();
        String conta = sc.nextLine();

        int validaConta = buscaUsuarioConta(usuarios, conta); // Busca o usuário pela conta dele
        if (validaConta == 0) {
          System.out.println("A conta informada não foi encontrada");
        } else {
          System.out.println("Conta " + validaConta +" encontrada com sucesso!");
          System.out.println("Digite o valor a ser depositado: ");
          float valorDeposito = sc.nextFloat();

          if (valorDeposito < 0) {
            System.out.println("Valor inválido para depósito!");
          } else {
            float valorNovo = Float.parseFloat(usuarios[validaConta][5]) + valorDeposito; // formata o valor da matriz, que é string, e transforma em float para poder calcular
            usuarios[validaConta][5] = Float.toString(valorNovo); // Salva o valor somado na matriz ao mesmo tempo que formata para String novamente
            System.out.println("O valor de: R$" + valorDeposito + " foi depositado com sucesso!");
          }
        }
      }

      if ( menu == 4 ) {
        System.out.println("Saque");
        System.out.println("Digite a conta que deseja retirar o dinheiro: ");
        sc.nextLine();
        String conta = sc.nextLine();

        int validaConta = buscaUsuarioConta(usuarios, conta);
        if (validaConta == 0) {
          System.out.println("A conta informada não foi encontrada");
        } else {
          System.out.println("Conta " + validaConta +" encontrada com sucesso!");
          System.out.println("Digite a senha da conta: ");
          String senhaDigitada = sc.nextLine();


          if (usuarios[validaConta][3].equals(senhaDigitada)) {
            System.out.println("Digite o valor a ser retirado: ");

            float valorSaque = sc.nextFloat();

            if (valorSaque < 0) {
              System.out.println("Valor inválido para saque!");
            } else {
              float valorNovo = Float.parseFloat(usuarios[validaConta][5]) - valorSaque;

              if (valorNovo >= 0) {
                usuarios[validaConta][5] = Float.toString(valorNovo);
                System.out.println("O valor de: R$" + valorSaque + " foi sacado com sucesso!");
                System.out.println("O saldo total atual da conta é de: R$" + valorNovo);
              } else {
                System.out.println("O valor da conta não pode ficar negativo!");
              }

            }
          } else {
            System.out.println("Senha inválida!");
          }
        }

      }

      if ( menu == 5 ) {
        System.out.println("Transferência");
        System.out.println("Digite a conta de origem: ");
        sc.nextLine();
        String contaOrigem = sc.nextLine();

        int validaContaOrigem = buscaUsuarioConta(usuarios, contaOrigem); // Busca o usuário pela conta de origem
        if (validaContaOrigem == 0) {
          System.out.println("A conta de origem informada não foi encontrada");
        } else {
          System.out.println("Digite a conta de destino: ");
          String contaDestino = sc.nextLine();
          int validaContaDestino = buscaUsuarioConta(usuarios, contaDestino); // Busca o usuário pela conta de destino
          if (validaContaDestino == 0) {
            System.out.println("A conta de destino informada não foi encontrada");
          }

          System.out.println("Digite a senha da conta origem: ");
          String senhaDigitada = sc.nextLine();
          if (!usuarios[validaContaOrigem][3].equals(senhaDigitada)) {
            System.out.println("Senha inválida!");
          }

          int validaConta = buscaUsuarioConta(usuarios, contaOrigem);
          if (usuarios[validaConta][3].equals(senhaDigitada)) {
            System.out.println("Digite o valor a ser transferido: ");
            float valorTransferido = sc.nextFloat();

            if (valorTransferido < 0) {
              System.out.println("Valor inválido para transferência");
            }

            float saldoOrigem = Float.parseFloat(usuarios[validaContaOrigem][5]); // Verifica se o saldo da conta origem é suficiente para a
            if (saldoOrigem < valorTransferido) {                                 // transferência, se não for, então ele retorna com a mensagem!
              System.out.println("Saldo insuficiente na conta de origem!");
              return;
            }

            float novoValorContaDestino = Float.parseFloat(usuarios[validaContaDestino][5]) + valorTransferido;
            float novoValorContaOrigem = Float.parseFloat(usuarios[validaContaOrigem][5]) - valorTransferido;

            usuarios[validaContaOrigem][5] = Float.toString(novoValorContaOrigem);
            usuarios[validaContaDestino][5] = Float.toString(novoValorContaDestino);

            System.out.println("A transferência de R$ " + valorTransferido + " foi realizada com sucesso!");
            System.out.println("O saldo da conta de origem é: R$ " + novoValorContaOrigem);
            System.out.println("O saldo da conta de destino é: R$ " + novoValorContaDestino);

          }
        }
      }

    }
  }

  public static String[][] adicionarSlot(String[][] matriz, String[] arrNovoRegistro)
  {
    String[][] novaMatriz = new String[matriz.length + 1][6];  //Cria um novo array com uma "linha" a mais

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++)
      {
        novaMatriz[i][j] = matriz[i][j];  //Copia os elementos do array antigo para o novo array
      }
    }

    novaMatriz[novaMatriz.length - 1] = arrNovoRegistro; // Adciona as novas colunas na linha criada. (-1 necessário pois o length não é igual ao index)

    return novaMatriz;
  }

  public static int buscaUsuarioCpf(String[][] matriz, String cpf) {
    for (int i = 0; i < matriz.length; i++) {
      if ( cpf.equals(matriz[i][1]) ) {
        return i;
      }
    }

    return 0;
  }

  public static int buscaUsuarioConta(String[][] matriz, String conta) {
    for (int i = 0; i < matriz.length; i++) {
      if ( conta.equals(matriz[i][4]) ) {
        return i;
      }
    }

    return 0;
  }

//  public static int transferenciaContas(String[][] matriz, String conta) {
//    for (int i = 0; i < matriz.length; i++) {
//      if (conta.equals(matriz[i][5])) {
//        return i;
//      }
//    }
//
//    return 0;
//  }

  public static void listarUsuarios(String[][] matriz) {  //Lista todos usuários cadastrados e seus respectivos dados
    for (int i = 1; i < matriz.length; i++) {
      System.out.println("---------- XX ----------");
      System.out.println("                         ");
      System.out.println("Usuário " + i);
      System.out.println("Nome: " + matriz[i][0]);
      System.out.println("CPF: " + matriz[i][1]);
      System.out.println("Email: " + matriz[i][2]);
      System.out.println("Num. Conta: " + matriz[i][4]);
      System.out.println("Saldo: " + matriz[i][5]);
      System.out.println("                         ");
      System.out.println("---------- XX ----------");
    }
  }

  public static String[][] mockUsuarios() {  // Banco de usuários para teste
    String[][] usuariosTeste = new String[4][6];

    usuariosTeste[1][0] = "Augusto";
    usuariosTeste[1][1] = "85217190000";
    usuariosTeste[1][2] = "augusto.citton@gmail.com";
    usuariosTeste[1][3] = "123";
    usuariosTeste[1][4] = "1";
    usuariosTeste[1][5] = "0";

    usuariosTeste[2][0] = "Bruno";
    usuariosTeste[2][1] = "66666666666";
    usuariosTeste[2][2] = "brunoj@gmail.com";
    usuariosTeste[2][3] = "123";
    usuariosTeste[2][4] = "2";
    usuariosTeste[2][5] = "500";

    usuariosTeste[3][0] = "Jon";
    usuariosTeste[3][1] = "33333333333";
    usuariosTeste[3][2] = "joaokanan@gmail.com";
    usuariosTeste[3][3] = "123";
    usuariosTeste[3][4] = "3";
    usuariosTeste[3][5] = "300";

    return usuariosTeste;
  }

}