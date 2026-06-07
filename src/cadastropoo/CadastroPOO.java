/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import cadastropoo.model.*;

public class CadastroPOO {
    public static void main(String[] args) {
        try {
            // --- PESSOAS FÍSICAS ---
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "Ana", "11111111111", 25));
            repo1.inserir(new PessoaFisica(2, "Carlos", "22222222222", 52));

            repo1.persistir("fisica.bin");
            System.out.println("Dados de Pessoa Fisica Armazenados");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar("fisica.bin");
            System.out.println("Dados de Pessoa Fisica Recuperados");

            for (PessoaFisica pf : repo2.obterTodos()) {
                pf.exibir();
            }

            // --- PESSOAS JURÍDICAS ---
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(3, "XPTO Sales", "33333333333333"));
            repo3.inserir(new PessoaJuridica(4, "XPTO Solutions", "44444444444444"));

            repo3.persistir("juridica.bin");
            System.out.println("Dados de Pessoa Juridica Armazenados");

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar("juridica.bin");
            System.out.println("Dados de Pessoa Juridica Recuperados");

            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}