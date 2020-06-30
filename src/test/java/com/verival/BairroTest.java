package com.verival;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.verival.entidades.Bairro;
import com.verival.entidades.geometria.Area;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.Test;

public class BairroTest {

    @Test
    public void novoBairroQuadradoTest(){
        Bairro bairro = Bairro.novoBairroQuadrado("Moinhos", new Ponto(100,100), 50, 20);
        assertEquals ("Moinhos", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte(), 0.0001);
        assertEquals(new Area (new Ponto(100,100), new Ponto(150,50)), bairro.getArea());
    }

    public void novoBairroRetangularTest(){
        Bairro bairro = Bairro.novoBairroRetangular("Petropolis", new Ponto(10,40), 20, 10, 10);
        assertEquals("Petropolis", bairro.getNome());
        assertEquals(10, bairro.getCustoTransporte(), 0.0001);
        assertEquals(new Area(new Ponto(10,40), new Ponto(30,30)), bairro.getArea());
    }
}