import java.util.*;
import java.io.*;
public class WordSearch {
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd = new ArrayList<String>();
    private ArrayList<String> wordsAdded = new ArrayList<String>();
    public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
	data = new char[rows][cols];
	Scanner s = new Scanner(fileName);
	while(s.hasNext()) {
	    wordsToAdd.add(s.next());
	}
	randgen = new Random(randSeed);
	seed = randSeed;
	for (int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[i].length; j++) {
		data[i][j] = '_';
	    }
	}
    }
    public WordSearch(int rows, int cols, String fileName)throws FileNotFoundException {
	data = new char[rows][cols];
	Scanner s = new Scanner(new File(fileName));
	while(s.hasNext()) {
	    wordsToAdd.add(s.next().toUpperCase());
	}
	randgen = new Random();
     	for (int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[i].length; j++) {
		data[i][j] = '_';
	    }
	}
    }
    private void clear() {
	for (int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[i].length; j++) {
		data[i][j] = '_';
	    }
	}
    }
    public String toString() {
	String x = "";
	for(int i = 0; i < data.length; i++) {
	    x += "|";
	    for (int j = 0; j < data[i].length; j++) {
		x += data[i][j] + " ";
	    }
	    x += "|\n";
	}
	return x;
    }
    public boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement) {
	int length = word.length()-1;
	if (rowIncrement == 0 && colIncrement == 0) {
	    return false;
	}
	if ((rowIncrement > 0 && length + r >= data.length) || (rowIncrement < 0 && r - length < 0) || (colIncrement > 0 && length + c >= data[0].length) || (colIncrement < 0 && c - length < 0)) {
	    return false;
	}
        for (int i = 0; i < word.length(); i++) {
	    if (data[r][c] != '_') {
		if (data[r][c] != word.charAt(i)) {
		    return false;
		}
	    }
	    c+=colIncrement;
	    r+=rowIncrement;
	}
	c += colIncrement*-word.length();
	r += rowIncrement*-word.length();
	for (int i = 0; i < word.length(); i++) {
	    data[r][c] = word.charAt(i);
	    c += colIncrement;
	    r += rowIncrement;
	}
       	return true;
    }
}
