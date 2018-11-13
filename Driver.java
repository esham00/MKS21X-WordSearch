import java.util.*;
import java.io.*;

public class Driver{
    public static void main(String[] args){
    try {
	WordSearch a = new WordSearch(10,10,"Emotions.txt");
	for (int i = 0; i < 500; i++) {
	    System.out.println(a.addAllWords());
	}
	System.out.println(a);
    }
    catch(FileNotFoundException e) {e.printStackTrace();}
  }
}










