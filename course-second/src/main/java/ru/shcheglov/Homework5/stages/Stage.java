/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5.stages;

import ru.shcheglov.Homework5.entity.Car;

public abstract class Stage {

    protected int length;

    protected String description;

    public abstract void go(Car c);
}