package Euler;

public class Euler4 {

	public static void main(String[] args) {
	
		long res = 0;
		for (long i = 111; i <= 999; i++) {
			for (long j = 111; j <= 999; j++) {
				long test = i*j;
				if(isPalindronic(test)&& test>res)
					res=test;
			}
		}
		System.out.println(res);
		
	}

	public static boolean isPalindronic(long inp) {
		String front= ""+inp;
		String back=new StringBuilder(front).reverse().toString();
		return front.equals(back);
	}
}
