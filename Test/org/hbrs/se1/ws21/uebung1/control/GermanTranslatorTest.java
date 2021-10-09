package org.hbrs.se1.ws21.uebung1.control;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {
    GermanTranslator gt;
    @BeforeEach
    void setUp(){
        gt = new GermanTranslator();
    }
    @AfterEach
    void tearDown(){
        gt = null;
    }

    @Test
    void translateTest(){
        assertEquals("eins",gt.translateNumber(1));
        assertEquals("zehn",gt.translateNumber(10));
        assertEquals("fünf",gt.translateNumber(5));
        assertEquals("Übersetzung der Zahl [11] nicht möglich [1.0]",gt.translateNumber(11));
        assertEquals("Übersetzung der Zahl [0] nicht möglich [1.0]",gt.translateNumber(0));
        assertEquals("Übersetzung der Zahl [-12] nicht möglich [1.0]",gt.translateNumber(-12));
    }
}