import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame f=new JFrame();


		Carte c=new Carte();
		f.setContentPane(c);
		f.setVisible(true);
		f.setSize(488, 490);
		f.setResizable(false);
		f.setTitle("RegroupeLesEtFuis");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("start");

	}


}
