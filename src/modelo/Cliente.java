package modelo;

import util.Cadastro;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cliente {
    private int conta;
    private String nome;
    private double saldo;
    private final ArrayList<Operacoes> operacoes = new ArrayList<>();

    public Cliente(int conta, double saldo, String nome) {
        this.conta = conta;
        this.saldo = saldo;
        this.nome = nome;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void extrato(){
        System.out.println("\tEXTRATO");
        System.out.println("Nome: " + this.nome);
        System.out.println("Número da conta: " + this.conta);
        System.out.printf("Saldo atual: %.2f\n",this.saldo);
        
        Collections.sort(operacoes, new Comparator<Operacoes>() {
            @Override
            public int compare(Operacoes o1, Operacoes o2) {
                return (int) (o1.timestamp - o2.timestamp);
            }
        });
        
        for(Operacoes operacao : operacoes) {
            LocalDateTime date = Instant.ofEpochMilli(operacao.timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            System.out.println(String.format("Data: %s | Operação: %s", date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), operacao.getOperacao()));
        }
    }
    
    public void depositar(double valor){  
        final String DEPOSITAR = "Depositar";
        saldo += valor;
        System.out.println("Depositado: " + valor);
        System.out.println("Novo saldo: " + saldo + "\n"); 
	Operacoes operacao = new Operacoes(DEPOSITAR);
        operacoes.add(operacao);
    }
    
    public void sacar(double valor){
        final String SAQUE = "Saque";
        if(saldo >= valor){
            saldo -= valor;
            System.out.println("Sacado: " + valor);
            System.out.println("Novo saldo: " + saldo + "\n");
        } else {
            System.out.println("Saldo insuficiente. Faça um depósito\n");
        } 
        Operacoes operacao = new Operacoes(SAQUE);
        operacoes.add(operacao);
    }
    
    public void transferir(int conta, double valor){
        
        if(this.saldo < valor) {
            System.out.println("Dinheiro na sua conta bancaria insuficiente para fazer transferencias");
        } else {
            
            Cliente clienteEncontrado = Cadastro.encontrarCliente(conta);
            clienteEncontrado.setSaldo(clienteEncontrado.getSaldo() + valor);

            saldo -= valor;

            System.out.println("Valor Transferido: " + valor);
            System.out.println("Novo Saldo da conta:" + saldo);
        }
        
    }
    
    class Operacoes {
        
        private long timestamp;
        private String operacao;

        public Operacoes(String operacao) {
            this.timestamp = Instant.now().toEpochMilli();
            this.operacao = operacao;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getOperacao() {
            return operacao;
        }

        public void setOperacao(String operacao) {
            this.operacao = operacao;
        }
        
    }
    
}