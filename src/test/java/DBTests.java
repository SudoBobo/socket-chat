import chat.server.store.ConnectionPool;
import chat.server.store.UserStore;
import chat.server.store.impls.UserStoreImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class DBTests {

    private UserStore userStore;
    @BeforeClass
    public void setUpClass() throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool(1);
        userStore = new UserStoreImpl(connectionPool);
    }
    @Test
    public void userCreationTest(){
    }

    @AfterClass
    public void tearDown(){
        // освобождение ресурсов

    }

}
