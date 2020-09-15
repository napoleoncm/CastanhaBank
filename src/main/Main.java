package main;

import util.Cadastro;
import modelo.Cliente;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Cadastro.cadastrarPadrao();
        menuPrincipal();
    }
    
    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("------------------------------------------------------\n");
        System.out.println("|              Bem vindo ao CastanhaBank!            |\n"); 
        System.out.println("------------------------------------------------------\n");
        System.out.println("|                   1 - Cadasttro                    |\n");
        System.out.println("|                   2 - Entrar                       |\n");
        System.out.println("|                   3 - Sair                         |\n");
        System.out.println("------------------------------------------------------\n");
        int opcao = scanner.nextInt();
        
        limparConsole();
        
        switch(opcao){
          case 1:
               Cadastro.cadastrar();
             break;
          case 2:
               System.out.println("Informe o numero da conta: ");
               int conta = scanner.nextInt();
               Cliente cliente = Cadastro.encontrarCliente(conta);
               
               if(cliente != null) {
                   entrar(cliente);
               } else {
                   System.out.println("Cliente não encontrado.");
                   menuPrincipal();
               }
               
               break;
          case 3:
              System.out.println("Obrigado por usar o CastanhaBank!");
              break;
          default:
              System.out.println("Operação indisponivel!");
              menuPrincipal();
        }
        
    }

    public static void entrar(Cliente cliente){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("------------------------------------------------------\n");
        System.out.println("              Bem vindo " + cliente.getNome() + "               \n");
        System.out.println("------------------------------------------------------\n");
        System.out.println("|                   1 - Saque                        |\n");
        System.out.println("|                   2 - Deposito                     |\n");
        System.out.println("|                   3 - Transferencia                |\n");
        System.out.println("|                   4 - Extrato                      |\n");
        System.out.println("|                   5 - Sair                         |\n");
        System.out.println("------------------------------------------------------\n");
        int opcao = scanner.nextInt();
        
        double valor;
        int conta;
        
        limparConsole();
        
        switch(opcao){
            case 1:
                System.out.println("Informe o valor de saque:");
                valor = scanner.nextDouble();
                cliente.sacar(valor);
               break;
            case 2:
                System.out.println("Informe o valor de deposito:");
                valor = scanner.nextDouble();
                cliente.depositar(valor);
               break;
            case 3:
                System.out.println("Informe o numero que conta que será transferido:\n");
                conta = scanner.nextInt();
                System.out.println("Informe o valor a ser transferido:\n");
                valor = scanner.nextDouble();
                cliente.transferir(conta, valor);
               break;
            case 4:
                cliente.extrato();
               break;
            case 5:
                menuPrincipal();
                break;
            default:
                System.out.println("Operação indisponivel!");
        }
        
        entrar(cliente);
        
    }
    
    private static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch(Exception ex) {
            //
        }
    }
}