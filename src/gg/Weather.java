package gg;

/**
 * Klasa przechowująca informacje o pogodzie w danym miejscu
 */
public class Weather {
    public String description;
    public int pressure;
    public double temperature;
    public int humidity;
    public int clouds;
    public double wind;

    Weather(double t, String desc, int p, int h, int cl, double win) {
        temperature = t;
        pressure = p;
        humidity = h;
        description = desc;
        clouds = cl;
        wind = win;
    }



}
