package tennis;

import java.awt.*;

public class Balle {
    private int x, y;
    private int Vx, Vy;
    private final int RADIUS = 10;  

    // Initialisation de la position de la balle et sa vitesse initiale
    public Balle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.Vx = 4;  
        this.Vy = 4;  
    }

    // Déplacement de la balle
    public void mouvement() {
        x += Vx;  
        y += Vy;  

        if (y <= 50 || y >= 440) {  
            Vy = -Vy;  
        }
    }

    public void collision(Joueur joueur) {
        Rectangle balleRect = new Rectangle(x, y, RADIUS, RADIUS);

        if (balleRect.intersects(joueur.bord())) {
            if (Vx > 0) {  
                if (balleRect.getMaxX() > joueur.bord().getMinX()) {  
                    x = joueur.bord().x - RADIUS;  
                }
            } else if (Vx < 0) {  
                if (balleRect.getMinX() < joueur.bord().getMaxX()) {  
                    x = joueur.bord().x + joueur.bord().width;  
                }
            }
            
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
        this.Vx = 4;  
        this.Vy = 4;  
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
