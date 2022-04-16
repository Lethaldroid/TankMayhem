package TankMayhem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class HomeGUI implements KeyListener
{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    public HomeGUI()
    {
        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);

        JLabel ETC = new JLabel("Press Any Key To Continue");
        ETC.setBounds(245,390,300,25);
        panel.add(ETC);

        ImageIcon background=new ImageIcon("src/TankMayhem/Title.jpg");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(640,480,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel back=new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,630,480);
        panel.add(back);
        frame.setVisible(true);
        frame.addKeyListener((KeyListener) this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void quitHome(JFrame frame)
    {
        frame.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        quitHome(this.frame);
        GameCanvas canvas = new GameCanvas();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

}
