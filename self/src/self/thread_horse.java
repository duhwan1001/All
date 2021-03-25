package self;

public class thread_horse {

	public static void main(String[] args) {
		
		Horse[] horse = new Horse[] {
			new Horse("1번말"),
			new Horse("2번말"),
			new Horse("3번말")
		};
		
		for(int i=0; i<horse.length; i++) {
			horse[i].start();
		}
		
		for(Horse mal : horse) {
			try {
				mal.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	
	private String name;
	int rank;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Horse(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}



	@Override
	public void run() {
		int range = 30;

		for (int i = 0; i < range; i++) {
			System.out.print(name + " : ");
			for (int j = 0; j < i; j++) {
				System.out.print("-");
			}
			System.out.print(">");

			for (int j = range-1; j > i; j--) {
				System.out.print("-");
			}

			System.out.println();
			
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " 도착 ");
		
	}

	@Override
	public int compareTo(Horse o) {
		return 0;
	}
}
