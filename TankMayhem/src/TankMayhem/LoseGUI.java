package TankMayhem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LoseGUI implements KeyListener
{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public LoseGUI()
    {
        frame.setSize((int)size.getWidth(),(int)size.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);

        JLabel ETC = new JLabel("Press any Button to restart");
        panel.add(ETC);
        ETC.setVerticalTextPosition(SwingConstants.CENTER);
        ETC.setVerticalAlignment(SwingConstants.CENTER);
        ETC.setHorizontalTextPosition(SwingConstants.CENTER);
        ETC.setHorizontalAlignment(SwingConstants.CENTER);
        ETC.setBounds(((int)size.getWidth()/4),(int)size.getHeight()/3,(int)size.getWidth()/2,(int)size.getHeight()/3);
        ETC.setFont(new Font("Serif",Font.BOLD,30));
        ETC.setBackground(Color.darkGray);
        ETC.setForeground(Color.white);
        ETC.setVisible(true);


        ImageIcon background=new ImageIcon("resources/lose.png");
        Image img=background.getImage();
        Image temp=img.getScaledInstance((int)size.getWidth(),(int)size.getHeight(),Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel back=new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,(int)size.getWidth(),(int)size.getHeight());
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
