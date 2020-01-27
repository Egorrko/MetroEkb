import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class ScheduleParser {
    private final String mainPage = "http://metro-ektb.ru/podrobnye-grafiki-po-stanciyam/";
    private Map<String, String> stationSchedulesLinks = new HashMap<String, String>();

    public ScheduleParser() {
        createDictStations();
    }

    public Set<String> GetStationsList() {
        return stationSchedulesLinks.keySet();
    }

    private void createDictStations() {
        Document html = null;
        try {
            html = Jsoup.connect(mainPage).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements doc = html.body().getElementsByClass("detail_schedule");
        for (Element element : doc.select("a"))
            stationSchedulesLinks.put(element.text(), element.attr("href"));
    }

    public Schedule fullScheduleForStation(String station) {
        var schedule = new Schedule();

        if (!stationSchedulesLinks.containsKey(station)) {
            return null;
        }
        var scheduleLink = stationSchedulesLinks.get(station);
        Document html = null;
        try {
            html = Jsoup.connect(scheduleLink).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        var pattern = Pattern.compile("[РВ][А-Яа-я\" ]*\" ");
        var timePattern = Pattern.compile("[0-2]\\d:[0-5]\\d");
        String doc = html.body().getElementsByClass("uss_catalog_content").text();
        var titleMatcher = pattern.matcher(doc);
        var splitted = pattern.split(doc);
        var titles = new ArrayList<String>();
        while (titleMatcher.find())
            titles.add(titleMatcher.group());

        for (var title : titles) {
            var i = 1;
            var matcher = timePattern.matcher(splitted[i]);
            var times = new ArrayList<LocalTime>();
            while (matcher.find())
                times.add(LocalTime.parse(matcher.group()));
            schedule.schedules.put(title, times);
            i+=1;
        }
        return schedule;
    }

    public String retResult(String Station) {
        if(!GetStationsList().contains(Station)){
            for(var i = 0; i < GetStationsList().size(); i++)
                System.out.println(GetStationsList().toArray()[i]);
            System.out.println(Station);
            return "Ошибка. Такой станции не существует.";
        }
        var station = fullScheduleForStation(Station);
        var dayOfWeekNow = LocalDateTime.now().getDayOfWeek();
        var timeNow = LocalTime.of(15,8);

        if (!(dayOfWeekNow == DayOfWeek.SATURDAY || dayOfWeekNow == DayOfWeek.SUNDAY)) {
            var north = GetTime(timeNow, station.schedules.get("Рабочие дни в сторону станции \"Проспект космонавтов\" "));
            var south = GetTime(timeNow, station.schedules.get("Рабочие дни в сторону станции \"Ботаническая\" "));
            return north + " " + south;
        }
        var north = GetTime(timeNow, station.schedules.get("Выходные дни в сторону станции \"Проспект космонавтов\" "));
        var south = GetTime(timeNow, station.schedules.get("Выходные дни в сторону станции \"Ботаническая\" "));
        return north + " " + south;
    }

    private LocalTime GetTime(LocalTime now, ArrayList<LocalTime> time) {
        System.out.println(time.size());
        System.out.println(now);
        System.out.println("qweqweqweqweqweqwe");
        System.out.println();
        for (LocalTime i : time
        ) {
            System.out.println(i);
            if (now.compareTo(i) < 0){
                System.out.println("PASSED");
                return i;
            }
        }
        return LocalTime.of(0, 0);
    }
}