
public class Mur {
	private int posX;
	private int posY;
	// on creer un nouveau de taille 50,50 au coordonnees choisit 
	public Mur(int x,int y){
		posX=x;
		posY=y;
	}
	//empeche les personnages de passer à travers le mur
	public boolean etreAccessible(Personnage h,int x,int y){
	if(h.getPosX()+x<posX+50 && h.getPosX()+x>=posX && h.getPosY()+y<posY+50 && h.getPosY()+y>=posY )
		return false;
	return true;
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
}
