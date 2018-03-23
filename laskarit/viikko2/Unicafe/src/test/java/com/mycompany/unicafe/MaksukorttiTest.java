package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void otaRahaaEiMeneNeg(){
        kortti.otaRahaa(1200);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    @Test
    public void otaRahaaOikein(){
        kortti.otaRahaa(900);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    @Test 
    public void saldoOikein(){
        assertTrue(kortti.saldo()==1000);
    }
    @Test
    public void laitaOikein(){
        kortti.lataaRahaa(100);
        assertEquals("saldo: 11.0", kortti.toString());
    }
    @Test
    public void otaRahaaFalse(){
        assertTrue(kortti.otaRahaa(1200)==false);
    }
    @Test
    public void otaRahaaTrue(){
        assertTrue(kortti.otaRahaa(900)==true);
    }
}
