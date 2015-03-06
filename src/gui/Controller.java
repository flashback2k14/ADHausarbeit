package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import utilities.Util;

public class Controller implements ActionListener {
	private MainFrame view;
	private Model model;

	public MainFrame getView() {
		return view;
	}

	public void setView(MainFrame view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void initialise(MainFrame view, Model model) {
		setView(view);
		setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case ActionConstants.NEW:
			getView().resetAreas();
			break;

		case ActionConstants.OPEN:
			JFileChooser chooser = new JFileChooser();
			int rueckgabeWert = chooser.showOpenDialog(null);
			if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				getModel().setBlubbBlubb(Util.readFile(path));
				getView().setDataToInputArea();
			}
			break;

		case ActionConstants.CLOSE:
			if (JOptionPane.showConfirmDialog(getView(),
					"Are you sure to close the program?", "Closing",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
			break;

		case ActionConstants.NEXTSTEP:
			if (!getModel().getBlubbBlubb().isEmpty()) {
				getView().setDataToOutputArea();
			} else {
				JOptionPane.showMessageDialog(null, "No Data to show!",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
			break;

		default:
			break;
		}
	}
}
