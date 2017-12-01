
public class Personnage {
	private int posX;
	private int posY;
	private Carte c;
	//un personnage est un ennemie ou un hero,possede des coordonnées et leur carte actuel
	public Personnage(int x,int y,Carte c){
		posX=x;
		posY=y;
		this.c=c;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public Carte getC() {
		return c;
	}
	public void setC(Carte c) {
		this.c = c;
	}
}
