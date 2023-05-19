package app.dao;

import java.util.List;

public interface daoInterface<E> {

    int addData(E data);
    int delData(E data);
    int updateData(E data);

    List<E> showData();

}
