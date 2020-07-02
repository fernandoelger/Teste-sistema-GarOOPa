package com.verival;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.verival.entidades.Passageiro;

import org.junit.jupiter.api.Test;

public class PassageiroTest {
    
    @Test
    public void novoPassageiroTest() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Adalberto");
        assertEquals("Adalberto", passageiro.getNome());
        assertEquals("12345678910", passageiro.getCPF());
        assertEquals(8, passageiro.getPontuacaoAcumulada());
        assertEquals(1, passageiro.getQtdadeAvaliacoes());
        assertEquals(8, passageiro.getPontuacaoMedia());
    }

    @Test
    public void passageiroExistenteTest() {
        Passageiro passageiro = Passageiro.passageiroExistente("123456789", "Adalberto 2", 10, 5);
        assertEquals("Adalberto 2", passageiro.getNome());
        assertEquals("123456789", passageiro.getCPF());
        assertEquals(10, passageiro.getPontuacaoAcumulada());
        assertEquals(5, passageiro.getQtdadeAvaliacoes());
        assertEquals(2, passageiro.getPontuacaoMedia());
    }

    @Test
    public void infoPontuacaoTest() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Adalberto");
        passageiro.infoPontuacao(5);
        assertEquals(13, passageiro.getPontuacaoAcumulada());
        assertEquals(2, passageiro.getQtdadeAvaliacoes());
    }
}