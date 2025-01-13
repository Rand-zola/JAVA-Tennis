package tennis;

import java.awt.*;

public class Joueur {
	private int x, y;         
    private final int WIDTH = 20, HEIGHT = 100; 
    private final int SPEED = 10; 

    // Constructeur pour initialiser la raquette à une position donnée
    public Joueur(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    // Déplacer la raquette vers le haut
    public void moveUp() {
        if (y > 0) {  
            y -= SPEED; 
        }
    }

    // Déplacer la raquette vers le bas
    public void moveDown() {
        if (y < 460 - HEIGHT) {  
            y += SPEED; 
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);  
        g.fillRect(x, y, WIDTH, HEIGHT);  
    }
    // Retourner le rectangle de la raquette pour la gestion des collisions
    public Rectangle bord() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
