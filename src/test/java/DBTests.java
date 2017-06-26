import chat.User;
import chat.server.store.ConnectionPool;
import chat.server.store.MessageStore;
import chat.server.store.UserStore;
import chat.server.store.impls.MessageStoreImpl;
import chat.server.store.impls.UserStoreImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class DBTests {

    private static UserStore userStore;
    private static MessageStore messageStore;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool(1);
        userStore = new UserStoreImpl(connectionPool);
        messageStore = new MessageStoreImpl(connectionPool);

    }
    @Test
    public void userTest(){
//        User bobo = new User("bobo", "bobo", 1);
//
//        User boboFromDb = null;
//        try {
//            boboFromDb = userStore.getUser("bobo", "bobo");
//        } catch (UserStoreImpl.NoSuchUserException e) {
//            e.printStackTrace();
//        }
//
//        assertEquals(bobo, boboFromDb);
//
//        boboFromDb = userStore.getUserById(1L);
//        assertEquals(bobo, boboFromDb);

    }

    @Test
    public void usersInChatTest(){
        User bobo = new User("bobo", "bobo", 1);
        User dodo = new User("dodo", "dodo", 2);

//        List<User> users = userStore.getUsersInChat();

    }

    @Test
    public  void addChatTest(){
//        ArrayList<Long> usersId = new ArrayList<>();
//        usersId.add(1L);
//        usersId.add(2L);
//            messageStore.addChat(usersId, "chat33222");
    }
    @AfterClass
    public static void tearDown(){
        // освобождение ресурсов

    }

}
