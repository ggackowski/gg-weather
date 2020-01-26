package gg;

import static org.junit.jupiter.api.Assertions.*;

class Ow_apiTest {

    @org.junit.jupiter.api.Test
    void connect() {
        assertEquals(true, Ow_api.INSTANCE.connect());
    }

    @org.junit.jupiter.api.Test
    void getActualWeather() {
        Weather w = Ow_api.INSTANCE.getActualWeather("Krakow");
        assertEquals(true, w.description != null);
    }

    @org.junit.jupiter.api.Test
    void getDataForPlot() {
        float[] f = Ow_api.INSTANCE.getDataForPlot("Krakow", "temp");
        assertNotEquals(0, f.length);
    }


    @org.junit.jupiter.api.Test
    void getDataForPlotFails() {
        float[] f = Ow_api.INSTANCE.getDataForPlot("Krakowfsdf", "temp");
        assertEquals(null, f);
    }


    @org.junit.jupiter.api.Test
    void getUV() {
        UV[] uv = Ow_api.INSTANCE.getUV("37.75", "-122.37", "2", "1498049953", "1498481991");
        assertNotEquals(0, uv.length);
    }

    @org.junit.jupiter.api.Test
    void getMapOne() {
        String map = Ow_api.INSTANCE.getMap("clouds_new", "2", "1", "1");
        assertNotEquals(null, map);
    }

    @org.junit.jupiter.api.Test
    void getMapTwo() {
        String map = Ow_api.INSTANCE.getMap("temp_new", "2", "1", "1");
        assertNotEquals(null, map);
    }

    @org.junit.jupiter.api.Test
    void fullTest() {
        Ow_api.INSTANCE.connect();
        Weather w = Ow_api.INSTANCE.getActualWeather("Kinshasa");
        float[] f = Ow_api.INSTANCE.getDataForPlot("Kinshasa", "temp");
        assertNotEquals(0, f.length + w.pressure);
    }

}