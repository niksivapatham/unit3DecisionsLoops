import java.util.Scanner;

public class SumAndAverageValue
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter multiple numbers or 'q' to quit: ");
        
        double total = 0;
        int count = 0;
        
        while( in.hasNextDouble() )
        {
            double value = in.nextDouble();
            total += value;
            count++;
        }

        double average = 0;
        if (count > 0 )
        {
            average = total/count ;
        }
        System.out.println("Total: " + total + " Average: " + average);
    }
}
