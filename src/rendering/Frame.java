package rendering;

import java.awt.Toolkit;
import javax.swing.JFrame;
import rendering.Panel;

public class Frame extends JFrame {
	public Frame() {
		this.add(new Panel());
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
