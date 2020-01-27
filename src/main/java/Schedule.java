import java.io.*;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Schedule {
    HashMap<String, ArrayList<LocalTime>> schedules = new HashMap<>();
//    private static final String mainAdress = "http://metro-ektb.ru/podrobnye-grafiki-po-stanciyam/";
//    private Map<String,String> links = new HashMap<String,String>();
//    private Map<String,ArrayList<ArrayList<LocalTime>>> schedules = new HashMap<String,ArrayList<ArrayList<LocalTime>>>();
//
//    public Map<String,ArrayList<ArrayList<LocalTime>>> uploadSchedule(){
//        try{
//            createDictStations();
//            fillSchedules();
//        }
//        catch (Exception e){
//            System.out.println(e.getCause());
//            System.out.println(e.toString());
//        }
//        return schedules;
//    }
//    private void createDictStations() throws IOException {
//        Document html = Jsoup.connect(mainAdress).get();
//        Elements doc = html.body().getElementsByClass("detail_schedule");
//        for (Element element:doc.select("a")) {
//            links.put(element.attr("href"), element.text());
//        }
//    }
//    private void fillSchedules() throws IOException, ParseException {
//        for(String url : links.keySet()){
//            var list = new ArrayList<ArrayList<LocalTime>>();
//            Document html = Jsoup.connect(url).get();
//            Elements doc = html.body().getElementsByClass("uss_catalog_content");
//            try(FileWriter writer = new FileWriter(links.get(url)+ ".txt", false))
//            {
//                writer.write(doc.toString());
//            }
//            catch (Exception e){
//                System.out.println(links.get(url));
//                System.out.println(doc.toString());
//            }
//            for (Element element:doc.select("li")){
//                var q = new ArrayList<LocalTime>();
//                for (var i: element.text().replaceAll("[;.]", "").split(" ")
//                ) {
//                    q.add(LocalTime.parse(i));
//                }
//                list.add(q);
//            }
//            schedules.put(links.get(url),list);
//        }
}