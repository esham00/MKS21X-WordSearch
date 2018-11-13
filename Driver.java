import java.util.*;
import java.io.*;

public class Driver{
    public static void main(String[] args){
    try {
	WordSearch a = new WordSearch(10,10,"Emotions.txt");
	System.out.println(a.addAllWords());
	System.out.println(a);
    }
    catch(FileNotFoundException e) {e.printStackTrace();}
  }
}










