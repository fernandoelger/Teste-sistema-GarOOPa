package com.verival.casosDeUso.politicas;

import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;

public class CustoViagem {
    private CalculoCustoViagem ccv;
    
    public CustoViagem(CalculoCustoViagem ccv){
        this.ccv = ccv;
    }            

    public double custoViagem(Roteiro roteiro,Passageiro passageiro){
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
}