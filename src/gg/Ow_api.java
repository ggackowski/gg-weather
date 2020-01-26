package gg;

import com.google.gson.*;
import javafx.scene.image.Image;

import java.net.URL;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ow_api implements IAPI {

    public static final Ow_api INSTANCE = new Ow_api();

    static int numberOfCalls;
    static LocalTime time;
    String APIkey =  "cd63b7543809bce5009d0e5b9f73053c";


    /**
     * Konstruktor Ow_api
     * Wyrzuca wyjątek, jeśli instacja już gdzieś powstała.
     */
    private Ow_api() {
        if (INSTANCE != null)
            throw new IllegalStateException("Singleton already constructed");
        numberOfCalls = 0;
        time = LocalTime.now();
    }

    /**
     * Funkcja connect sprawdza połączenie z API
     */
    public boolean connect() {
        if (updateAndCheck()) {
            System.out.println("lacze sie " + numberOfCalls);
            return true;
        }
        return false;

    }

    /**
     * @param stationName - nazwa stacji
     * @return Zwraca obiekt Weather przechowujący aktualną pogodę w danym miejscu
     * Funkcja pobierająca z API informacje o pogodzie w danym miejscu
     */
    public Weather getActualWeather(String stationName) {
        if (updateAndCheck()) {
            String ask = "https://api.openweathermap.org/data/2.5/weather?q=";
            try {
                URL url = new URL(ask + stationName + "&APPID=" + APIkey);
                System.out.println("I did query the API now; " + (49 - numberOfCalls) + " calls left" );
                String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
                JsonParser jsonParser = new JsonParser();
                Double temperature = jsonParser.parse(out)
                        .getAsJsonObject().get("main")
                        .getAsJsonObject().get("temp").getAsDouble();
                String description = jsonParser.parse(out).getAsJsonObject()
                        .getAsJsonArray("weather").get(0)
                        .getAsJsonObject().get("description").getAsString();
                int pressure = jsonParser.parse(out)
                        .getAsJsonObject().get("main")
                        .getAsJsonObject().get("pressure").getAsInt();
                int humidity = jsonParser.parse(out)
                        .getAsJsonObject().get("main")
                        .getAsJsonObject().get("humidity").getAsInt();
                Double wind = jsonParser.parse(out)
                        .getAsJsonObject().get("wind")
                        .getAsJsonObject().get("speed").getAsDouble();
                int clouds = jsonParser.parse(out)
                        .getAsJsonObject().get("clouds")
                        .getAsJsonObject().get("all").getAsInt();
                Weather w = new Weather(temperature, description, pressure, humidity, clouds, wind);
                return w;
            } catch(java.net.MalformedURLException e) {
                System.out.println("There was a problem with an URL " + e.getLocalizedMessage());

            }
            catch(java.io.IOException e) {
                System.out.println("There was a problem with IO");
            }
        }
        return null;


    }

    /**
     * @param city - miasto dla którego ma zostać wykonany wykres
     * @param param - typ danych jaki ma zostać uwzgędniony na wykresie np temperatura
     * @return Funkcja zwraca tablicę wartości wykorzystywaną następnie do stworzenia wykresu.
     */
    public float[] getDataForPlot(String city, String param) {
        if (updateAndCheck()) {
            String query = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + APIkey;
            try {
                List<Float> resList = new LinkedList<>();
                float[] res;
                URL url = new URL(query);
                System.out.println("I did query the API now; " + (49 - numberOfCalls) + " calls left");
                String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
                JsonParser jsonParser = new JsonParser();
                if (param.equals("wind")) {
                    JsonArray arr = jsonParser.parse(out).
                            getAsJsonObject().get("list").getAsJsonArray();
                    for (JsonElement el : arr) {
                        resList.add(el.getAsJsonObject().get("wind").getAsJsonObject()
                                .get("speed").getAsFloat());
                    }
                    res = new float[resList.size()];
                    for (int i = 0; i < resList.size(); ++i) {
                        res[i] = resList.get(i);
                        System.out.println(res[i]);
                    }
                    return res;

                } else {

                    JsonArray arr = jsonParser.parse(out).
                            getAsJsonObject().get("list").getAsJsonArray();
                    for (JsonElement el : arr) {
                        resList.add(el.getAsJsonObject().get("main").getAsJsonObject()
                                .get(param).getAsFloat());
                    }
                    res = new float[resList.size()];
                    for (int i = 0; i < resList.size(); ++i) {
                        res[i] = resList.get(i) - 273;
                        System.out.println(res[i]);
                    }
                    return res;

                }
            }
            catch(java.io.IOException e) {
                System.out.println("There was a problem with IO");
            }

        }
        return null;
    }

    /**
     * @param lattitude - szeregość geograficzna
     * @param longtitude - wysokość geograficzna
     * @param cnt - liczba dni
     * @param start - czas początkowy (UNIX)
     * @param end - czas końcowy (UNIX)
     * @return Funkcja zwraca tablicę obiektów UV - każdy z nich przechowuje informację pobraną z API
     * Funkcja łączy się z API i pobiera informacje o promieniowaniu UV dla danego położenia.
     */
    public UV[] getUV(String lattitude, String longtitude,
                    String cnt, String start, String end) {
        if (updateAndCheck()) {
            try {
                String query = "https://api.openweathermap.org/data/2.5/uvi/history?appid=" + APIkey + "&lat=" + lattitude + "&lon=" + longtitude + "&cnt=" + cnt + "&start=" + start + "&end=" + end;
                System.out.println(query);
                URL url = new URL(query);

                System.out.println("I did query the API now; " + (49 - numberOfCalls) + " calls left");
                String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
                JsonParser jsonParser = new JsonParser();
                Gson gson = new Gson();
                UV[] uvArray = gson.fromJson(out, UV[].class);
                System.out.println(uvArray.length);
                for (int i = 0; i < uvArray.length; ++i) System.out.println(uvArray[i].lat + " " + uvArray[i].value);
                return uvArray;
            } catch (java.net.MalformedURLException e) {
                System.out.println("internet error" + e.getMessage() + " " + e.getLocalizedMessage() + " " + e.getCause());
            }
            catch(java.io.IOException e) {
                System.out.println("There was a problem with IO");
            }
        }
        return null;

    }

    /**
     * @param layer - warstwa mapy
     * @param zoom - parametr zoom
     * @param x - położenie w poziomie
     * @param y - położenie w pionie
     * @return Funkcja zwraca adres mapy o danych parametrach
     */
    public String getMap(String layer, String zoom, String x, String y) {
            if (updateAndCheck()) {
                String query = "https://tile.openweathermap.org/map/" + layer + "/" + zoom + "/" + x + "/" + y + ".png?appid=" + APIkey;
                System.out.println("I did query the API now; " + (49 - numberOfCalls) + " calls left");
                return query;
            }

        return null;
    }

    /**
     * Funkcja zwiększa licznik łączenia z API, aby nie przekroczyć limitu
     */
    private void updateCounter() {
        if (time.getMinute() < LocalTime.now().getMinute()) {
            time = LocalTime.now();
            numberOfCalls = 0;
        }
        else numberOfCalls++;
    }

    /**
     * @return Funkcja sprawdza, czy liczba połączeń nie przekroczyła 50
     */
    private boolean notTooMuch() {
        return numberOfCalls < 50;
    }

    /**
     * @return Funkcja zwiększa licznik i sprawdza przekroczenie limitu połączeń
     */
    private boolean updateAndCheck() {
        updateCounter();
        return notTooMuch();
    }
}
