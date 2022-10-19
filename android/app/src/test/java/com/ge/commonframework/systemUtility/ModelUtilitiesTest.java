/**
 * @file ModelUtilitiesTest.java
 * @author Ryan Lee(320006284)
 * @date 08/14/2015
 * @brief  A unit test class testing ModelUtilities class
 * @copyright General Electric Corporation (Confidential) All rights reserved.
 */

package com.ge.commonframework.systemUtility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.NullPointerException;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by RyanLee on 8/6/15/FW32.
 */
public class ModelUtilitiesTest {
    private static int index = 0;

    // Those are limits for temperatures
    private final int MAX_FAHRENHEIT = 10000;
    private final int MIN_FAHRENHEIT = -9999;
    private final int MAX_CELSIUS = 5538;
    private final int MIN_CELSIUS = -5573;

    @Before
    public void setUp() throws Exception {
        index++;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParseMap() throws Exception {

    }

    @Test
    public void testXmlParsing() throws Exception {

    }

    @Test
    public void testACMReturnData() throws Exception {

    }

    /**
     * Testing fahrenheitToCelsius(int fahrenheitTemperature)
     * @throws Exception: Out of range exception
     */
    @Test
    public void testFahrenheitToCelsius() throws Exception {
        System.out.println(index + "] testFahrenheitToCelsius");

        // Passing 0
        int result = ModelUtilities.fahrenheitToCelsius(0);
        assertEquals(-18, result);

        // Passing normal values
        result = ModelUtilities.fahrenheitToCelsius(32);
        assertEquals(0, result);

        result = ModelUtilities.fahrenheitToCelsius(50);
        assertEquals(10, result);

        result = ModelUtilities.fahrenheitToCelsius(-50);
        assertEquals(-46, result);

        result = ModelUtilities.fahrenheitToCelsius(500);
        assertEquals(260, result);

        result = ModelUtilities.fahrenheitToCelsius(-500);
        assertEquals(-296, result);

        result = ModelUtilities.fahrenheitToCelsius(5000);
        assertEquals(2760, result);

        result = ModelUtilities.fahrenheitToCelsius(-5000);
        assertEquals(-2796, result);

        // Passing a maximum value
        result = ModelUtilities.fahrenheitToCelsius(MAX_FAHRENHEIT);
        assertEquals(MAX_CELSIUS, result);

        // Passing a minimum value
        result = ModelUtilities.fahrenheitToCelsius(MIN_FAHRENHEIT);
        assertEquals(MIN_CELSIUS, result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.fahrenheitToCelsius(MAX_FAHRENHEIT + 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.fahrenheitToCelsius(MIN_FAHRENHEIT - 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }
    }

    /**
     * Testing fahrenheitToCelsius(String fahrenheitTemperature)
     * @throws Exception: Out of range exception
     */
    @Test
    public void testFahrenheitToCelsiusWithString() throws Exception {
        System.out.println(index + "] testFahrenheitToCelsiusWithString");

        // Passing 0
        String result = ModelUtilities.fahrenheitToCelsius("0");
        assertEquals("-18", result);

        // Passing normal values
        result = ModelUtilities.fahrenheitToCelsius("32");
        assertEquals("0", result);

        result = ModelUtilities.fahrenheitToCelsius("50");
        assertEquals("10", result);

        result = ModelUtilities.fahrenheitToCelsius("-50");
        assertEquals("-46", result);

        result = ModelUtilities.fahrenheitToCelsius("500");
        assertEquals("260", result);

        result = ModelUtilities.fahrenheitToCelsius("-500");
        assertEquals("-296", result);

        result = ModelUtilities.fahrenheitToCelsius("5000");
        assertEquals("2760", result);

        result = ModelUtilities.fahrenheitToCelsius("-5000");
        assertEquals("-2796", result);

        // Passing a maximum value
        result = ModelUtilities.fahrenheitToCelsius(String.valueOf(MAX_FAHRENHEIT));
        assertEquals(String.valueOf(MAX_CELSIUS), result);

        // Passing a minimum value
        result = ModelUtilities.fahrenheitToCelsius(String.valueOf(MIN_FAHRENHEIT));
        assertEquals(String.valueOf(MIN_CELSIUS), result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.fahrenheitToCelsius(String.valueOf(MAX_FAHRENHEIT + 1));
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.fahrenheitToCelsius(String.valueOf(MIN_FAHRENHEIT - 1));
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Wrong format: expected NumberFormatException
            ModelUtilities.fahrenheitToCelsius("");
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try{
            // Wrong format: expected NumberFormatException
            ModelUtilities.fahrenheitToCelsius("ABC$@#");
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }
    }

    /**
     * Testing celsiusToFahrenheit(int celsiusTemperature)
     * @throws Exception: Out of range exception
     */
    @Test
    public void testCelsiusToFahrenheit() throws Exception {
        System.out.println(index + "] testCelsiusToFahrenheit");

        // Passing 0
        int result = ModelUtilities.celsiusToFahrenheit(0);
        assertEquals(32, result);

        // Passing normal values
        result = ModelUtilities.celsiusToFahrenheit(-18);
        assertEquals(0, result);

        result = ModelUtilities.celsiusToFahrenheit(50);
        assertEquals(122, result);

        result = ModelUtilities.celsiusToFahrenheit(-50);
        assertEquals(-58, result);

        result = ModelUtilities.celsiusToFahrenheit(500);
        assertEquals(932, result);

        result = ModelUtilities.celsiusToFahrenheit(-500);
        assertEquals(-868, result);

        result = ModelUtilities.celsiusToFahrenheit(5000);
        assertEquals(9032, result);

        result = ModelUtilities.celsiusToFahrenheit(-5000);
        assertEquals(-8968, result);

        // Passing a maximum value
        result = ModelUtilities.celsiusToFahrenheit(MAX_CELSIUS);
        assertEquals(MAX_FAHRENHEIT, result);

        // Passing a minimum value
        // Offset: +1 to fill in the conversion error
        result = ModelUtilities.celsiusToFahrenheit(MIN_CELSIUS);
        assertEquals(MIN_FAHRENHEIT, result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.celsiusToFahrenheit(MAX_CELSIUS + 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.celsiusToFahrenheit(MIN_CELSIUS - 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }
    }

    /**
     * Testing celsiusToFahrenheit(String celsiusTemperature)
     * @throws Exception: Out of range exception
     */
    @Test
    public void testCelsiusToFahrenheitWithString() throws Exception {
        System.out.println(index + "] testCelsiusToFahrenheitWithString");

        // Passing 0
        String result = ModelUtilities.celsiusToFahrenheit("0");
        assertEquals("32", result);

        // Passing normal values
        result = ModelUtilities.celsiusToFahrenheit("-18");
        assertEquals("0", result);

        result = ModelUtilities.celsiusToFahrenheit("50");
        assertEquals("122", result);

        result = ModelUtilities.celsiusToFahrenheit("-50");
        assertEquals("-58", result);

        result = ModelUtilities.celsiusToFahrenheit("500");
        assertEquals("932", result);

        result = ModelUtilities.celsiusToFahrenheit("-500");
        assertEquals("-868", result);

        result = ModelUtilities.celsiusToFahrenheit("5000");
        assertEquals("9032", result);

        result = ModelUtilities.celsiusToFahrenheit("-5000");
        assertEquals("-8968", result);

        // Passing a maximum value
        result = ModelUtilities.celsiusToFahrenheit(String.valueOf(MAX_CELSIUS));
        assertEquals(String.valueOf(MAX_FAHRENHEIT), result);

        // Passing a minimum value
        result = ModelUtilities.celsiusToFahrenheit(String.valueOf(MIN_CELSIUS));
        assertEquals(String.valueOf(MIN_FAHRENHEIT), result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.celsiusToFahrenheit(String.valueOf(MAX_CELSIUS + 1));
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.celsiusToFahrenheit(String.valueOf(MIN_CELSIUS - 1));
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Wrong format: expected NumberFormatException
            ModelUtilities.celsiusToFahrenheit("");
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Wrong format: expected NumberFormatException
            ModelUtilities.celsiusToFahrenheit("ABC$@#");
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }
    }

    @Test
    public void testTempWithCurrentUnit() throws Exception {
        System.out.println(index + "] testTempWithCurrentUnit");

        // Passing 0
        int result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, 0);
        assertEquals(-18, result);

        // Passing normal values
        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, 32);
        assertEquals(0, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, 50);
        assertEquals(10, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, -50);
        assertEquals(-46, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, 500);
        assertEquals(260, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, -500);
        assertEquals(-296, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, 5000);
        assertEquals(2760, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, -5000);
        assertEquals(-2796, result);

        // Passing a maximum value
        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, MAX_FAHRENHEIT);
        assertEquals(MAX_CELSIUS, result);

        // Passing a minimum value
        result = ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, MIN_FAHRENHEIT);
        assertEquals(MIN_CELSIUS, result);

        // Test fahrenheit cases
        result = ModelUtilities.tempWithCurrentUnit(CommonData.FAHRENHEIT, 100);
        assertEquals(100, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.FAHRENHEIT, -100);
        assertEquals(-100, result);

        // Passing max and min value
        result = ModelUtilities.tempWithCurrentUnit(CommonData.FAHRENHEIT, MAX_FAHRENHEIT);
        assertEquals(MAX_FAHRENHEIT, result);

        result = ModelUtilities.tempWithCurrentUnit(CommonData.FAHRENHEIT, MIN_FAHRENHEIT);
        assertEquals(MIN_FAHRENHEIT, result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, MAX_FAHRENHEIT + 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.tempWithCurrentUnit(CommonData.CELSIUS, MIN_FAHRENHEIT - 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try{
            // Passing wrong temperature unit: Expected: IllegalArgumentException
            ModelUtilities.tempWithCurrentUnit(null, 100);
        }
        catch(NullPointerException e){
            assertTrue(true);
        }
    }

    @Test
    public void testTempStringWithCurrentUnit() throws Exception {
        System.out.println(index + "] testTempStringWithCurrentUnit");

        // Passing 0
        String result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, 0);
        assertEquals("-18" + CommonData.CELSIUS_UNIT, result);

        // Passing normal values
        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, 32);
        assertEquals("0" + CommonData.CELSIUS_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, 50);
        assertEquals("10" + CommonData.CELSIUS_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, -50);
        assertEquals("-46" + CommonData.CELSIUS_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, 500);
        assertEquals("260" + CommonData.CELSIUS_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, -500);
        assertEquals("-296" + CommonData.CELSIUS_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, 5000);
        assertEquals("2760" + CommonData.CELSIUS_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, -5000);
        assertEquals("-2796" + CommonData.CELSIUS_UNIT, result);

        // Passing a maximum value
        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, MAX_FAHRENHEIT);
        assertEquals("" + MAX_CELSIUS + CommonData.CELSIUS_UNIT, result);

        // Passing a minimum value
        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, MIN_FAHRENHEIT);
        assertEquals("" + MIN_CELSIUS + CommonData.CELSIUS_UNIT, result);

        // Test fahrenheit case
        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.FAHRENHEIT, 100);
        assertEquals("100" + CommonData.FAHRENHEIT_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.FAHRENHEIT, -100);
        assertEquals("-100" + CommonData.FAHRENHEIT_UNIT, result);

        // Passing max and min value
        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.FAHRENHEIT, MAX_FAHRENHEIT);
        assertEquals("" + MAX_FAHRENHEIT + CommonData.FAHRENHEIT_UNIT, result);

        result = ModelUtilities.tempStringWithCurrentUnit(CommonData.FAHRENHEIT, MIN_FAHRENHEIT);
        assertEquals("" + MIN_FAHRENHEIT + CommonData.FAHRENHEIT_UNIT, result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, MAX_FAHRENHEIT + 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.tempStringWithCurrentUnit(CommonData.CELSIUS, MIN_FAHRENHEIT - 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try{
            // Passing wrong temperature unit: Expected: IllegalArgumentException
            ModelUtilities.tempStringWithCurrentUnit("", 100);
        }
        catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void testConvertToFahrenheitTemperature(){
        System.out.println(index + "] testConvertToFahrenheitTemperature");

        // Passing normal values
        int result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, 0);
        assertEquals(32, result);

        // Passing normal values
        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, -18);
        assertEquals(0, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, 50);
        assertEquals(122, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, -50);
        assertEquals(-58, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, 500);
        assertEquals(932, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, -500);
        assertEquals(-868, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, 5000);
        assertEquals(9032, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, -5000);
        assertEquals(-8968, result);

        // Passing a maximum value
        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, MAX_CELSIUS);
        assertEquals(MAX_FAHRENHEIT, result);

        // Passing a minimum value
        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, MIN_CELSIUS);
        assertEquals(MIN_FAHRENHEIT, result);

        // Test fahrenheit cases
        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.FAHRENHEIT, 100);
        assertEquals(100, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.FAHRENHEIT, -100);
        assertEquals(-100, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.FAHRENHEIT, MAX_FAHRENHEIT);
        assertEquals(MAX_FAHRENHEIT, result);

        result = ModelUtilities.convertToFahrenheitTemperature(CommonData.FAHRENHEIT, MIN_FAHRENHEIT);
        assertEquals(MIN_FAHRENHEIT, result);

        try {
            // Passing a value exceeded a maximum value: Expected NumberFormatException
            ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, MAX_FAHRENHEIT + 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try {
            // Passing a value exceeded a minimum value: Expected NumberFormatException
            ModelUtilities.convertToFahrenheitTemperature(CommonData.CELSIUS, MIN_FAHRENHEIT - 1);
        }
        catch(NumberFormatException e){
            assertTrue(true);
        }

        try{
            // Passing wrong temperature unit: Expected: IllegalArgumentException
            ModelUtilities.convertToFahrenheitTemperature("", 100);
        }
        catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }
}