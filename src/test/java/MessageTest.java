import chat.messages.LoginMessage;
import chat.messages.Message;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MessageTest
{
    @Test
    public void msgTest(){
        LoginMessage m1 = new LoginMessage("pep", "pop", 1L, 1L);
        LoginMessage m2 = new LoginMessage("pep", "pop", 1L, 1L);

        Message m3 = (Message) m1;
//        System.out.println(m1.equals(m2));

    }

    @Test
    public void parseTest(){

        String input = "/chat_create 1,2 boboChat";
        String[] tokens = input.split(" ");

        List<Long> usersId = new LinkedList<>();
        String usersIdString = tokens[1];


        String [] usersSeparated = usersIdString.split(",");


        for (String userString : usersSeparated){
            usersId.add(Long.parseLong(userString));
        }

        for (Long id : usersId){
            System.out.println(id);
        }



    }
}
