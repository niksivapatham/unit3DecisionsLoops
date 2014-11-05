import java.util.Scanner;
import java.util.Random;

public class SwapLetters
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Give me a word fat nuget: ");
        String word = in.nextLine();
        Random generator = new Random();
        if (word.length() > 1)
        {
            for(int i = 0; i<word.length(); i++)
            {
                int index = generator.nextInt(word.length()-1);
                int range = word.length()-index-1;
                int index2;
                if(range != 0)
                {
                    index2 = generator.nextInt(range)+index+1;
                }
                else
                {
                    System.out.println("this case was needed!!");
                    index2 = index +1;
                }
                //System.out.println("index: " + index + " index2: " + index2);
                String first = word.substring(0,index);
                String middle = word.substring(index+1, index2);
                String last = word.substring(index2+1);
                word = first+word.charAt(index2)+middle+word.charAt(index)+last;
            }
            
        }
        System.out.println(word);
    }
}
