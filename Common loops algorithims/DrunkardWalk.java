import java.util.Random;

public class DrunkardWalk
{
   public static void main(String[] args)
   {
       int xPosition = 0;
       int yPosition = 0;
       Random generator = new Random();
       for (int i = 0; i<=100000000; i++)
       {
           int direction = generator.nextInt(4)+1;
           switch (direction) 
           {
               //Going upwards (Positive Y)
               case 1:
               yPosition ++;
               break;
               
               //Going Left (Positive X)
               case 2:
               xPosition ++;
               break;
               
               //Going Down (Negative Y)
               case 3:
               yPosition --;
               break;
               
               //Going Right (Negative X)
               case 4:
               xPosition --;
               break;
               
           }
       }
       System.out.println("Your drunk friend has walked to the point (" + xPosition + "," + yPosition + ").") ;
       double distance = Math.sqrt(Math.pow(xPosition,2)+Math.pow(yPosition,2));
       System.out.println("The distance from that to the origin is " + distance + " units.");
   }
}
