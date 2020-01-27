import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class TelegramAPI extends TelegramLongPollingBot {
    ScheduleParser scheduleParser = new ScheduleParser();
    public static void init(){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramAPI());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                sendMsg(update.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "metroekb_bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    public void sendMsg (Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        createKeyboard(replyKeyboardMarkup);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(scheduleParser.retResult(message.getText()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public Set<String> set(int max){
        var q = new HashMap<String,String>();
        for(var i = 0; i < max; i++){
            q.put(Integer.toString(i),"w");
        }
        return q.keySet();
    }

    public void createKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup){
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        var Stations = scheduleParser.GetStationsList().toArray();

        List<KeyboardRow> keyboard = new ArrayList<>();
        for(var i = 0; i<Stations.length / 3;i++){
            var kRow = new KeyboardRow();
            kRow.add((String) Stations[i]);
            kRow.add((String) Stations[i + Stations.length / 3]);
            kRow.add((String) Stations[i + Stations.length / 3 * 2]);
            keyboard.add(kRow);
        }

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
