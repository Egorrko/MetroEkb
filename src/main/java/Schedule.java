import java.io.*;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.*;

import com.google.inject.internal.cglib.core.$WeakCacheKey;
import javafx.util.Pair;
import org.glassfish.hk2.internal.HandleAndService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Schedule {
    ArrayList<Station> stations = new ArrayList<>();
    private class Station{
        String name;
        HashMap<Pair<Direction,Week>,
                ArrayList<LocalTime>> times = new HashMap<>();
    }
    private enum Direction{
        NORTH,
        SOUTH
    }
    private enum Week{
        WEEKDAY,
        WEEKEND
    }
    public Station getStation(String name){
        for(var i = 0; i < stations.size();i++){
            if(name == stations.get(i).name)
                return stations.get(i);
        }
        return null;
    }
    public LocalTime getTime(String name){
        getStation(name)
        return new LocalTime(0,0);
    }
    public LocalTime[] getTime(String name, int count){

    }
}