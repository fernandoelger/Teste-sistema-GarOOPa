package com.verival.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.verival.entidades.geometria.Area;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.Test;

public class BairroTest {

    // Teste Unitario para ver a contrução e os métodos de retorno de uma instância de Area
    @Test
    public void novoBairroQuadradoTest(){
        Bairro bairro = Bairro.novoBairroQuadrado("Moinhos", new Ponto(200,200), 100, 20);
        assertEquals ("Moinhos", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte(), 0.0001);
        assertEquals(new Area(new Ponto(200,200), new Ponto(300,100)), bairro.getArea());
    }

    // Teste Unitario para ver a contrução e os métodos de retorno de uma instância de Area
    @Test
    public void novoBairroRetangularTest(){
        Bairro bairro = Bairro.novoBairroRetangular("Petropolis", new Ponto(10,40), 20, 10, 10);
        assertEquals("Petropolis", bairro.getNome());
        assertEquals(10, bairro.getCustoTransporte(), 0.0001);
        assertEquals(new Area(new Ponto(10,40), new Ponto(30,30)), bairro.getArea());
    }

    // Teste Unitario: verifica se o valor do custo é > 0
    @Test
    public void alteraCustoTransporteTest() {
        Bairro bairro = Bairro.novoBairroRetangular("Petropolis", new Ponto(10,40), 20, 10, 10);
        bairro.alteraCustoTransporte(35.0);
        assertEquals(35, bairro.getCustoTransporte(), 0.0001);
    }

    @Test
    public void alteraCustoTransporteExceptionTest() {
        Bairro bairro = Bairro.novoBairroRetangular("Petropolis", new Ponto(10,40), 20, 10, 10);

        assertThrows(IllegalArgumentException.class,
        ()->{
            bairro.alteraCustoTransporte(-1.0);
        });
    }
}