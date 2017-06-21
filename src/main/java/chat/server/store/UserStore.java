package chat.server.store;


import chat.User;
import chat.server.store.impls.UserStoreImpl;

import java.util.List;

public interface UserStore {
    /**
     * Добавить пользователя в хранилище
     * Вернуть его же
     */
    User addUser(User user);

    /**
     * Обновить информацию о пользователе
     */
    User updateUser(User user);

    /**
     *
     * Получить пользователя по логину/паролю
     * return null if user not found
     */
    User getUser(String login, String pass) throws UserStoreImpl.NoSuchUserException;

    /**
     *
     * Получить пользователя по id, например запрос информации/профиля
     * return null if user not found
     */
    User getUserById(Long id);

    List<User> getUsersInChat(Integer chatId);
}
