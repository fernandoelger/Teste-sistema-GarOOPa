package com.verival.casosDeUso.politicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import com.verival.entidades.Bairro;
import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculoCustoViagemRelampagoTest {

    private ArrayList<Bairro> bairros;
    private Collection<Bairro> bairrosPercorridos;
    private CalculoCustoViagem ccv;
    private Roteiro roteiro;

    @BeforeEach
    public void setup() {
        ccv = new CalculoCustoViagemRelampago();

        bairros = new ArrayList<>();

        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));

        roteiro = mock(Roteiro.class);
        bairrosPercorridos = new ArrayList<Bairro>();
    }
    
    //Classes de equivalencia: pontuação média > 5.0 e qtdAvaliações > 30
    //                         pontuação média < 5.0 ou qtdAvaliações < 30
    //                         qtdAvaliações > 30
    //                         pontuação média > 5.0

    //Valor limite: pontuação média exatamente 5.0 e qtdAvaliações exatamente 30
              
    @ParameterizedTest
    @CsvSource({"200,32,4.0", "120,20,0.0", "10,31,0.0", "100,15,0.0",
                "150,30,0.0"})
    public void descontoPontuacaoRelampagoTest(int pontuacaoAcumulada, int qtdadeAvaliacoes, double expected) {
        
        bairrosPercorridos.add(bairros.get(0));
        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(3));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);
        
        Passageiro passageiro = Passageiro.passageiroExistente("123456789", "Adalberto", pontuacaoAcumulada, qtdadeAvaliacoes);
        ccv.definePassageiro(passageiro);
        assertEquals(expected, ccv.descontoPontuacao());
    }

    //Classes de Equivalencia: consegue o desconto e não consegue o desconto
    //Valor limite: quantidade de bairros percorridos é 2 (não consegue o desconto)
    @ParameterizedTest
    @CsvSource({"0,3,4.0","1,1,0.0","1,4,0.0"})
    public void descontoPromocaoSazonalTestCaso1() {

        bairrosPercorridos.add(bairros.get(0));
        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(3));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        assertEquals(4.0, ccv.descontoPromocaoSazonal());
    }

    public void descontoPromocaoSazonalTestCaso2() {

        bairrosPercorridos.add(bairros.get(1));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        assertEquals(0.0, ccv.descontoPromocaoSazonal());
    }

    public void descontoPromocaoSazonalTestCaso3() {

        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(3));
        bairrosPercorridos.add(bairros.get(4));

        assertEquals(0.0, ccv.descontoPromocaoSazonal());
    }
}