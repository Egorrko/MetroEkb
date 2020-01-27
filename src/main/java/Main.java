import com.google.inject.internal.cglib.core.$Local;

import javax.swing.text.html.parser.TagElement;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {
    ScheduleParser scheduleParser = new ScheduleParser();
    public static void main(String[] args) {
        TelegramAPI.init();
    }

}
