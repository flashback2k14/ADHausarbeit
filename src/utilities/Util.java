package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Util {
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<int[]> readFile(String path) {
		File file = new File(path);
		try {
			ArrayList<int[]> blubb = new ArrayList<>();
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try {
				String line = bufferedReader.readLine();
				while (line != null) {				
					blubb.add(cutLineIntoPieces(line));					
					line = bufferedReader.readLine();
				}
				return blubb;
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	/**
	 * 
	 * @param line
	 * @return
	 */
	private static int[] cutLineIntoPieces(String line) {
		int[] linePieces = new int[4];
		String[] tmp = line.split("\t");
		
		try {
			for(int i = 0; i < tmp.length; i++) {
				linePieces[i] = Integer.parseInt(tmp[i]);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, nfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
			
		return linePieces;
	}
}