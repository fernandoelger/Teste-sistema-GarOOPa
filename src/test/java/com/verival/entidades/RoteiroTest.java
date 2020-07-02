package com.verival.entidades;

import java.util.ArrayList;
import java.util.Collection;

import com.verival.entidades.geometria.Ponto;
import com.verival.entidades.geometria.Reta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoteiroTest {

    private ArrayList<Bairro> bairros;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private Roteiro roteiro;
    

    @BeforeEach
    public void setup() {
        bairros = new ArrayList<>();

        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));

        bairroOrigem = bairros.get(0);
        bairroDestino = bairros.get(3);
    }

    // Classes de Equivalência: testa a criação e o retorno da Rota,
    // o retorno do bairro de origem
    // o retorno do bairro de destino
    @Test
    public void roteiroTest() {
        roteiro = new Roteiro(bairroOrigem, bairroDestino, bairros);

        Ponto pOrig = bairroOrigem.getArea().pontoCentral();
        Ponto pDest = bairroOrigem.getArea().pontoCentral();

        assertEquals(new Reta(pOrig,pDest), roteiro.getRota());
        assertEquals(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0), roteiro.getBairroOrigem());
        assertEquals(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0), roteiro.getBairroDestino());
    }

    //Classes de equivalencia: apenas um caso já que o método só possui um comportamento
    @Test
    public void bairrosPercorridosTest() {
        roteiro = new Roteiro(bairroOrigem, bairroDestino, bairros);
        Collection<Bairro> expected = new ArrayList<>();
        expected.add(bairros.get(0));
        expected.add(bairros.get(1));
        expected.add(bairros.get(2));
        expected.add(bairros.get(3));
        assertEquals(expected, roteiro.bairrosPercoridos());
    }

    @Test
    public void toStringTest() {

        roteiro = new Roteiro(bairroOrigem, bairroDestino, bairros);

        String bairroDest = "Bairro [area=" + "Area [pInfDir=" + "Ponto [x=" + 60 + ", y=" + 20 + "]" + ", pSupEsq=" + "Ponto [x=" + 40 + ", y=" + 30 + "]" + "]" + ", nome=" + "Auxiliadora" + "]";

        String bairroOrig = "Bairro [area=" + "Area [pInfDir=" + "Ponto [x=" + 30 + ", y=" + 30 + "]" + ", pSupEsq=" + "Ponto [x=" + 10 + ", y=" + 40 + "]" + "]" + ", nome=" + "Bom Fim" + "]";

        String expected = "Roteiro [bairroDestino=" + bairroDest + ", bairroOrigem=" + bairroOrig + "]";

        assertEquals(expected, roteiro.toString());
    }
}