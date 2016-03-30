package ru.simflex.ex;

import ru.simflex.ex.dao.implementation.TariffDaoImplementation;
import ru.simflex.ex.dao.implementation.UserDaoImplementation;
import ru.simflex.ex.exceptions.TariffDaoException;
import ru.simflex.ex.exceptions.UserDaoException;

/**
 * Created by ex on 28.03.2016.
 */
public class MainTest {

    public static void main(String[] args) throws UserDaoException, TariffDaoException {

        String str = "A.,2131A==-1 sAsc";
        String newStr = str.replaceAll("[^A-z0-9]", "");
        System.out.println(newStr);
    }
}
