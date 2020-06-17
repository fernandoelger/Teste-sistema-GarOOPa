package com.verival.casosDeUso.Repositorios;

import java.util.List;

import com.verival.entidades.Passageiro;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();
    Passageiro recuperaPorCPF(String cpf);
    void atualizaPassageiro(Passageiro passageiro);
}