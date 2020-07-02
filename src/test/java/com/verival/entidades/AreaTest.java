package com.verival.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.verival.entidades.geometria.Area;
import com.verival.entidades.geometria.Ponto;
import com.verival.entidades.geometria.Reta;
import com.verival.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AreaTest {

    private Area area;

    @BeforeEach
    public void setup() {
        area = new Area(new Ponto(10,50), new Ponto(60,10));
    }

    //testando a exceção do construtor com valor limte
    @ParameterizedTest
    @CsvSource({"20,50,20,10", "21,50,20,10", "10,30,60,30", "10,30,60,31"})
    public void constructorExceptionTest(int p1x, int p1y, int p2x, int p2y) {

        assertThrows(IllegalArgumentException.class,
        ()->{
            Area areaException = new Area(new Ponto(p1x,p1y),new Ponto(p2x,p2y));
        });
    }

    @Test
    public void pontoCentralTest(){
        Ponto p = area.pontoCentral();

        assertEquals(35, p.getX());
        assertEquals(30, p.getY());
    }
    
    //Classes de equivalência: um ponto denro, em cima, em baixo,
    // a esqueda e a direita da área
    //Valor limite: pontos em cima das retas da área
    @ParameterizedTest
    @CsvSource({"20,20,0", "20,55,1", "65,15,4", "15,5,2", "5,15,8"})
    public void codificaPontoTest(int x, int y, int expected){
        int actual = area.codificaPonto(new Ponto(x,y));

        assertEquals(expected, actual);
    }

    //Classes de equivalência: reta dentro, instersecta pelas 8 áreas,
    // toda fora nas 8 áreas, intersecta mais de uma área (5 casos),
    // toda fora intersectando mais de uma área (5 casos)
    //Valor limite: 
    @ParameterizedTest
    @CsvSource({"20,35, 30,35, 0", //reta dentro
                "30,40, 30,60, 2", "50,20, 70,20, 2", "30,20, 30,5, 2", "5,30, 20,30, 2", //instersecta pelas 8 áreas porém acho que precisa das áreas diagonais
                "70,20, 80,20, 1", "70,60, 80,60, 1", "20,60, 30,60, 1", "5,60, 5,70, 1", "5,20, 5,30, 1", "5,5, 7,7, 1", "20,5, 30,5, 1", "70,5, 80,5, 1", //toda fora nas 8 áreas
                "5,20, 20,5, 2", "50,5, 70,20, 2", "70,40, 50,60, 2", "20,60, 5,30, 2", //intersecta mais de uma área  - 4 casos
                "1,20, 12,5, 1", "55,1, 62,12, 1", "70,45, 55,60, 1", "11,70, 1,45, 1"}) //toda fora intersectando mais de uma área - 4 casos
    public void classificaTest(int x1, int y1, int x2, int y2, int expected){
        SituacaoReta actual = area.classifica(new Reta(new Ponto(x1,y1), new Ponto(x2,y2)));

        assertEquals(expected, actual.ordinal());
    }
}