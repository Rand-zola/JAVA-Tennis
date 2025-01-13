package tennis;

import java.awt.*; 

public class Balle {
	private int x, y;       
	private int Vx, Vy;     
	private final int RADIUS = 10; 
	
	//initialisation de la position de la balle et sa vitesse initiale
    public Balle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.Vx = 3;  
        this.Vy = 3;  
    }
    
    //déplacement de la balle 
    public void mouvement() {
        x += Vx;  
        y += Vy;   

        if (y <= 0 || y >= 450) { 
            Vy = -Vy;  
        }
    }
    
    public void collision(Joueur joueur) {
        if (new Rectangle(x, y, RADIUS, RADIUS).intersects(joueur.bord())) {
            Vx = -Vx; 
        }
    }
    
    // Dessiner la balle dans le graphique
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);  
        g.fillOval(x, y, RADIUS, RADIUS);  
    }
    
    // Réinitialiser la position de la balle
    public void reset(int startX, int startY) {
        this.x = startX;  
        this.y = startY;  
        this.Vx = 3;      
        this.Vy = 3;      
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
