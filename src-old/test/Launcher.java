package test;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import utilities.Util;

public class Launcher {

	public static void main(String[] args) {
		String path = "src/Daten12B.txt";
		ArrayList<int[]> blubbBlubb = Util.readFile(path);
		
		if (blubbBlubb != null) {
			for (int i = 0; i < blubbBlubb.size(); i++) {
			     System.out.println(Arrays.toString(blubbBlubb.get(i)));
			}
			JOptionPane.showMessageDialog(null, "Reading File completed", "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Something went wrong! Please retry", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}