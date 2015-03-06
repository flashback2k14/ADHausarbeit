package gui;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {
	private ArrayList<int[]> blubbBlubb;

	public Model() {
		this.blubbBlubb = new ArrayList<>();
	}

	public ArrayList<int[]> getBlubbBlubb() {
		return blubbBlubb;
	}

	public void setBlubbBlubb(ArrayList<int[]> blubbBlubb) {
		this.blubbBlubb = blubbBlubb;
	}

	public String printArrayList() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < blubbBlubb.size(); i++) {
			sb.append(Arrays.toString(blubbBlubb.get(i)) + "\n");
		}

		return sb.toString();
	}
}