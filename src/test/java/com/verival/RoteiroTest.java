package com.verival;

import java.util.ArrayList;

import com.verival.entidades.geometria.Ponto;
import com.verival.entidades.geometria.Reta;
import com.verival.entidades.Roteiro;
import com.verival.entidades.Bairro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoteiroTest {

    private ArrayList<Bairro> bairros;

    @BeforeEach
    public void setup() {
        bairros = new ArrayList<>();

        bairros.add(Bairro.novoBairroRetangular("Passo das Pedras", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Leopoldina", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Protásio Alves", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Mário Quintana", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Jari", new Ponto(40,20), 20, 10, 20.0));
    }

    @Test
    public void rotaTest() {
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Reta expected = new Reta(new Ponto(40,35), new Ponto(50,15));
        assertEquals(expected, roteiro.getRota());
    }
}