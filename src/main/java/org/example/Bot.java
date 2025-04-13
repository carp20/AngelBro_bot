package org.example;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {

    ArrayList<PokerGame> pokerGames = new ArrayList<>();

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
            if(msg.getText().equals("/start")) {
                sendText(id, "Привет, я - AngelBro_bot. Я создан для того, что бы в будущем можно было играть в " +
                        "покер онлайн. Реализация самой простой игры в покер ожидается на версии 0.3(Pre-alfa)." +
                        "Настоящая версия бота: 0.2.1");
            }
            else if(msg.getText().equals("/help")) {
                sendText(id, "В данный момент эта функция недоступна. Причина: отсутствие информации," +
                        " которую можно было бы выдавать при использовании данной команды.\n" +
                        "Для получения основной информации о боте, используйте /start");
            }
            if(msg.getText().equals("/play")) {
                play(id);
            }
            else{
                    System.out.println("Неопознанная команда");
            }
            pokerGames = new ArrayList<>();
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
            String text;
            PokerCard[] pokerCard = PokerCard.values();
            String pokerCards = Arrays.toString(pokerCard);


            for (int i = 0; i <= 2; i++) {
                int randomRank = random.nextInt(6, 15);
                System.out.println("1");
                int randomSuit = random.nextInt(0, 4);
                String stringRank = getRank(randomRank);
                System.out.println("2");

                PokerGame identify = new PokerGame(pokerCard[randomSuit], stringRank);
                System.out.println("3");
                if (identify.isClub()) {
                    System.out.println("4");
                    sendText(id, "Поздравляем, тебе выпала карта треф " + stringRank);
                    text = "Пользователю " + id + " выпала карта треф " + stringRank;
                    System.out.println(text);
                    saveGson(text);
                } else if (identify.isDiamond()) {
                    System.out.println("4");
                    sendText(id, "Поздравляем, тебе выпала карта бубен " + stringRank);
                    text = "Пользователю " + id + " выпала карта бубен " + stringRank;
                    System.out.println(text);
                    saveGson(text);
                } else if (identify.isHeart()) {
                    System.out.println("4");
                    sendText(id, "Поздравляем, тебе выпала карта червь " + stringRank);
                    text = "Пользователю " + id + " выпала карта червь " + stringRank;
                    System.out.println(text);
                    saveGson(text);
                } else if (identify.isSpades()) {
                    System.out.println("4");
                    sendText(id, "Поздравляем, тебе выпала карта пики " + stringRank);
                    text = "Пользователю " + id + " выпала карта пики " + stringRank;
                    System.out.println(text);
                    saveGson(text);
                } else {
                    System.out.println("4");
                    sendText(id, "Произошла какая-то ошибка! Повторите попытку позже!");
                    text = "Прошла ошибка  пользователем " + id;
                    System.out.println(text);
                    saveGson(text);
                }
                System.out.println("5");
                pokerGames.add(identify);
                System.out.println("i: " + i);

            }
            combination(pokerGames.get(0), pokerGames.get(1), id);
            System.out.println("6");
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

    public void combination(PokerGame identify, PokerGame identify2, long id){
        String a = identify.getRank();
        String b = identify2.getRank();
        String txt = "Пользователю " + id + " выпала пара карт " + b;
        if(a.equals(b)){
            sendText(id,"Поздравляем! Вам выпала пара " + a);
            System.out.println(txt);
        }
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
