import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int menu = 0;

    String[][] usuarios = mockUsuarios();

    int numConta = 3;

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
<<<<<<< HEAD
    } else {
      System.out.println("Criando uma conta...");
      System.out.println("Digite seu nome completo");
      String nomeC = sc.nextLine();
      System.out.println("Digite seu CPF:");
      String cpf = sc.next();
      System.out.println("Digite sua senha:");
      String senha = sc.next();
      System.out.println("Digite seu Email");
      String email = sc.next();


      String[][] dadosClientes = {
              {"12345678900", "senha123", "email@email.com", "Nome1"},
              {"98765432100", "senha456", "email@email.com", "Nome2"},
              {"11122233344", "senha789", "email@email.com", "Nome3"},
      };

      boolean cpfJaCadastrado = false;
      for (String[] cliente : dadosClientes) {
        if (cliente[0].equals(cpf)) {
          cpfJaCadastrado = true;
          break;
        }
      }

      if (cpfJaCadastrado) {
        System.out.println("CPF já cadastrado. Tente novamente.");
      } else {
        System.out.println("Conta criada com sucesso!");
      }
    }
  }
=======
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

>>>>>>> 9f557c921e5f71d8004eb9485e931425d1107a43
}