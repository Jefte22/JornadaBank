import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String opcaoLogin;
    int opcao = 0;
    while (opcao != 1 && opcao != 2) {
      System.out.println("Olá, Seja bem vindo ao JORNADA BANK");
      System.out.println("Digite 1 se já tem uma conta");
      System.out.println("Digite 2 se deseja criar uma conta");
      opcaoLogin = sc.next();
      try {
        opcao = Integer.parseInt(opcaoLogin);
      } catch (NumberFormatException e) {
        opcao = 0;
      }
    }

    if (opcao == 1) {
      String cpf;
      String senha;
      String nomeC;
      String email;
      boolean sair = false;
      while (!sair) {
        System.out.println("Você está na seção de Login");
        System.out.println("Digite seu CPF ou digite /sair/ para sair");
        cpf = sc.next();
        if (cpf.equals("sair")) {
          sair = true;
          System.out.println("Saindo do sistema...");
        } else {
          System.out.println("Digite sua senha ou digite /sair/ para sair");
          senha = sc.next();
          if (senha.equals("sair")) {
            sair = true;
            System.out.println("Saindo do sistema...");
          } else {
            String[][] dadosClientes = {
                    {"12345678900", "senha123"},
                    {"98765432100", "senha456"},
                    {"11122233344", "senha789"}
            };

            // Esse brl é pra buscar o CPF e a Senha
            // Código do ChatGPT
            boolean loginSucesso = false;
            for (String[] cliente : dadosClientes) {
              if (cliente[0].equals(cpf) && cliente[1].equals(senha)) {
                loginSucesso = true;
                break;
              }
            }

            if (loginSucesso) {
              System.out.println("Login efetuado com sucesso!");
              sair = true;
            } else {
              System.out.println("CPF ou senha incorretos. Tente novamente.");
            }
          }
        }
      }
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
      System.out.println("Teste do Guto!");
      System.out.println("Teste Pedro");
    }
  }

}