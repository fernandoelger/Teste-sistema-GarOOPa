package com.verival.casosDeUso.Repositorios;

import java.util.List;

import com.verival.entidades.Bairro;

public interface RepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);
    List<Bairro> recuperaListaBairros();
}