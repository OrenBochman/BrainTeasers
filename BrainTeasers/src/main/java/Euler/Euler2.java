package Euler;

public class Euler2 {
	//sum of even fibs less than 4000000
	public static void main(String[] args) {
		long MAX=4000000;
		long a=1;
		long b=2;
		long total = 2;
		long sum = 0;
		do {
			sum = a + b;
			a = b;
			b = sum;
			if(sum%2==0) 
				total += sum;
			
			System.out.println("fib: "+ b+ " total: " + total  );

		}while(a+b < MAX);
		System.out.println(total);
	}

}
