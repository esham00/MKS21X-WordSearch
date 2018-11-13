import java.util.*;
import java.io.*;
public class WordSearch {
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd = new ArrayList<String>();
    private ArrayList<String> wordsAdded = new ArrayList<String>();
    public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
	if (rows <= 0 || cols <= 0) {
	    throw new IllegalArgumentException();
	}
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
	addAllWords();
    }
    public WordSearch(int rows, int cols, String fileName)throws FileNotFoundException {
	if (rows <= 0 || cols <= 0) {
	    throw new IllegalArgumentException();
	}	
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
	addAllWords();
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
    private boolean addWord(int r, int c, String word, int rowIncrement, int colIncrement) {
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
    public boolean addAllWords() {
	for (int i = 0; i < wordsToAdd.size(); i++) {
	    String s = wordsToAdd.get(Math.abs(randgen.nextInt() % wordsToAdd.size()));
	    int length = wordsToAdd.get(i).length();
	    int rowIncrement = randgen.nextInt() % 2;
	    int columnIncrement = randgen.nextInt() % 2;
	    for (int j = 0; j < 100; j++) {
		int row = 0;
		int column = 0;
		if (rowIncrement < 0) {
		    row  = Math.abs(randgen.nextInt() % (data.length - length)) + length;
		}
		if (rowIncrement > 0) {
		    row = Math.abs(randgen.nextInt() % (data.length - length));
		}
		if (columnIncrement < 0) {
		    column = Math.abs(randgen.nextInt() % (data[0].length-length)) + length;
		}
		if (columnIncrement > 0) {
		    column = Math.abs(randgen.nextInt() % (data[0].length-length));
		}
		if (addWord(row, column, s, rowIncrement, columnIncrement) == true) {
		    addWord(row, column, s, rowIncrement, columnIncrement);
		    wordsAdded.add(s);
		    wordsToAdd.remove(s);
		    j = 200;
		}
	    }
	}
	if (wordsToAdd.size() > wordsAdded.size()) {
	    return false;
	}
	else {
	    return true;
	}
    }
}
