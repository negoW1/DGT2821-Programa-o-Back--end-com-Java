/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import cadastropoo.model.*;
import java.util.Scanner;

public class CadastroPOO {

    static Scanner sc = new Scanner(System.in);
    static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir por ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> incluir();
                case 2 -> alterar();
                case 3 -> excluir();
                case 4 -> exibirPorId();
                case 5 -> exibirTodos();
                case 6 -> salvar();
                case 7 -> recuperar();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    static String escolherTipo() {
        System.out.print("Tipo (F - Fisica  |  J - Juridica): ");
        return sc.nextLine().trim().toUpperCase();
    }

    static void incluir() {
        String tipo = escolherTipo();

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        if (tipo.equals("F")) {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
            System.out.println("Pessoa Fisica incluida com sucesso!");
        } else if (tipo.equals("J")) {
            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();
            repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
            System.out.println("Pessoa Juridica incluida com sucesso!");
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    static void alterar() {
        String tipo = escolherTipo();

        System.out.print("ID da entidade a alterar: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf == null) { System.out.println("Nao encontrado!"); return; }
            System.out.println("Dados atuais:"); pf.exibir();
            System.out.print("Novo nome: ");
            pf.setNome(sc.nextLine());
            System.out.print("Novo CPF: ");
            pf.setCpf(sc.nextLine());
            System.out.print("Nova idade: ");
            pf.setIdade(sc.nextInt()); sc.nextLine();
            repoFisica.alterar(pf);
            System.out.println("Alterado com sucesso!");
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj == null) { System.out.println("Nao encontrado!"); return; }
            System.out.println("Dados atuais:"); pj.exibir();
            System.out.print("Novo nome: ");
            pj.setNome(sc.nextLine());
            System.out.print("Novo CNPJ: ");
            pj.setCnpj(sc.nextLine());
            repoJuridica.alterar(pj);
            System.out.println("Alterado com sucesso!");
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    static void excluir() {
        String tipo = escolherTipo();

        System.out.print("ID da entidade a excluir: ");
        int id = sc.nextInt(); sc.nextLine();

        if (tipo.equals("F")) {
            repoFisica.excluir(id);
            System.out.println("Pessoa Fisica removida!");
        } else if (tipo.equals("J")) {
            repoJuridica.excluir(id);
            System.out.println("Pessoa Juridica removida!");
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    static void exibirPorId() {
        String tipo = escolherTipo();

        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();

        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf == null) System.out.println("Nao encontrado!");
            else pf.exibir();
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj == null) System.out.println("Nao encontrado!");
            else pj.exibir();
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    static void exibirTodos() {
        String tipo = escolherTipo();

        if (tipo.equals("F")) {
            System.out.println("\n-- Pessoas Fisicas --");
            if (repoFisica.obterTodos().isEmpty()) {
                System.out.println("Nenhum cadastro encontrado.");
            } else {
                for (PessoaFisica pf : repoFisica.obterTodos()) {
                    pf.exibir();
                    System.out.println("---");
                }
            }
        } else if (tipo.equals("J")) {
            System.out.println("\n-- Pessoas Juridicas --");
            if (repoJuridica.obterTodos().isEmpty()) {
                System.out.println("Nenhum cadastro encontrado.");
            } else {
                for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                    pj.exibir();
                    System.out.println("---");
                }
            }
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    static void salvar() {
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = sc.nextLine();
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos em: " + prefixo + ".fisica.bin e " + prefixo + ".juridica.bin");
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    static void recuperar() {
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = sc.nextLine();
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao recuperar: " + e.getMessage());
        }
    }
}