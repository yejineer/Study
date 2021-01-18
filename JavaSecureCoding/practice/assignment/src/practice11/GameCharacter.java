// 컴퓨터학과 20170964 이예진
package practice11;

import java.util.ArrayList;

public class GameCharacter {
	
	class GameItem {
		String name;
		int type;
		int price;
		
		int getPrice() {
			return price;
		}
		
		public String toString() {
			return "GameItem [name=" + name + ", type=" + type + ", price=" + price + "]\n";
		}
	}
	
	private ArrayList<GameItem> list;
	
	public void add(String name, int type, int price) {
		GameItem item = new GameItem();
		item.name = name;
		item.type = type;
		item.price = price;
		list.add(item);
	}
	
	public void print() {
		int sum = 0;
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i).toString());
			sum += list.get(i).getPrice();
		}
		System.out.println(sum);
	}
	
	GameCharacter() {
		list = new ArrayList<GameItem>();
	}
	
}
