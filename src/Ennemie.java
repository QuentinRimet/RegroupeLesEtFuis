import java.util.Timer;
import java.util.TimerTask;

public class Ennemie extends Personnage{
	
	public Ennemie(int x,int y,Carte c){
		super(x,y,c);
		
	}
	
	//permet de savoir si la direction vers ou se dirige l'ennemie est accessible
	public boolean etreAccessible(int x,int y){
		for(int i=0;i<this.getC().getMur().size();i++){
			if(!((Mur)this.getC().getMur().get(i)).etreAccessible(this, x, y))
				return false;

		}
		return true;
	}
	//permet de savoir si 2 ennemie sont rassembler
	public boolean rassembler(Ennemie e){
		if(this.getPosX()==e.getPosX()&& this.getPosY()==e.getPosY())
			return true;
		return false;
	}
	//permet de savoir si l'ennemie a tuer le hero
	public boolean tueHero(Hero e){
		if(this.getPosX()==e.getPosX()&& this.getPosY()==e.getPosY())
			return true;
		return false;
	}
	
	//permet a l'ennemie de s'approcher du hero et de se bloquer si il y a un obstacle
	public void rattraper(Hero h){
		if (this.getPosX()<h.getPosX() && etreAccessible(10,0)){
			if(this.getPosY()%10==0 || etreAccessible(10,10))
			this.setPosX(this.getPosX()+5);
		}
		else
			if (this.getPosX()>h.getPosX() && etreAccessible(-5,0)){
				if(this.getPosX()%10==0 || etreAccessible(-5,5))
				this.setPosX(this.getPosX()-5);
			}
		if (this.getPosY()>h.getPosY()&& etreAccessible(0,-5)){
			if(this.getPosX()%10==0 || etreAccessible(5,-5))
			this.setPosY(this.getPosY()-5);
		}
		else
			if(this.getPosY()<h.getPosY()&& etreAccessible(0,10) ){
				if(this.getPosX()%10==0 || etreAccessible(10,10))
				this.setPosY(this.getPosY()+5);
			}

	}
}

