package gui;

public class LauncherGui {

	public static void main(String[] args) {
		Model model = new Model();
		MainFrame view = new MainFrame();
		Controller controller = new Controller();
		
		view.initialise(controller, model);
		controller.initialise(view, model);
	}
}