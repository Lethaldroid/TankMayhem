package TankMayhem;

import TankMayhem.RotatedIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.BreakIterator;
import java.util.Objects;
import java.util.Vector;

public class GameCanvas implements KeyListener
{
    public JLabel wall;
    public int wall_num;
    public Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public Timer timer;
    public Timer timer2;
    public Vector<WALLS> all_walls_v = new Vector<WALLS>();
    public Vector<WALLS> all_walls_h = new Vector<WALLS>();
    public Vector<WALLS> break_walls = new Vector<WALLS>();
    public ImageIcon tank1_icon = new ImageIcon("src/TankMayhem/player-tank.png");
    public ImageIcon tank2_icon = new ImageIcon("resources/ai-tank.png");
    public ImageIcon life = new ImageIcon("resources/life.png");
    RotatedIcon l = new RotatedIcon(life, 0);
    JLabel lifeLabel1[] = {new JLabel(l),new JLabel(l),new JLabel(l)};
    JLabel lifeLabel2[] = {new JLabel(l),new JLabel(l),new JLabel(l)};
    public int healthpoints1 = 100;
    public int healthpoints2 = 100;
    JLabel hp1 = new JLabel("HP: "+ healthpoints1);
    JLabel hp2 = new JLabel("HP: "+ healthpoints2);
    JLabel bs = new JLabel(); //bullet speed
    public int lives_p1 = 3;
    public int lives_p2 = 3;
    public int lx = 35;
    public int lx2 = (int)(size.getWidth()-100);
    public int ly = 65;
    public int rangeofBullet = 100;
    public int tank1_x = 100;
    public int tank1_y = 100;
    public int tank1_w = 55;
    public int tank1_h = 47;
    public int tank2_x = 200;
    public int tank2_y = 100;
    public int tank2_w = 55;
    public int tank2_h = 47;
    int rateofrot = 5;
    public boolean state = false;
    public boolean state2 = false;
    public int tank1_r = 0;
    public int tank2_r = 0;
//    RotatedIcon r = new RotatedIcon(tank1_icon, Math.toRadians(tank1_r));
    public JLabel tank1_label = new JLabel(tank1_icon);
    public JLabel tank2_label = new JLabel(tank2_icon);
    public JFrame frame = new JFrame();
    public JPanel panel = new JPanel();
    public int wall_w = 0;
    public int wall_h = 0;
    public GameCanvas()
    {

        panel.setLayout(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        tank1_label.setIcon(tank1_icon);
        tank1_label.setBounds(tank1_x,tank1_y, tank1_w, tank1_h);
        tank2_label.setIcon(tank2_icon);
        tank2_label.setBounds(tank2_x, tank2_y, tank2_w, tank2_h);
        frame.add(panel);
        panel.add(tank1_label);
        panel.add(tank2_label);
        WALLS wallx1 = new WALLS(300, 150, 40, 220);
        WALLS wallx2 = new WALLS(1000, 150, 40, 220);
        WALLS wallx3 = new WALLS(300, 400, 40, 220);
        WALLS wallx4 = new WALLS(1000, 400, 40, 220);
        WALLS wally1 = new WALLS(350, 150, 250, 40);
        WALLS wally2 = new WALLS(740, 150, 250, 40);
        WALLS wally3 = new WALLS(350, 580, 250, 40);
        WALLS wally4 = new WALLS(740, 580, 250, 40);
        WALLS b1 = new WALLS(500, 430, 50, 50);
        WALLS b2 = new WALLS(790, 430, 50, 50);
        WALLS b3 = new WALLS(500, 270, 50, 50);
        WALLS b4 = new WALLS(790, 270, 50, 50);

        b1.setIcon(new ImageIcon("resources/break_wall.png"));
        b2.setIcon(new ImageIcon("resources/break_wall.png"));
        b3.setIcon(new ImageIcon("resources/break_wall.png"));
        b4.setIcon(new ImageIcon("resources/break_wall.png"));

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);

        break_walls.add(b1);
        break_walls.add(b2);
        break_walls.add(b3);
        break_walls.add(b4);

        wallx1.setIcon(new ImageIcon("resources/wall.png"));
        wallx2.setIcon(new ImageIcon("resources/wall.png"));
        wallx3.setIcon(new ImageIcon("resources/wall.png"));
        wallx4.setIcon(new ImageIcon("resources/wall.png"));

        wally1.setIcon(new ImageIcon("resources/wall.png"));
        wally2.setIcon(new ImageIcon("resources/wall.png"));
        wally3.setIcon(new ImageIcon("resources/wall.png"));
        wally4.setIcon(new ImageIcon("resources/wall.png"));

        panel.add(wallx1);
        panel.add(wallx2);
        panel.add(wallx3);
        panel.add(wallx4);

        panel.add(wally1);
        panel.add(wally2);
        panel.add(wally3);
        panel.add(wally4);

        all_walls_v.add(wallx1);
        all_walls_v.add(wallx2);
        all_walls_v.add(wallx3);
        all_walls_v.add(wallx4);

        all_walls_h.add(wally1);
        all_walls_h.add(wally2);
        all_walls_h.add(wally3);
        all_walls_h.add(wally4);

        JLabel playerone = new JLabel("PLAYER 1");
        playerone.setBounds(20,0,100,20);
        panel.add(playerone);
        JLabel playertwo = new JLabel("PLAYER 2");
        playertwo.setBounds((int)(size.getWidth()-200),0 ,100,20);
        panel.add(playertwo);
        hp1.setBounds(20,15,100,20);
        panel.add(hp1);
        hp2.setBounds((int)(size.getWidth()-200),15,100,20);
        panel.add(hp2);
//        bs.setBounds(20,30,100,20);
//        panel.add(bs);
        drawLives_p1(panel);
        drawLives_p2(panel);

        panel.repaint();
        frame.pack();
        frame.setSize((int)size.getWidth(), (int)size.getHeight());
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    public void decreaseLivesp1()
    {
        rangeofBullet = 100;
        lives_p1--;
        removeLifep1(panel);
        drawLives_p1(panel);
    }
    public void decreaseLivesp2()
    {
        rangeofBullet = 100;
        lives_p2--;
        removeLifep2(panel);
        drawLives_p2(panel);
    }
    public void DecreaseHP1(JPanel panel)
    {
        if(healthpoints1 <= 50)
        {
            healthpoints1 = 100;
            decreaseLivesp1();
            hp1.setText("HP: "+ healthpoints1);
            panel.repaint();

        }
        else
        {
            healthpoints1 -=50;
            hp1.setText("HP: "+ healthpoints1);
            panel.repaint();
        }
    }
    public void DecreaseHP2(JPanel panel)
    {
        if(healthpoints2 <= 50)
        {
            healthpoints2 = 100;
            decreaseLivesp2();
            hp2.setText("HP: "+ healthpoints2);
            panel.repaint();

        }
        else
        {
            healthpoints2 -=50;
            hp2.setText("HP: "+ healthpoints2);
            panel.repaint();
        }
    }

    public void drawLives_p1(JPanel panel)
    {
        for (int i=0;i<lives_p1;i++)
        {
            panel.add(lifeLabel1[i]);
            lifeLabel1[i].setBounds(lx,ly,5,5);
            lx+=40;
        }
        panel.repaint();
        lx = 35;
    }
    public void drawLives_p2(JPanel panel)
    {
        for (int i=0;i<lives_p2;i++)
        {
            panel.add(lifeLabel2[i]);
            lifeLabel2[i].setBounds(lx2,ly,5,5);
            lx2-=40;
        }
        panel.repaint();
        lx2 = (int)(size.getWidth()-35);
    }
    public void removeLifep1(JPanel panel)
    {
        if(lives_p1>0)
        {
            panel.remove(lifeLabel1[lives_p1]);
            panel.repaint();
        }
        else
        {
            LoseGUI lost = new LoseGUI();
            frame.dispose();

        }
    }
    public void removeLifep2(JPanel panel)
    {
        if(lives_p2>0)
        {
            panel.remove(lifeLabel2[lives_p2]);
            panel.repaint();
        }
        else
        {
            LoseGUI lost = new LoseGUI();
            frame.dispose();
        }
    }

    public void SuperEffect1()
    {
        rangeofBullet += 50;
    }
    public void SuperEffect2()
    {
        if(lives_p1<3)
        {
            lives_p1++;
            drawLives_p1(panel);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w')
        {
            int cond = 0;
            for (int i = 0;i < all_walls_h.size();i++){
                if ((tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) < all_walls_h.get(i).x + all_walls_h.get(i).w && (tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > all_walls_h.get(i).x && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) < all_walls_h.get(i).y + all_walls_h.get(i).h && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > all_walls_h.get(i).y){
                    cond = 1;
                }
            }
            for (int i = 0;i < all_walls_v.size();i++){
                if ((tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) < all_walls_v.get(i).x + all_walls_v.get(i).w && (tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > all_walls_v.get(i).x && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) < all_walls_v.get(i).y + all_walls_v.get(i).h && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > all_walls_v.get(i).y ){
                    cond = 1;
                }
            }
            if ((tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10)  < tank2_x + tank2_x && (tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > tank2_x && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) < tank2_y + tank2_h && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > tank2_y){
                cond = 1;
            }
            for (int i = 0;i < break_walls.size();i++){
                if ((tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) < break_walls.get(i).x + break_walls.get(i).w && (tank1_x + Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > break_walls.get(i).x && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) < break_walls.get(i).y + break_walls.get(i).h && (tank1_y + Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > break_walls.get(i).y){
                    cond = 1;
                }
            }
            if (cond == 0 && (tank1_x +tank1_w +  Math.cos(Math.toRadians(tank1_r)) * 10) < size.getWidth() - 10 && (tank1_y +tank1_h+  Math.sin(Math.toRadians(tank1_r)) * 20) < size.getHeight() - 50 && (tank1_x +  Math.cos(Math.toRadians(tank1_r)) * 10) > 0 && (tank1_y  +  Math.sin(Math.toRadians(tank1_r)) * 10) > 0 ){
                tank1_x += Math.cos(Math.toRadians(tank1_r)) * 20;
                tank1_y += Math.sin(Math.toRadians(tank1_r)) * 20;
            }
            tank1_label.setBounds(tank1_x, tank1_y, tank1_w, tank1_h);
            panel.repaint();

        }

        if (key == 'i')
        {
            int cond = 0;
            for (int i = 0;i < all_walls_h.size();i++){
                if ((tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) < all_walls_h.get(i).x + all_walls_h.get(i).w && (tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > all_walls_h.get(i).x && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) < all_walls_h.get(i).y + all_walls_h.get(i).h && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > all_walls_h.get(i).y){
                    cond = 1;
                }
            }
            for (int i = 0;i < all_walls_v.size();i++){
                if ((tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) < all_walls_v.get(i).x + all_walls_v.get(i).w && (tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > all_walls_v.get(i).x && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) < all_walls_v.get(i).y + all_walls_v.get(i).h && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > all_walls_v.get(i).y){
                    cond = 1;
                }
            }

            if ((tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10)  < tank1_x + tank1_x && (tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > tank1_x && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) < tank1_y + tank1_h && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > tank1_y){
                cond = 1;
            }
            for (int i = 0;i < break_walls.size();i++){
                if ((tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) < break_walls.get(i).x + break_walls.get(i).w && (tank2_x + Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > break_walls.get(i).x && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) < break_walls.get(i).y + break_walls.get(i).h && (tank2_y + Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > break_walls.get(i).y){
                    cond = 1;
                }
            }

            if (cond == 0 && (tank2_x +tank2_w +  Math.cos(Math.toRadians(tank2_r)) * 10) < size.getWidth() - 10 && (tank2_y +tank2_h+  Math.sin(Math.toRadians(tank2_r)) * 20) < size.getHeight() - 50 && (tank2_x +  Math.cos(Math.toRadians(tank2_r)) * 10) > 0 && (tank2_y  +  Math.sin(Math.toRadians(tank2_r)) * 10) > 0){
                tank2_x += Math.cos(Math.toRadians(tank2_r)) * 20;
                tank2_y += Math.sin(Math.toRadians(tank2_r)) * 20;
            }
            tank2_label.setBounds(tank2_x, tank2_y, tank2_w, tank2_h);
            panel.repaint();

        }

        if (key == 'a'){
            tank1_r -= rateofrot;
            tank1_r = tank1_r % 360;
            RotatedIcon r = new RotatedIcon(tank1_icon, Math.toRadians(tank1_r));
            r.setDegrees(tank1_r);
            tank1_label.setIcon(r);
        }

        if (key == 'j'){
            tank2_r -= rateofrot;
            tank2_r = tank2_r % 360;
            RotatedIcon r = new RotatedIcon(tank2_icon, Math.toRadians(tank2_r));
            r.setDegrees(tank2_r);
            tank2_label.setIcon(r);
        }

        if (key == 's')
        {
            int cond = 0;
            for (int i = 0;i < all_walls_h.size();i++){
//                System.out.println(tempx < all_walls_h.get(i).x + all_walls_h.get(i).w && tempx + tank1_w > all_walls_h.get(i).x && tempy < all_walls_h.get(i).y + all_walls_h.get(i).h && tempy + tank1_h > all_walls_h.get(i).y);
                if ((tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) < all_walls_h.get(i).x + all_walls_h.get(i).w && (tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > all_walls_h.get(i).x && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) < all_walls_h.get(i).y + all_walls_h.get(i).h && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > all_walls_h.get(i).y){
                    cond = 1;
                }
            }
            for (int i = 0;i < all_walls_v.size();i++){
//                System.out.println(tempx < all_walls_h.get(i).x + all_walls_h.get(i).w && tempx + tank1_w > all_walls_h.get(i).x && tempy < all_walls_h.get(i).y + all_walls_h.get(i).h && tempy + tank1_h > all_walls_h.get(i).y);
                if ((tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) < all_walls_v.get(i).x + all_walls_v.get(i).w && (tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > all_walls_v.get(i).x && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) < all_walls_v.get(i).y + all_walls_v.get(i).h && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > all_walls_v.get(i).y){
                    cond = 1;
                }
            }
            if ((tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10)  < tank2_x + tank2_x && (tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > tank2_x && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) < tank2_y + tank2_h && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > tank2_y){
                cond = 1;
            }
            for (int i = 0;i < break_walls.size();i++){
                if ((tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) < break_walls.get(i).x + break_walls.get(i).w && (tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) + tank1_w > break_walls.get(i).x && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) < break_walls.get(i).y + break_walls.get(i).h && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) + tank1_h > break_walls.get(i).y){
                    cond = 1;
                }
            }
//            System.out.println(cond);
            if (cond == 0 && (tank1_x - Math.cos(Math.toRadians(tank1_r)) * 10) > 0 && (tank1_y - Math.sin(Math.toRadians(tank1_r)) * 10) > 0 && (tank1_y - tank1_h + Math.sin(Math.toRadians(tank1_r)) * 10) < size.getHeight() - 50 && (tank1_x - tank1_w + Math.cos(Math.toRadians(tank1_r)) * 10) < size.getWidth() - 10){
                tank1_x -= Math.cos(Math.toRadians(tank1_r)) * 20;
                tank1_y -= Math.sin(Math.toRadians(tank1_r)) * 20;
            }

            tank1_label.setBounds(tank1_x, tank1_y, tank1_w, tank1_h);
            panel.repaint();
        }

        if (key == 'k')
        {
            int cond = 0;
            for (int i = 0;i < all_walls_h.size();i++){
                if ((tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) < all_walls_h.get(i).x + all_walls_h.get(i).w && (tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > all_walls_h.get(i).x && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) < all_walls_h.get(i).y + all_walls_h.get(i).h && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > all_walls_h.get(i).y){
                    cond = 1;
                }
            }
            for (int i = 0;i < all_walls_v.size();i++){
//                System.out.println(tempx < all_walls_h.get(i).x + all_walls_h.get(i).w && tempx + tank2_w > all_walls_h.get(i).x && tempy < all_walls_h.get(i).y + all_walls_h.get(i).h && tempy + tank2_h > all_walls_h.get(i).y);
                if ((tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) < all_walls_v.get(i).x + all_walls_v.get(i).w && (tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > all_walls_v.get(i).x && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) < all_walls_v.get(i).y + all_walls_v.get(i).h && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > all_walls_v.get(i).y){
                    cond = 1;
                }
            }
            if ((tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10)  < tank1_x + tank1_x && (tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > tank1_x && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) < tank1_y + tank1_h && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > tank1_y){
                cond = 1;
            }
            for (int i = 0;i < break_walls.size();i++){
                if ((tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) < break_walls.get(i).x + break_walls.get(i).w && (tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) + tank2_w > break_walls.get(i).x && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) < break_walls.get(i).y + break_walls.get(i).h && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) + tank2_h > break_walls.get(i).y){
                    cond = 1;
                }
            }
            if (cond == 0 && (tank2_x - Math.cos(Math.toRadians(tank2_r)) * 10) > 0 && (tank2_y - Math.sin(Math.toRadians(tank2_r)) * 10) > 0 && (tank2_y - tank2_h + Math.sin(Math.toRadians(tank2_r)) * 10) < size.getHeight() - 50 && (tank2_x - tank2_w + Math.cos(Math.toRadians(tank2_r)) * 10) < size.getWidth() - 10){
                tank2_x -= Math.cos(Math.toRadians(tank2_r)) * 20;
                tank2_y -= Math.sin(Math.toRadians(tank2_r)) * 20;
            }

            tank2_label.setBounds(tank2_x, tank2_y, tank2_w, tank2_h);
            panel.repaint();
        }


        if (key == 'd'){
            tank1_r += rateofrot;
            tank1_r = tank1_r % 360;
            RotatedIcon r = new RotatedIcon(tank1_icon, Math.toRadians(tank1_r), false);
            r.setDegrees(tank1_r);
            tank1_label.setIcon(r);
        }

        if (key == 'l'){
            tank2_r += rateofrot;
            tank2_r = tank2_r % 360;
            RotatedIcon r = new RotatedIcon(tank2_icon, Math.toRadians(tank2_r), false);
            r.setDegrees(tank2_r);
            tank2_label.setIcon(r);
        }

        if (key == 'q')
        {
            if (!state)
            {
                Bullets b = new Bullets((tank1_x + tank1_w / 2), (tank1_y + tank1_h / 2), tank1_r);
                b.first_update();
                panel.add(b);
                state = !state;
                timer = new Timer(60, new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        System.out.println(((b.check_walls(break_walls) != -1)));
                        b.bullet_firstshot(all_walls_v, all_walls_h);
                        if (b.check_walls(break_walls) != -1){
                            int temp = b.check_walls(break_walls);
                            panel.remove(break_walls.get(b.check_walls(break_walls)));
                            break_walls.get(temp).x = -50;
                            break_walls.get(temp).y = -50;

                            b.setBullet_counter(100);
                        }
                        if(b.check_tank(tank1_x, tank1_y, tank1_w, tank1_h))
                        {
                              DecreaseHP1(panel);
                              panel.remove(b);
                              state = !state;
                              timer.stop();
                              panel.repaint();
//                            panel.remove(tank1_label);
//                            tank1_x = -1 * tank1_w;
//                            tank1_y = -1 * tank1_h;
                            //b.setBullet_counter(100);
                        }
                        if (b.check_tank(tank2_x, tank2_y, tank2_w, tank2_h)){
//                            panel.remove(tank2_label);
//                            tank2_x = -1 * tank2_w;
//                            tank2_y = -1 * tank2_h;
//                            b.setBullet_counter(100);
                            DecreaseHP2(panel);
                            panel.remove(b);
                            panel.repaint();
                            state = !state;
                            timer.stop();

                        }
                        panel.repaint();
                        if (state)
                        {
                            b.incBullet_counter();
                            bs.setText(""+b.bullet_counter);
                            panel.repaint();
                            if (b.getBullet_counter() >= 100)
                            {
//                                System.out.println("Timer stopped bullet 1");
                                panel.remove(b);
                                panel.repaint();
                                state = false;
                                timer.stop();
                            }
                        }
                    }
                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.setInitialDelay(0);
                timer.start();
            }
        }
        if (key == 'u')
        {
            if (!state2)
            {
                Bullets b2 = new Bullets((tank2_x + tank2_w / 2), (tank2_y + tank2_h / 2), tank2_r);
                b2.first_update();
                panel.add(b2);
                state2 = !state2;

                timer2 = new Timer(60, new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
//                        System.out.println(all_walls.size());
                        b2.bullet_firstshot(all_walls_v, all_walls_h);
                        if (b2.check_walls(break_walls) != -1){
                            int temp = b2.check_walls(break_walls);
                            panel.remove(break_walls.get(b2.check_walls(break_walls)));
                            break_walls.get(temp).x = -50;
                            break_walls.get(temp).y = -50;

                            b2.setBullet_counter(100);
                        }
                        if ( b2.check_tank(tank2_x, tank2_y, tank2_w, tank2_h))
                        {
                            DecreaseHP2(panel);
                            panel.remove(b2);
                            panel.repaint();
                            state2=false;
                            timer2.stop();
                        }
                        if (b2.check_tank(tank1_x, tank1_y, tank1_w, tank1_h))
                        {
                            DecreaseHP1(panel);
                            state2=false;
                            timer2.stop();
                            panel.remove(b2);
                            panel.repaint();
//                            panel.remove(tank1_label);
//                            tank1_x = -1 * tank1_w;
//                            tank1_y = -1 * tank1_h;
//                            b2.setBullet_counter(100);
                        }

                        panel.repaint();
                        if (state2)
                        {
                            b2.incBullet_counter();
                            panel.repaint();
                            if (b2.getBullet_counter() >= 100)
                            {
//                                System.out.println("Timer stopped bullet 2");
                                panel.remove(b2);
                                panel.repaint();
                                state2 = false;
                                timer2.stop();
                            }
                        }
                    }
                });
                timer2.setRepeats(true);
                timer2.setCoalesce(true);
                timer2.setInitialDelay(0);
                timer2.start();
            }
        }


//        tank1_label.setBounds(tank1_x , tank1_y, tank1_w, tank1_h);
        panel.repaint();
    }


    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
