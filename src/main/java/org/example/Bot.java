package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.example.PokerCard.club;

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
                if(id==1966262220 || id==7519531395L || id==5809236702L) {
                    PokerCard[] pokerCard = PokerCard.values();
                    String pokerCards = Arrays.toString(pokerCard);

                    int i = random.nextInt(6, 15);
                    System.out.println("Создано случайное число i " + i);
                    int iI = random.nextInt(0, 4);
                    System.out.println("Создано случайное число iI " + iI);

                    PokerGame identify = new PokerGame(pokerCard[iI], i);
                    System.out.println("Создан экземпляр класса PokerGame");
                    if (identify.isClub) {
                        sendText(id, "Поздравляем, тебе выпала карта треф " + i);
                        System.out.println("Прошла проверка isClub");
                    }
                    else if (identify.isDiamond) {
                        sendText(id, "Поздравляем, тебе выпала карта бубен " + i);
                        System.out.println("Прошла проверка isDiamond");
                    }
                    else if (identify.isHeart) {
                        sendText(id, "Поздравляем, тебе выпала карта червь " + i);
                        System.out.println("Прошла проверка isHeart");
                    }
                    else if (identify.isSpades) {
                        sendText(id, "Поздравляем, тебе выпала карта пики " + i);
                        System.out.println("Прошла проверка isSpades");
                    }
                    else {
                        sendText(id, "Произошла какая-то ошибка! Повторите попытку позже!");
                        System.out.println("Прошла ошибка");
                    }
                }
                else {
                    sendText(id,"У Вас нет прав использовать эту команду!");
                    System.out.println("Пользователь " + id + " попытался использовать команду /play, но не смог (не тот id)");
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
}
