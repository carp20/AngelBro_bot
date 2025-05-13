package org.example;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Bot extends TelegramLongPollingBot {

    private Map<Long, CommunityPokerGame> games = new HashMap<>();
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    @Override
    public String getBotUsername() {
        return "AngelBro_bot";
    }

    @Override
    public String getBotToken() {
        return "7287242445:AAGzxvATcPDI7fb3CDlJKWLDj9i_tJG7pXc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        long id = user.getId();
        saveGson("~");
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
                            "\nВаши карты: " + pokerGame.getCards(pokerGame.getPlayer()) + ". ");

                    saveGson("Общие карты: " + pokerGame.getCommunityCards() + ". ");
                    saveGson("Карты пользователя " + id + ": " + pokerGame.getCards(pokerGame.getPlayer()) + ". ");
                    saveGson("Карты бота: " + pokerGame.getCards(pokerGame.getBot()) + ". ");

                    sendText(id, "Хотите сделать ставку (если да, то кол-во фишек, иначе введите 0)?");
                    int answer = scanner.nextInt();
                    if(answer!=0){

                    }
                    else{
                        sendText(id, "Игра закончена! Для новой игры введите /play");
                    }
                }

                else {
                    sendText(id, "У Вас нету прав на использование этой команды!");
                }
            }

            else if(msg.getText().equals("/getId")){
                sendText(id, String.valueOf(id));
            }

            else if(msg.getText().equals("/newgame")){
                games.put(id, new CommunityPokerGame());
                sendText(id, "Новая игра создана! Игроки могут присоединяться с помощью команды /join.");
            }

            else if (msg.getText().equals("/join")) {
                CommunityPokerGame game = games.get(id);
                if (game != null) {
                    Player player = new Player(user.getUserName());
                    game.addPlayer(player);
                    sendText(id, player.getName() + " присоединился к игре!");
                } else {
                    sendText(id, "Сначала создайте игру с помощью /newgame.");
                }
            }

            else if (msg.getText().equals("/startgame")) {
                CommunityPokerGame game = games.get(id);
                if (game != null && game.getPlayers().size() >= 2) {
                    game.startGame();
                    sendText(id, "Игра началась! Общие карты: " + game.getCommunityCards());
                } else {
                    sendText(id, "Недостаточно игроков для начала игры.");
                }
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

    public int checkCombination(PokerGame pokerGame){
        int numberCombination = 0;

        int numberHighCard = highCard(pokerGame);


        return numberCombination;
    }

    public int highCard(PokerGame pokerGame){
        int numberHighCard = 0;
        List<Card> cards = pokerGame.getPlayer().getHand();
        cards.addAll(pokerGame.getCommunityCards());
        for(int i = 0; i<cards.size(); i++){
            int b = transform(cards.get(i).getValue()) - 4;
            if(b>numberHighCard){
                numberHighCard = b;
            }
        }
        return numberHighCard;
    }

    public int onePair(PokerGame pokerGame){
        int numberOnePair = 0;
        List<Card> cards = pokerGame.getPlayer().getHand();
        cards.addAll(pokerGame.getCommunityCards());

        return numberOnePair;
    }

    public int transform(String a){
        int b;
        if(a.equals("J")){
            b = 11;
        }
        else if(a.equals("Q")){
            b = 12;
        }
        else if (a.equals("K")) {
            b = 13;
        }
        else if (a.equals("A")) {
            b = 14;
        }
        else{
            b = 1;
        }
        return b;
    }

}
