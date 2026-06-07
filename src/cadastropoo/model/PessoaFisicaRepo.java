/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo.model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> lista = new ArrayList<>();

    public void inserir(PessoaFisica p) {
        lista.add(p);
    }

    public void alterar(PessoaFisica p) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
                lista.set(i, p);
                return;
            }
        }
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica p : lista) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return lista;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(nomeArquivo))) {
            lista = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}