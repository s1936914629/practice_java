package snake;
import javax.swing.JFrame;

public class Msnake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame =new JFrame();
		frame.setBounds(10, 10, 900, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new Mpanel());
		frame.setVisible(true);

	}

}
