package TankMayhem;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Bullets extends JLabel
{
    public Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public int bullet_counter = 0;
    public int priv_x;
    public int priv_y;
    public int bullet_r = 0;
    public int bullet_x = 0;
    public int bullet_y = 0;
    public int bullet_w = 12;
    public int bullet_h = 8;
    public int cdx = 1;
    public int cdy = 1;
    private ImageIcon bullet_icon = new ImageIcon("resources/shell.png");

    public Bullets(int x ,int y, int tank_r)
    {
        this.bullet_r = tank_r;
        this.bullet_x = x;
        this.bullet_y = y;
        RotatedIcon bullet = new RotatedIcon(this.bullet_icon, Math.toRadians(bullet_r));
        bullet.setDegrees(bullet_r);
        super.setIcon(bullet);
    }
    public void first_update() {
        this.bullet_x += (Math.cos(Math.toRadians(this.bullet_r))*30) * this.cdx;
        this.bullet_y += (Math.sin(Math.toRadians(this.bullet_r))*30) * this.cdy;
    }

    public void bullet_firstshot(Vector<WALLS> all_walls_v, Vector<WALLS> all_walls_h)
    {
        for (int i = 0;i < all_walls_v.size();i++){
            if (this.bullet_x <= (all_walls_v.get(i).x + all_walls_v.get(i).w) && this.bullet_x + this.bullet_w >= all_walls_v.get(i).x && this.bullet_y <= all_walls_v.get(i).y + all_walls_v.get(i).h && this.bullet_y + this.bullet_h >= all_walls_v.get(i).y){
                if (this.bullet_x > all_walls_v.get(i).x){
                    this.bullet_x = all_walls_v.get(i).x + all_walls_v.get(i).w+ 1;
                }
                this.cdx *= -1;
            }
        }
        for (int i = 0;i < all_walls_h.size();i++){
            if (this.bullet_x < (all_walls_h.get(i).x + all_walls_h.get(i).w) && this.bullet_x + this.bullet_w > all_walls_h.get(i).x && this.bullet_y < all_walls_h.get(i).y + all_walls_h.get(i).h && this.bullet_y + this.bullet_h > all_walls_h.get(i).y){
                if (this.bullet_y > all_walls_h.get(i).y){
                    this.bullet_y = all_walls_h.get(i).y + all_walls_h.get(i).h+ 1;
                }
                this.cdy *= -1;
            }
        }
        if (this.bullet_x > (int)(size.getWidth() - 50))
        {
            this.cdx *= -1;
            this.bullet_x = (int)size.getWidth() - 51;
//            System.out.println("IN THE IF BULLETX: " + this.bullet_x);
        }
        if (this.bullet_y > ((int)size.getHeight() - 50)){
            this.cdy *= -1;
            this.bullet_y = (int)size.getHeight() - 51;
        }
        if (this.bullet_x < 20){
            this.cdx *= -1;
            this.bullet_x = 21;
        }
        if (this.bullet_y < 10) {
            this.cdy *= -1;
            this.bullet_y = 11;
        };
        priv_x = this.bullet_x;
        priv_y = this.bullet_y;

        if (Math.toRadians(this.bullet_r) == -3.141592653589793){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
            this.bullet_y += ((Math.sin(Math.toRadians(this.bullet_r))*20) * this.cdy);
        }
        System.out.println(((Math.sin(Math.toRadians(this.bullet_r))*20) * this.cdy));
        this.bullet_x += (Math.cos(Math.toRadians(this.bullet_r))*20) * this.cdx;
//        System.out.println(this.bullet_x);
//        System.out.println(this.bullet_y);
//        System.out.println((Math.cos(Math.toRadians(this.bullet_r))*20));
//        System.out.println((Math.sin(Math.toRadians(this.bullet_r))*20));
//        System.out.println(Math.toRadians(this.bullet_r));
//        if(Math.cos(Math.toRadians(this.bullet_r))*30 < 0)
//        {
//            this.bullet_x -= 6;
//        }
//        if(Math.sin(Math.toRadians(this.bullet_r))*30 < 0)
//        {
//            this.bullet_y -= 4;
//        }
        super.setBounds(bullet_x,bullet_y,12,8);
    }
//    public void bullet_traversal()
//    {
//        System.out.println(this.bullet_x > (size.getWidth() - 100));
//        System.out.println( "WDITH: " + (int)(size.getWidth() - 100));
//        if (this.bullet_x > (int)(size.getWidth() - 100))
//        {
//            this.cdx *= -1;
//            this.bullet_x = (int)size.getWidth() - 101;
//            System.out.println("IN THE IF BULLETX: " + this.bullet_x);
//        }
//        this.bullet_x += (Math.cos(Math.toRadians(this.bullet_r))) * this.cdx;
//        System.out.println((Math.cos(Math.toRadians(this.bullet_r))) * this.cdx);
//        System .out.println("BULLETS: X : " + this.bullet_x);
//        this.bullet_y += (Math.sin(Math.toRadians(this.bullet_r))) * this.cdy;
//
//    }
    public void incBullet_counter()
    {
        this.bullet_counter++;
    }
    public int getBullet_counter()
    {
        return this.bullet_counter;
    }
    public void setBullet_counter(int b){
        this.bullet_counter = b;
    }
    public boolean check_tank(int x, int y, int w, int h){
        if (this.bullet_x < x + w && this.bullet_x + this.bullet_w > x && this.bullet_y < y + h && this.bullet_y + this.bullet_h > y){
            return true;
        }
        else{
            return false;
        }
    }
    public int check_walls(Vector<WALLS> wall){
        for (int i = 0;i < wall.size();i++){
            if (this.bullet_x < wall.get(i).x + wall.get(i).w && this.bullet_x + this.bullet_w > wall.get(i).x && this.bullet_y < wall.get(i).y + wall.get(i).h && this.bullet_y + this.bullet_h > wall.get(i).y) {
                return i;
            }
        }
        return -1;
    }

}
