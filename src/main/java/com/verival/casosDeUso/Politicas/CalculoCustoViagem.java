package com.verival.casosDeUso.politicas;

import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;

public interface CalculoCustoViagem {
    void defineRoteiro(Roteiro roteiro);
    void definePassageiro(Passageiro passageiro);
	public Roteiro getRoteiro();
	public Passageiro getPassageiro();
    double calculoCustoBasico();
    double descontoPontuacao();
    double descontoPromocaoSazonal();
    double custoViagem();
}