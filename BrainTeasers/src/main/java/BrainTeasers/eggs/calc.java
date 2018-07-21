package BrainTeasers.eggs;

import javax.swing.JOptionPane;

public class calc {

	public static void main(String[] args) {
		int res=0;
		String strNum2 = JOptionPane.showInputDialog("please enter num"); 
		int j= Integer.parseInt(strNum2);

			for (int i = 0; i < j; i++) {
				res+=j-i;
				System.out.println(i+": sum:" + res);
			}
	}
}
