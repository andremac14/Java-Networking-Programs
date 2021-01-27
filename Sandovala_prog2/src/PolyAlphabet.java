/**
This class has everything needed to encrypt a string and return the 
encrypted message
@author Andre Sandoval
*/

public class PolyAlphabet 
{   
   /**
   Brings in a letter a-z and a caeser cipher number and converts 
   the letter to the correct encrypted letter
   @param text the letter to be encrypted
   @param s integer needed for formula
   @return the encrypted lesser   
   */  
   public static String encrypt(String text, int s) 
   { 
      String result = new String(); 
  
      if (Character.isUpperCase(text.charAt(0))) 
      { 
         char ch = (char)(((int)text.charAt(0) + s - 65) % 26 + 65); 
         result = result + ch; 
      } 
      else
      { 
         char ch = (char)(((int)text.charAt(0) + s - 97) % 26 + 97); 
         result = result + ch;  
      } 
      return result; 
   } 
  
   /**
   Takes in a whole message and encrypts the whole message
   @param message the string to be encrypted
   @return the encrypted string
   */  
   public String encryptMessage(String message)
   { 
      int[] encription = {5, 19, 19, 5, 19};
      String[] items = message.split("");
      String A;
      String cyphertext = "";
      A = "";
      int j = 0;
        
      for(int i = 0; i < message.length(); i++)
      {   
         char c = items[i].charAt(0);
           
         if( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
         {
            if(j > 4)
               j = 0;
              
            A = encrypt(items[i], encription[j]);
            cyphertext = cyphertext + A;
            j++;

         }
         else
            cyphertext = cyphertext + items[i];      
      }
      return cyphertext;
   } 
}
