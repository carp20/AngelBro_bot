package org.example;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {

    Random random = new Random();

    @Override
    public String getBotUsername() {
        return "AngelBro_bot";
    }

    @Override
    public String getBotToken() {
        return "7287242445:AAHzH1ch8T8DEETQQqr0auL7iMKXSbuh0rM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        long id = user.getId();
        saveGson(null);
        saveGson("Пользователь " + id + " отправил сообщение " + msg.getText());


        if (msg.isCommand()) {
            if(msg.getText().equals("/start")) {
                sendText(id, "Привет, я - AngelBro_bot. Я создан для того, что бы в будущем можно было играть в " +
                        "покер онлайн. Реализация самой простой игры в покер ожидается на версии 0.3(Pre-alfa)." +
                        "Настоящая версия бота: 0.2.2");
            }
            else if(msg.getText().equals("/help")) {
                sendText(id, "В данный момент эта функция недоступна. Причина: отсутствие информации," +
                        " которую можно было бы выдавать при использовании данной команды.\n" +
                        "Для получения основной информации о боте, используйте /start");
            }
            else if(msg.getText().equals("/play")) {

                if(id == 7519531395L) {
                    PokerGame pokerGame = new PokerGame();
                    pokerGame.startGame();
                    sendText(id, "Игра началась! Общие карты: " + pokerGame.getCommunityCards() + ". " +
                            "\nВаши карты: " + pokerGame.getYourCards() + ". " +
                            "\nКарты бота: " + pokerGame.getBotCards() + ". ");
                    saveGson("Общие карты: " + pokerGame.getCommunityCards() + ". ");
                    saveGson("Карты пользователя " + id + ": " + pokerGame.getYourCards() + ". ");
                    saveGson("Карты бота: " + pokerGame.getBotCards() + ". ");
                }

                else {
                    sendText(id, "У Вас нету прав на использование этой команды!");
                }
            }
            else if(msg.getText().equals("/getId")){
                sendText(id, String.valueOf(id));
            }
            else{
                sendText(id,"Неопознанная команда");
            }
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder().chatId(who.toString()).text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void copyMessage(Long who, Integer msgId) {
        CopyMessage cm = CopyMessage.builder().fromChatId(who.toString()).chatId(who.toString()).messageId(msgId).build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRank(int randomRank) {
        String i = null;
        if (randomRank <= 10) {
            i = Integer.toString(randomRank);
        }
        else if (randomRank == 11) {
            i = "валет";
            }
        else if (randomRank == 12) {
            i = "дама";
            }
        else if (randomRank == 13) {
            i = "король";
            }
        else if (randomRank == 14) {
            i = "туз";
            }
        else {
            return null;
        }

        return i;
    }

    public void saveGson(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        String filePath = "log.json";

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append("\n");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

}
