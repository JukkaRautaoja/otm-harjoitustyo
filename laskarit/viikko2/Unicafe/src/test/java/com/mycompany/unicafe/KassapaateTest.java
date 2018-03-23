/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jukka
 */
public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paate=new Kassapaate();
        kortti=new Maksukortti(1000);
        
    }
    
    @Test 
    public void saldoOikein(){
        assertTrue(paate.kassassaRahaa()==100000);
    }
     @Test 
    public void lounaatEduOikein(){
        assertTrue(paate.edullisiaLounaitaMyyty()==0);
    }
     @Test 
    public void lounaatMauOikein(){
        assertTrue(paate.maukkaitaLounaitaMyyty()==0);
    }
    @Test 
    public void myyOikein(){
        assertTrue(paate.syoEdullisesti(1000)==760);
        assertTrue(paate.edullisiaLounaitaMyyty()==1);
    }
    @Test 
    public void kassanRahatOikeinMyy(){
        paate.syoEdullisesti(240);
        assertTrue(paate.kassassaRahaa()==100240);
        paate.syoMaukkaasti(400);
        assertTrue(paate.kassassaRahaa()==100640);
    }
    @Test 
    public void eiRahaa(){
        assertTrue(paate.syoEdullisesti(100)==100);
        assertTrue(paate.edullisiaLounaitaMyyty()==0);
        assertTrue(paate.syoMaukkaasti(100)==100);
        assertTrue(paate.maukkaitaLounaitaMyyty()==0);
        
    }
    @Test 
    public void myyOikeinKortillaEdu(){
        assertTrue(paate.syoEdullisesti(kortti)==true);
        assertTrue(paate.edullisiaLounaitaMyyty()==1);
        assertTrue(kortti.saldo()==760);
        assertTrue(paate.kassassaRahaa()==100000);
    }
    @Test
    public void lataaKortille(){
        paate.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo()==1100);
        assertTrue(paate.kassassaRahaa()==100100);
    }
    @Test
    public void lataaKortilleFalse(){
        paate.lataaRahaaKortille(kortti, -100);
        assertTrue(kortti.saldo()==1000);
        assertTrue(paate.kassassaRahaa()==100000);
    }
    @Test 
    public void myyOikeinKortillaMau(){
        assertTrue(paate.syoMaukkaasti(kortti)==true);
        assertTrue(paate.maukkaitaLounaitaMyyty()==1);
        assertTrue(kortti.saldo()==600);
        assertTrue(paate.kassassaRahaa()==100000);
    }
     @Test 
    public void eiRahaaKortillaMau(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.syoMaukkaasti(kortti)==false);
        assertTrue(paate.maukkaitaLounaitaMyyty()==2);
        assertTrue(kortti.saldo()==200);
        assertTrue(paate.kassassaRahaa()==100000);
    
    }
     @Test 
    public void eiRahaaKortillaEdu(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        assertTrue(paate.syoEdullisesti(kortti)==false);
        assertTrue(paate.edullisiaLounaitaMyyty()==4);
        assertTrue(kortti.saldo()==40);
        assertTrue(paate.kassassaRahaa()==100000);
    
    }
}
