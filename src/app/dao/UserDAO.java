package app.dao;

import app.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO implements daoInterface<User>{
    @Override
    public int addData(User data) {
        return 0;
    }

    @Override
    public int delData(User data) {
        return 0;
    }

    @Override
    public int updateData(User data) {
        return 0;
    }

    @Override
    public ObservableList<User> showData() {
        ObservableList<User> uList = FXCollections.observableArrayList();

        return null;
    }
}
