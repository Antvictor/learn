package antvictor.study.design.srp.v2;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Antvictor
 * @date 2024/1/21
 **/
public class FoodManager {
    private final LinkedList<String> foodList = new LinkedList<>();
    private Lock lock = new ReentrantLock();

    public void addFood(String name) {
        lock.lock();
        try {
            foodList.add(name);
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return foodList.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    public String poll() {
        lock.lock();
        try {
            return foodList.pollFirst();
        } finally {
            lock.unlock();
        }
    }
}
