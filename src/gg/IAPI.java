package gg;

public interface IAPI {
    boolean connect();
    Weather getActualWeather(String stationName);
    public float[] getDataForPlot(String city, String param);
    public UV[] getUV(String lattitude, String longtitude,
                      String cnt, String start, String end);
    public String getMap(String layer, String zoom, String x, String y);


}
