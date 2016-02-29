package gml.template.androidtemplate;

import org.junit.Test;

import gml.template.androidtemplate.util.Logic;

import static org.junit.Assert.assertEquals;

/**
 * Created by guomenglong on 16/2/26.
 */
public class LogicTest {
    @Test
    public void addingNegativeNumberShouldSubtract() {
        Logic logic = new Logic();
        assertEquals("6 + -2 must be 4", 4, logic.add(6, -2));
        assertEquals("2 + -5 must be -3", -3, logic.add(2, -5));
    }
}
