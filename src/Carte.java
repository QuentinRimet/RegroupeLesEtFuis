import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Carte extends JPanel{
	private ArrayList<Mur> mur=new ArrayList<Mur>();
	private Hero h;
	private Ennemie e1;
	private Ennemie e2;
	private Ennemie e3;
	private Ennemie e4;
	private boolean perdu=false;
	private boolean gagner=false;
	//on initialise la carte
	public Carte(){
		this.setBackground(Color.LIGHT_GRAY);
		//on ajoute 25 murs de facon alleatoire dans la carte mais en laissant un chemin accessible de la zone de depart depuis tous les cotes 
		for(int i=0;i<25;i++){
			mur.add(new Mur(10*(int)(Math.random()*50),10*(int)(Math.random()*50)));
			while((((Mur)mur.get(i)).getPosX()<40) 
					|| (((Mur)mur.get(i)).getPosX()>390)
					|| (((Mur)mur.get(i)).getPosY()>370)
					|| (((Mur)mur.get(i)).getPosY()<40)
					|| (((Mur)mur.get(i)).getPosY()>=170 &&((Mur)mur.get(i)).getPosY()<240)) 
				mur.set(i,new Mur(10*(int)(Math.random()*50),10*(int)(Math.random()*50)));
		}
		//on rajoute les murs exterieur pour fermer la carte
		for(int i=0;i<20;i++){
			mur.add(new Mur(i*50,-40));
			mur.add(new Mur(i*50,450));
			mur.add(new Mur(-40,i*50));
			mur.add(new Mur(470,i*50));
		}


		//on rajoute les ennemies et le hero
		h=new Hero(240,220,this);
		e1=new Ennemie(450,430,this);
		e2=new Ennemie(450,20,this);
		e3=new Ennemie(20,430,this);
		e4=new Ennemie(20,20,this);
		this.jeu();

		this.addKeyListener(h);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	//permet de reinitialiser le jeu
	public void reinitialise(){

		//on reinitialise les 25 murs de facon alleatoire dans la carte mais en laissant un chemin accessible de la zone de depart depuis tous les cotes 
		for(int i=0;i<25;i++){
			mur.set(i,new Mur(10*(int)(Math.random()*50),10*(int)(Math.random()*50)));
			while((((Mur)mur.get(i)).getPosX()<40) 
					|| (((Mur)mur.get(i)).getPosX()>390)
					|| (((Mur)mur.get(i)).getPosY()>370)
					|| (((Mur)mur.get(i)).getPosY()<40)
					|| (((Mur)mur.get(i)).getPosY()>=170 &&((Mur)mur.get(i)).getPosY()<240)) 

				mur.set(i,new Mur(10*(int)(Math.random()*50),10*(int)(Math.random()*50)));
		}
		
		//on reinitialise les ennemies et le hero
		this.removeKeyListener(h);
		e1=new Ennemie(450,430,this);
		e2=new Ennemie(450,20,this);
		e3=new Ennemie(20,430,this);
		e4=new Ennemie(20,20,this);
		h=new Hero(240,220,this);
		if(perdu || gagner){
			perdu=false;
			gagner=false;
			this.jeu();
		}
		this.addKeyListener(h);
	}

	//methode qui va boucler afin de faire avancer le jeu
	public void jeu(){
		//si tous les ennemies sont regrouper et que l'on ai retournais a la zone de depart alors on gagne
		if(h.getPosX()>=230 && h.getPosX()<260 && h.getPosY()<240 && h.getPosY()>=210 && e1.rassembler(e2) && e2.rassembler(e3) && e2.rassembler(e4)){
			gagner=true;
			repaint();
		}
		//si une ennemie nous touche on perd
		else if(e1.tueHero(h)||e2.tueHero(h)||e3.tueHero(h)||e4.tueHero(h)){
			perdu=true;
			repaint();
		}
		//sinon on les personnages se deplace
		else{
			h.deplacer();
			e1.rattraper(h);
			e2.rattraper(h);
			e3.rattraper(h);
			e4.rattraper(h);
			repaint();
			
			//permet de boucler la methode
			Timer time=new Timer();
			time.schedule(new TimerTask(){
				public void run(){
					jeu();
				}
			}, 50)
			;}
	}

	//permet d'affiche la carte
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//on affiche tous les murs en noir
		for(int i=0;i<mur.size();i++)
			g.fillRect(((Mur)mur.get(i)).getPosX(), ((Mur)mur.get(i)).getPosY(), 50, 50);
		//on affiche la zone de depart
		g.setColor(Color.green);
		g.fillRect(230, 210, 30, 30);
		//on affiche le hero
		g.setColor(Color.blue);
		g.fillOval(h.getPosX(), h.getPosY(), 10, 10);
		//on affiche les ennemies
		g.setColor(Color.red);
		g.fillOval(e1.getPosX(), e1.getPosY(), 10, 10);
		g.fillOval(e2.getPosX(), e2.getPosY(), 10, 10);
		g.fillOval(e3.getPosX(), e3.getPosY(), 10, 10);
		g.fillOval(e4.getPosX(), e4.getPosY(), 10, 10);
		//si on gagne
		if(gagner){
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 1, 100));
			g.drawString("Victoire",60,250);
		}
		//si on perd
		if(perdu){
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 1, 100));
			g.drawString("Defaite",60,250);
		}
	}
	public ArrayList<Mur> getMur() {
		return mur;
	}
	public void setMur(ArrayList<Mur> mur) {
		this.mur = mur;
	}
}