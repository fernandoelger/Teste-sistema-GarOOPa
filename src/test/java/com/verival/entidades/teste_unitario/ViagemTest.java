package com.verival.entidades.teste_unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.verival.entidades.Bairro;
import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;
import com.verival.entidades.Viagem;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.Test;


public class ViagemTest {

    private Viagem viagem;
    private ArrayList<Bairro> bairros;
    
    @Test
    public void viagemTest() {
        
        bairros = new ArrayList<Bairro>();

        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));

        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(3), bairros);

        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Adalberto");

        LocalDateTime time = LocalDateTime.now();

        viagem = new Viagem(1, time, roteiro, passageiro, 12.5);

        assertEquals(1, viagem.getId());
        assertEquals(time, viagem.getDataHora());
        assertEquals(roteiro, viagem.getRoteiro());
        assertEquals(passageiro, viagem.getPassageiro());
        assertEquals(12.5, viagem.getValorCobrado());
    }
}