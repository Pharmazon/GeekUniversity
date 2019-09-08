/*******************************************************************************
 * Java Core 2.
 * Homework 2. Java+mySQL CRUDe.
 * Class: Message
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

package ru.shcheglov.Homework2.utils;

public class Messages {

    public static final String PROGRESS_ADDING_ENTRIES = "Добавление записей в БД...Пожалуйста, подождите...";

    public static final String ENTRIES_ADDED = "Все записи успешно добавлены!";

    public static final String BYE_BYE = "Спасибо за использование! Ждем Вас снова. =)";

    public static final String COMMAND_LINE = "Команда>> ";

    public static final String GOODS_NOT_EXIST = "Такого товара нет.";

    public static final String UNKNOWN_COMMAND = "Неизвестная команда. Повторите ввод.";

    public static final String WELCOME = "Добро пожаловать!\n" +
            "Вам доступны следующие команды: \n" +
            "- '" + ConsoleCommands.EXIT + "' для выхода из приложения.\n" +
            "- '" + ConsoleCommands.PRICE + " товар1' для получения цены на товар1.\n" +
            "- '" + ConsoleCommands.GOODS + " товар1' для получения записи из БД на товар1.\n" +
            "- '" + ConsoleCommands.CHANGE_PRICE + " товар1 123' для изменения цены на товар1 на 123.0.\n" +
            "- '" + ConsoleCommands.GOODS_BY_PRICE + " 100 600' для выборки всех товаров " +
                "в ценовом диапазоне от 100.0 до 600.0.\n" +
            "- '" + ConsoleCommands.DELETE_ENTRY+ " товар1' для удаления записи из БД.";
}