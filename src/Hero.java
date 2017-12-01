import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Hero extends Personnage implements KeyListener{
	private boolean haut=false;
	private boolean bas=false;
	private boolean gauche=false;
	private boolean droite=false;
	public Hero(int x,int y,Carte c){
		super(x,y,c);
	}
	//permet de savoir si la direction vers ou se dirige le hero est accessible
	public boolean etreAccessible(int x,int y){
		for(int i=0;i<this.getC().getMur().size();i++){
			if(!((Mur)this.getC().getMur().get(i)).etreAccessible(this, x, y))
				return false;

		}
		return true;
	}
	
	//permet au hero de se deplacer sur la carte en fonction de la direction choisit
	public void deplacer(){
		if (droite && etreAccessible(10,0)){
			this.setPosX(this.getPosX()+10);
		}
		else
			if (gauche && etreAccessible(-10,0)){
				this.setPosX(this.getPosX()-10);
			}
		if (haut && etreAccessible(0,-10)){
			this.setPosY(this.getPosY()-10);
		}
		else
			if(bas && etreAccessible(0,10)){
				this.setPosY(this.getPosY()+10);
			}

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
		//permet au hero de se deplacer en appuyant sur les fleches du clavier
		if(e.getKeyCode()==38) haut=true;
		else
			if(e.getKeyCode()==40) bas=true;
			else
				if(e.getKeyCode()==37 )gauche=true;
				else
					if(e.getKeyCode()==39 )droite=true;
					else
						//l'appui sur espace reinitialise la carte
						if(e.getKeyCode()==32 )((Carte)e.getSource()).reinitialise();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//permet au hero d'arreter de se deplacer en relachant les fleches du clavier
		if(e.getKeyCode()==38) haut=false;
		else
			if(e.getKeyCode()==40) bas=false;
			else
				if(e.getKeyCode()==37 )gauche=false;
				else
					if(e.getKeyCode()==39 )droite=false;
		
	}
}
