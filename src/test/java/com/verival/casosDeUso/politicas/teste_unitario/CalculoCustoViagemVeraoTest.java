package com.verival.casosDeUso.politicas.teste_unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.verival.casosDeUso.Politicas.CalculoCustoViagem;
import com.verival.casosDeUso.Politicas.CalculoCustoViagemVerao;
import com.verival.entidades.Bairro;
import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculoCustoViagemVeraoTest {
    
    private ArrayList<Bairro> bairros;
    private CalculoCustoViagem ccv;

    @BeforeEach
    public void setup() {
        ccv = new CalculoCustoViagemVerao();

        bairros = new ArrayList<>();

        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));
    }

    //Classes de Equivalencia: consegue o desconto e não consegue o desconto
    //Valor limite: pontuação média exatamente 9.0 (não consegue o desconto)
    @ParameterizedTest
    @CsvSource({"30,3,72.0", "5,3,0.0","9,1,0.0"})
    public void descontoPontuacaoTest(int pontuacaoAcumulada, int qtdadeAvaliacoes, double expected) {
        Passageiro passageiro = Passageiro.passageiroExistente("123456789", "Adalberto", pontuacaoAcumulada, qtdadeAvaliacoes);
        ccv.definePassageiro(passageiro);
        assertEquals(expected, ccv.descontoPontuacao());
    }

    //Classes de Equivalencia: consegue o desconto e não consegue o desconto
    //Valor limite: quantidade de bairros percorridos é 2 (não consegue o desconto)
    @ParameterizedTest
    @CsvSource({"0,3,8.0", "1,1,0.0","0,1,0.0"})
    public void descontoPromocaoSazonalTest(int bairroOrigem, int bairroDestino, double expected) {

        Roteiro roteiro = new Roteiro(bairros.get(bairroOrigem), bairros.get(bairroDestino), bairros);
        ccv.defineRoteiro(roteiro);

        assertEquals(expected, ccv.descontoPromocaoSazonal());
    }
}