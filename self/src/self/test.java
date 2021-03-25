package self;

class test {

	public static void main(String[] args) {
		int range = 30;
		for (int i = 0; i < range; i++) {
			System.out.print("1번마 : ");
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
	}
}