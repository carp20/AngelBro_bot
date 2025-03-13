package org.example;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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


        if (msg.isCommand()) {
            System.out.println("Пользователь " + id + " отправил сообщение боту в лс: " + msg.getText());
            if (msg.getText().equals("/start")){
                sendText(id,"Привет, я - AngelBro_bot. Я создан для того, что бы в будущем можно было играть в " +
                        "покер онлайн. Реализация самой простой игры в покер ожидается на версии 0.3(Pre-alfa)." +
                        "Настоящая версия бота: 0.2.0");
            }
            else if (msg.getText().equals("/help")){
                sendText(id,"В данный момент эта функция недоступна. Причина: отсутствие информации," +
                        " которую можно было бы выдавать при использовании данной команды.\n" +
                        "Для получения основной информации о боте, используйте /start");
            }
            else if (msg.getText().equals("/play")) {
                play(id);
            }
            else if (msg.getText().equals("/getName")) {
                System.out.println(update.getMessage().getFrom());
            }
            else if (msg.getText().equals("playSolo")) {
                while (true){
                    play(7287242445L);
                }
            }
            else{
                copyMessage(id, msg.getMessageId());
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
    public void play(Long id){
        if(id==1966262220 || id==7519531395L || id==5809236702L) {
            String txt;
            PokerCard[] pokerCard = PokerCard.values();
            String pokerCards = Arrays.toString(pokerCard);
            int randomRank1 = random.nextInt(6, 15);
            int iI1 = random.nextInt(0, 4);
            String i1 = getRank(randomRank1);

            PokerGame identify = new PokerGame(pokerCard[iI1], i1);
            if (identify.isClub) {
                sendText(id, "Поздравляем, тебе выпала карта треф " + i1);
                txt = "Пользователю " + id + " выпала карта треф " + i1;
                System.out.println(txt);
                SaveGson(txt);
            } else if (identify.isDiamond) {
                sendText(id, "Поздравляем, тебе выпала карта бубен " + i1);
                txt = "Пользователю " + id + " выпала карта бубен " + i1;
                System.out.println(txt);
                SaveGson(txt);
            } else if (identify.isHeart) {
                sendText(id, "Поздравляем, тебе выпала карта червь " + i1);
                txt = "Пользователю " + id + " выпала карта червь " + i1;
                System.out.println(txt);
                SaveGson(txt);
            } else if (identify.isSpades) {
                sendText(id, "Поздравляем, тебе выпала карта пики " + i1);
                txt = "Пользователю " + id + " выпала карта пики " + i1;
                System.out.println(txt);
                SaveGson(txt);
            } else {
                sendText(id, "Произошла какая-то ошибка! Повторите попытку позже!");
                txt = "Прошла ошибка  пользователем " + id;
                System.out.println(txt);
                SaveGson(txt);
            }

            int randomRank2 = random.nextInt(6, 15);
            int iI2 = random.nextInt(0, 4);
            String i2 = getRank(randomRank2);

            PokerGame identify2 = new PokerGame(pokerCard[iI2], i2);
            if (identify2.isClub) {
                sendText(id, "Поздравляем, тебе выпала карта треф " + i2);
                txt = "Пользователю " + id + " выпала карта треф " + i2;
                System.out.println(txt);
                SaveGson(txt);
            } else if (identify2.isDiamond) {
                sendText(id, "Поздравляем, тебе выпала карта бубен " + i2);
                txt = "Пользователю " + id + " выпала карта бубен " + i2;
                System.out.println(txt);
                SaveGson(txt);
            } else if (identify2.isHeart) {
                sendText(id, "Поздравляем, тебе выпала карта червь " + i2);
                txt = "Пользователю " + id + " выпала карта червь " + i2;
                System.out.println(txt);
                SaveGson(txt);
            } else if (identify2.isSpades) {
                sendText(id, "Поздравляем, тебе выпала карта пики " + i2);
                txt = "Пользователю " + id + " выпала карта пики " + i2;
                System.out.println(txt);
                SaveGson(txt);
            } else {
                sendText(id, "Произошла какая-то ошибка! Повторите попытку позже!");
                txt = "Прошла ошибка  пользователем " + id;
                System.out.println(txt);
                SaveGson(txt);
            }

            Combination(identify, identify2, id);

        }
        else {
            sendText(id,"У Вас нет прав использовать эту команду!");
            System.out.println("Пользователь " + id + " попытался использовать команду /play, но не смог (не тот id)");
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

    public void Combination(PokerGame identify, PokerGame identify2, long id){
        String a = identify.getRank();
        String b = identify2.getRank();
        String txt = "Пользователю " + id + " выпала пара карт " + b;
        if(a.equals(b)){
            sendText(id,"Поздравляем! Вам выпала пара " + a);
            System.out.println(txt);
        }
    }

    public void SaveGson(Object object){
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
