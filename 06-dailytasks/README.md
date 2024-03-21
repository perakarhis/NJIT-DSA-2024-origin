# DailyTasks app

In this task you will use your queue data strucuter into which you read from a file tasks that will be done during one day, nd which will be dequeued and printed on the screen.

The `DailyTasks.java` includes code which uses the queue to present your daily tasks using a Timer. In real life, timer would be much slower, or use actual times (hours:minutes) to schedule the timer, not fixed intervals. But this is just a demonstration of using queues.

Here you will see can see the figure from the queue task again and now you will implement the DailyTasks related detialse.

![Classes in this exercise](classes.png)

First, **copy** `QueueFactory.java` and `QueueImplementation.java` files **from** your previous queue task **to** `src/main/java/oy/tol/tra` folder of this task.

**Implement** the missing code following the comments in the methods. After implementing, run the main either from VS Code (you should see `Run|Debug` just above the `main()` function) or from the command line:

```command
mvn package
java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar
```

See how it works. You should see one new task printed out periodically, until the queue is empty, controlled by the `Timer` and `TimerTask`. If the tests passed, the app should also work.

## Delivery

Deliver your updated repository as instructed in the course.

## Questions or problems?

Participate in the course lectures, and execises and ask for help exercises.

If you have issues building and running the tests, make sure you have the correct JDK installed, environment variables are as they should and Maven is installed.

## About

* Course material for Tietorakenteet ja algoritmit | Data structures and algorithms 2021.
* Study Program for Information Processing Science, University of Oulu.
* Antti Juustila, INTERACT Research Group.
* Modified for NJIT implementation:
* Pertti Karhapää, M3S Research Unit
