package util;

import modelo.Cliente;
import java.util.ArrayList;
import java.util.Scanner;
import main.Main;


public class Cadastro {
    public static ArrayList<Cliente> arrClientes = new ArrayList<>();
    
    public static void cadastrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Conta: ");
        int conta = scanner.nextInt();
        
        float saldo = 0;

        Cliente cliente = new Cliente(conta, saldo, nome);
        arrClientes.add(cliente);
        
        System.out.println("Cadastrado com sucesso.");
        
        Main.menuPrincipal();
    }
    
    public static Cliente encontrarCliente(int conta){
        for(Cliente cliente : arrClientes){
            
            if(cliente.getConta() == conta){
              return cliente;
            }

        }
        return null;
    }
    
    public static void cadastrarPadrao() {
        Cliente cliente1 = new Cliente(84, 1200, "Toguro");
        Cliente cliente2 = new Cliente(284, 1569, "Sayuri");
        
        arrClientes.add(cliente1);
        arrClientes.add(cliente2);
    }
    
}