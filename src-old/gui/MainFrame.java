package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.TextArea;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Model model;
	private TextArea inputArea;
	private TextArea outputArea;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void initialise(Controller controller, Model model) {
		this.setTitle("Kostenminimaler Fluss");

		setModel(model);
		buildMenu(controller);

		inputArea = new TextArea(100, 20);
		this.add(inputArea, BorderLayout.WEST);

		outputArea = new TextArea();
		this.add(outputArea, BorderLayout.CENTER);

		JButton btnPerformStep = new JButton(ActionConstants.NEXTSTEP);
		btnPerformStep.setActionCommand(ActionConstants.NEXTSTEP);
		btnPerformStep.addActionListener(controller);
		this.add(btnPerformStep, BorderLayout.SOUTH);

		this.setSize(new Dimension(400, 600));
		this.setLocation(new Point(100, 100));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void buildMenu(Controller controller) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem itemNew = new JMenuItem(ActionConstants.NEW);
		JMenuItem itemOpen = new JMenuItem(ActionConstants.OPEN);
		JMenuItem itemClose = new JMenuItem(ActionConstants.CLOSE);

		itemNew.setActionCommand(ActionConstants.NEW);
		itemNew.addActionListener(controller);

		itemOpen.setActionCommand(ActionConstants.OPEN);
		itemOpen.addActionListener(controller);

		itemClose.setActionCommand(ActionConstants.CLOSE);
		itemClose.addActionListener(controller);

		menu.add(itemNew);
		menu.add(itemOpen);
		menu.addSeparator();
		menu.add(itemClose);

		menuBar.add(menu);

		this.setJMenuBar(menuBar);
	}

	public void resetAreas() {
		inputArea.setText("");
		outputArea.setText("");
	}

	public void setDataToInputArea() {
		inputArea.setText(getModel().printArrayList());
	}

	public void setDataToOutputArea() {
		outputArea.setText(outputArea.getText()
				+ Arrays.toString(getModel().getBlubbBlubb()
						.get(new Random().nextInt(getModel().getBlubbBlubb()
								.size()))) + "\n");
	}
}