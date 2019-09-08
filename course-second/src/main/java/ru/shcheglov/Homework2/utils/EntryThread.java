/*******************************************************************************
 * Java Core 2.
 * Homework 2. Java+mySQL CRUDe.
 * Class: EntryThread
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 25, 2018
 ******************************************************************************/

package ru.shcheglov.Homework2.utils;

import ru.shcheglov.Homework2.dao.GoodsDAO;

public class EntryThread extends Thread implements Runnable {

    private GoodsDAO goodsDAO;

    private int from;

    private int to;

    public EntryThread(int from, int to) {
        goodsDAO = new GoodsDAO();
        this.from = from;
        this.to = to;
    }

    public void run() {
        for (int i = from; i <= to; i++) {
            goodsDAO.addEntry(i, "товар" + i, i * 10);
        }
        goodsDAO.disconnect();
        System.out.println("Поток записи " + Thread.currentThread().getName() + " завершен. " +
                "Добавлены записи с " + from + " по " + to + ".");
    }
}
