package ru.shcheglov.Homework2;

/*******************************************************************************
 * Java Core 2.
 * Homework 2. Java+mySQL CRUDe.
 * Class: UserConsole
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

import ru.shcheglov.Homework2.dao.GoodsDAO;
import ru.shcheglov.Homework2.utils.ConsoleCommands;
import ru.shcheglov.Homework2.utils.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserConsole {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Messages.WELCOME);
        GoodsDAO goodsDAO = new GoodsDAO();
        String command = " ";
        String firstPar = " ";
        String secondPar = " ";

        try {
            while (true) {
                System.out.print(Messages.COMMAND_LINE);
                String line = reader.readLine();

                String[] strArray = line.split("\\s");
                if (strArray.length >= 1)
                    command = strArray[0];
                if (strArray.length >= 2)
                    firstPar = strArray[1];
                if (strArray.length >= 3)
                    secondPar = strArray[2];

                if (command.equals(ConsoleCommands.EXIT)) {
                    System.out.println(Messages.BYE_BYE);
                    return;
                }

                if (command.equals(ConsoleCommands.PRICE)) {
                    float cost = goodsDAO.getCostByTitle(firstPar);
                    if (cost > 0) {
                        System.out.println("Цена товара '" + firstPar + "' = " + cost + " руб.");
                    } else {
                        System.out.println(Messages.GOODS_NOT_EXIST);
                    }
                    continue;
                }

                if (command.equals(ConsoleCommands.CHANGE_PRICE)) {
                    float oldcost = goodsDAO.getCostByTitle(firstPar);
                    goodsDAO.changeCost(firstPar, Float.parseFloat(secondPar));
                    System.out.println("Цена товара '" + firstPar + "' успешно изменена с " +
                            oldcost + " руб. на " + secondPar + " руб.");
                    continue;
                }

                if (command.equals(ConsoleCommands.GOODS_BY_PRICE)) {
                    goodsDAO.getRangeOfGoodsByCosts(Float.parseFloat(firstPar), Float.parseFloat(secondPar));
                    continue;
                }

                if (command.equals(ConsoleCommands.DELETE_ENTRY)) {
                    goodsDAO.deleteEntry(firstPar);
                    continue;
                }

                if (command.equals(ConsoleCommands.GOODS)) {
                    goodsDAO.getGoodsByTitle(firstPar);
                    continue;
                }

                System.out.println(Messages.UNKNOWN_COMMAND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                goodsDAO.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
