package gg;

public class UV {
   float lat;
   float lon;
   String date_iso;
   float value;
   int date;

   UV(float la, float lo, String d, float v, int dt) {
       lat = la;
       lon = lo;
       date_iso = d;
       value = v;
       date = dt;
   }
}
