/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplotanque;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 * Jpanel dado que voy a pintar en el Canvas
 * ActionListener: Para poder ejecutar el escenario cada ciertos milisegundos
 */
public class Tablero extends JPanel implements ActionListener{
    private Timer timer ;
    private int x;
    private int y;
    private int secuencia;
    public Tablero(){
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(25, this);
        //Registrar evento del Teclado
        setFocusable(true); //Debe estar en modo Focus para que puede detectar el evento
        addKeyListener(new EventosTeclado()); //Inner class que procesa los eventos del teclado
        this.timer.start(); //Inicio con el escenario
        this.x=2;
        this.y=20;
    }
      
    //Inner class Que captura los eventos del teclado
     private class EventosTeclado extends KeyAdapter {
        //Cuando se suelta una tecla
         @Override
        public void keyReleased(KeyEvent e) {
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_SPACE) {
            System.out.println("VK_SPACE"); //Se  va usar posteriormente 
           }
        }
        //Cuando se presiona una tecla
        @Override
        public void keyPressed(KeyEvent e) {
           
        }
    }
    
    //Metodo donde se pintan los objetos 
     @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        g.fillRect(this.x, this.y, 50, 20);
        
        Image fondo = this.loadImage("fondo.png");
        g.drawImage(fondo, 0, 0, this);
        Image imagen = this.loadImage("1.png");      
        g.drawImage(imagen, this.x, 250, this);
        g.drawRect(this.x, 332, 181, 117);
        Image gato = this.loadImage("cats.gif");
        
        if(this.secuencia==5){
            this.secuencia=0;
        }else{
            this.secuencia++;
        }
//        g.drawImage(gato, 30, 30, 162, 110, 0, 0, 132, 80, this);
//        g.drawImage(gato, 132+30, 30, 162+132, 110, 132*4, 0, 132*4, 80, this);
        g.drawImage(gato, this.x, 300, 132+this.x, 300+80, 132*this.secuencia, 0, 132+this.secuencia*132, 80, this);
        
    }

    //Metodo que se ejecuta cada vez que se lanza un ActionListener
    @Override                                                 
    public void actionPerformed(ActionEvent e) {
        this.x++;
        repaint();
    }
    public Image loadImage(String imageName){
        ImageIcon ii= new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
        
    }
}
