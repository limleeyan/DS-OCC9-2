package def;

import java.util.*;
public class CaesarCipher{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Text: ");
        String text = input.nextLine();
        System.out.print("Shift: ");
        int shift = input.nextInt();

        String decrypted = "";
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if(Character.isAlphabetic(c)) {
                decrypted += (char)(((int)c - shift - 97 + 26) % 26 + 97);
            }

            else if(c == '^') {
                char a = (char)(((int)text.charAt(++i) - shift - 97 + 26) % 26 + 97);
                decrypted += Character.toUpperCase(a);
            }

            else if(c == '(') {
                int end_index = text.indexOf(')', i);
                String reversed = new StringBuilder(text.substring(i+1, end_index)).reverse().toString();
                for(int j=0; j<reversed.length(); j++){
                    decrypted += (char)(((int)reversed.charAt(j) - shift - 97 + 26) % 26 + 97);
                }
                i = end_index;
            }

            else if(c == '$') {
                decrypted += " ";
            }

            else {
                decrypted += c;
            }
        }

        System.out.println("Decrypted text: " + decrypted);
    }
}
