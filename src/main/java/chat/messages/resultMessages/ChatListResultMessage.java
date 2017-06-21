package chat.messages.resultMessages;

import chat.messages.Message;

import java.util.ArrayList;

public class ChatListResultMessage extends Message {
    //TODO как серьёзные люди выбирают структуры данных?

    private ArrayList <Integer> chatIdList;

    public ChatListResultMessage(){}
    public ChatListResultMessage(ArrayList<Integer> chatIdList){
        this.chatIdList = chatIdList;
    }

    public ArrayList<Integer> getChatIdList() {
        return chatIdList;
    }
}
