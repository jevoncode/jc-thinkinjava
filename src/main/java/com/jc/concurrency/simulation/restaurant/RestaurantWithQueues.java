package com.jc.concurrency.simulation.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.jc.enumerated.menu.Course;
import com.jc.enumerated.menu.Food;

/**
 * 饭店仿真，使用队列来设计并发模型，从而专注于客户、服务员、厨师的设计
 * 
 * 运用了之前学的枚举，让食物获取更加漂亮
 * @author jevoncode
 *
 */
//This is given to the waiter, who gives it to the chef:
class Order { // (A data-transfer object)
	private static int counter = 0;
	private final int id = counter++;
	private final Customer customer;
	private final WaitPerson waitPerson;
	private final Food food;

	public Order(Customer cust, WaitPerson wp, Food f) {
		customer = cust;
		waitPerson = wp;
		food = f;
	}

	public Food item() {
		return food;
	}

	public Customer getCustomer() {
		return customer;
	}

	public WaitPerson getWaitPerson() {
		return waitPerson;
	}

	public String toString() {
		return "Order: " + id + " item: " + food + " for: " + customer + " served by: " + waitPerson;
	}
}

// This is what comes back from the chef:
class Plate {
	private final Order order;
	private final Food food;

	public Plate(Order ord, Food f) {
		order = ord;
		food = f;
	}

	public Order getOrder() {
		return order;
	}

	public Food getFood() {
		return food;
	}

	public String toString() {
		return food.toString();
	}
}

class Customer implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final WaitPerson waitPerson;
	// Only one course at a time can be received:
	private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<Plate>(); //只能保存一个元素

	public Customer(WaitPerson w) {
		waitPerson = w;
	}

	public void deliver(Plate p) throws InterruptedException {
		// Only blocks if customer is still
		// eating the previous course:
		placeSetting.put(p);
	}

	public void run() {
		for (Course course : Course.values()) {
			Food food = course.randomSelection();
			try {
				waitPerson.placeOrder(this, food);
				// Blocks until course has been delivered:
				System.out.println(this + "eating " + placeSetting.take());
			} catch (InterruptedException e) {
				System.out.println(this + "waiting for " + course + " interrupted");
				break;
			}
		}
		System.out.println(this + "finished meal, leaving");
	}

	public String toString() {
		return "Customer " + id + " ";
	}
}

class WaitPerson implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	private AtomicInteger receiveOrders = new AtomicInteger();
	private AtomicInteger delivedOrders = new AtomicInteger();
	BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();

	public WaitPerson(Restaurant rest) {
		restaurant = rest;
	}

	public void placeOrder(Customer cust, Food food) {
		try {
			// Shouldn’t actually block because this is
			// a LinkedBlockingQueue with no size limit:
			receiveOrders.incrementAndGet();
			restaurant.orders.put(new Order(cust, this, food));
			System.out.println(this+" I had receive "+receiveOrders.get()+" orders");
		} catch (InterruptedException e) {
			System.out.println(this + " placeOrder interrupted");
		}
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until a course is ready
				Plate plate = filledOrders.take();
				System.out.println(this + "received " + plate + " delivering to " + plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
				delivedOrders.incrementAndGet();
				System.out.println(this+" I had delived "+delivedOrders.get()+" food");
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}

	public String toString() {
		return "WaitPerson " + id + " ";
	}
}

class Chef implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	private static Random rand = new Random(47);

	public Chef(Restaurant rest) {
		restaurant = rest;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until an order appears:
				Order order = restaurant.orders.take();
				Food requestedItem = order.item();
				// Time to prepare order:
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				Plate plate = new Plate(order, requestedItem);
				order.getWaitPerson().filledOrders.put(plate);
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " off duty");
	}

	public String toString() {
		return "Chef " + id + " ";
	}
}

class Restaurant implements Runnable {
	private List<WaitPerson> waitPersons = new ArrayList<WaitPerson>();
	private List<Chef> chefs = new ArrayList<Chef>();
	private ExecutorService exec;
	private static Random rand = new Random(47);
	BlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();

	public Restaurant(ExecutorService e, int nWaitPersons, int nChefs) {
		exec = e;
		for (int i = 0; i < nWaitPersons; i++) {
			WaitPerson waitPerson = new WaitPerson(this);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
		}
		for (int i = 0; i < nChefs; i++) {
			Chef chef = new Chef(this);
			chefs.add(chef);
			exec.execute(chef);
		}
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// A new customer arrives; assign a WaitPerson:
				WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
				Customer c = new Customer(wp);
				exec.execute(c);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Restaurant interrupted");
		}
		System.out.println("Restaurant closing");
	}
}

public class RestaurantWithQueues {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		Restaurant restaurant = new Restaurant(exec, 5, 2);
		exec.execute(restaurant);
		if (args.length > 0) // Optional argument
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
		else {
			System.out.println("Press ‘Enter’ to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
