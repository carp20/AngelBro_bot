package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static java.time.chrono.JapaneseEra.values;
import static org.example.PokerCards.diamond;

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
        var id = user.getId();


        if (msg.isCommand()) {
            if (msg.getText().equals("/start")){
                sendText(id,"Привет, я - AngelBro_bot. Я создан для того, что бы в будущем можно было играть в " +
                        "покер онлайн. Реализация самой простой игры в покер ожидается на версии 0.3(Pre-alfa)." +
                        "Настоящая версия бота: 0.1.5");
            }
            else if (msg.getText().equals("/help")){
                sendText(id,"В данный момент эта функция недоступна. Причина: отсутствие информации," +
                        " которую можно было бы выдавать при использовании данной команды.\n" +
                        "Для получения основной информации о боте, используйте /start");
            }
            else if (msg.getText().equals("/play")) {
                int i = random.nextInt(1, 9) + 5;
                Identify identify = new Identify(diamond, i);
                if(identify.isClub){
                    sendText(id, "Поздравляем, тебе выпала карта треф"+i);
                }
                if(identify.isDiamond){
                    sendText(id, "Поздравляем, тебе выпала карта бубен"+i);
                }
                if(identify.isHeart){
                    sendText(id, "Поздравляем, тебе выпала карта червь"+i);
                }
                if(identify.isSpades){
                    sendText(id, "Поздравляем, тебе выпала карта пики"+i);
                }
            }
            else{
                copyMessage(id, msg.getMessageId());
            }


        }
        System.out.println("Пользователь " + id + " отправил сообщение боту в лс: " + msg.getText());
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
