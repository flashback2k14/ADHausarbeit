import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Util {
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<Integer[]> readFile(String path) {
		File file = new File(path);
		try {
			ArrayList<Integer[]> netzwerk = new ArrayList<>();
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try {
				String line = bufferedReader.readLine();
				while (line != null) {				
					netzwerk.add(cutGraphDataIntoPieces(line));					
					line = bufferedReader.readLine();
				}
				return netzwerk;
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		} catch (IOException ioe) {
			System.out.println("Error: " + ioe.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param line
	 * @return
	 */
	private static Integer[] cutGraphDataIntoPieces(String line) {
		Integer[] linePieces = new Integer[4];
		String[] tmp = line.split("\t");
		
		try {
			for(int i = 0; i < tmp.length; i++) {
				linePieces[i] = Integer.parseInt(tmp[i]);
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Error: " + nfe.getMessage());
		}
			
		return linePieces;
	}
}