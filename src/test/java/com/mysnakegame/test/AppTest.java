package com.mysnakegame.test;

import java.awt.Point;
import java.util.ArrayList;

import com.mysnakegame.Snake;
import com.mysnakegame.SnakeSegment;
import com.mysnakegame.fruits.Fruit;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testValidateFalse() {
        boolean result = false;
        Snake snake = new Snake();
        Point point;
        for (SnakeSegment segment : snake.getSegments()) {
            point = segment.getLocation();
            result = Fruit.validateLocation(point, snake);
            if (result) {
                System.out.println("Result: " + result);
                Assert.fail("Fruit Validation failed");
            }
        }
        Assert.assertEquals(false, result);
    }

}
