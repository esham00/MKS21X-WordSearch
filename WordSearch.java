import java.util.*;
import java.io.*;
public class WordSearch {
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd = new ArrayList<String>();
    private ArrayList<String> wordsAdded = new ArrayList<String>();
    public WordSearch(int rows, int cols, String fileName, int randSeed, boolean answer) throws FileNotFoundException{
	if (rows <= 0 || cols <= 0) {
	    throw new IllegalArgumentException("A wordsearch cannot have negative or zero rows and columnns.");
	}
	data = new char[rows][cols];
	Scanner s = new Scanner(new File(fileName));
	while(s.hasNext()) {
	    wordsToAdd.add(s.next().toUpperCase());
	}
	randgen = new Random(randSeed);
	seed = randSeed;
	for (int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[i].length; j++) {
		data[i][j] = '_';
	    }
	}
	for (int i = 0; i < 10; i++) {
	  addAllWords();
	}
      	if (answer==false) {
	    fill();
	}
	if (answer == true) {
	    answerKey();
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
    public void  answerKey() {
	for(int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[i].length; j++) {
		if (data[i][j] == '_') {
		    data[i][j] = ' ';
		}
	    }
	}
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
	    if (s.length() > data.length || s.length() > data[0].length) {
		throw new IllegalArgumentException(s + " is too large to fit on the word search");
	    }
	    int rowIncrement = randgen.nextInt() % 2;
	    int columnIncrement = randgen.nextInt() % 2;
	    int length = s.length();
	    for (int j = 0; j < 200; j++) {
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
		    j = 5000;
		}
	    }
	}
	if (wordsToAdd.size() == 0) {
	    return true;
	}
	else {
	    return false;
	}
    }
    public void fill() {
	for (int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[0].length; j++) {
		if (data[i][j] == '_') {
		    data[i][j] = (char)(Math.abs(randgen.nextInt() % 26) + 65.0);
		}
	    }
	}
    }
    public static void main(String args[]) {
	if (args.length < 3) {
	    if (args.length < 1) {
		System.out.println("Please input the number of rows you want your wordsearch to have");
		System.out.println("Please input the number of columns you want your wordsearch to have");
	    }
	    if (args.length == 1) {
		System.out.println("Please input the number of columns you want your wordsearch to have");
	    }
	    System.out.println("Please input the file with the words you want to upload (with the type like file.txt)");
	    System.exit(1);
	}
	try {
	    int rows = Integer.parseInt(args[0]);
	    int columns = Integer.parseInt(args[1]);
	    String FILEname = args[2];
	    Random number = new Random();
	    int seed = number.nextInt() % 10000;
	    WordSearch generated = new WordSearch(rows, columns, FILEname, seed, false);
	    if (args.length > 3) {
		if (args.length > 4) {
		    if (args[4].equals("key")) {
		        seed = Integer.parseInt(args[3]);
			generated = new WordSearch(rows, columns, FILEname, seed, true);
		    }
		    else {
			seed = Integer.parseInt(args[3]);
			generated = new WordSearch(rows, columns, FILEname, seed,false);
		    }
		}
		if (args.length == 4) {
		    seed = Integer.parseInt(args[3]);		
		    generated = new WordSearch(rows, columns, FILEname, seed, false);
		}
	    }
	    if (args.length == 3) {
		System.out.println("Your seed is " + seed);
		generated = new WordSearch(rows, columns, FILEname, seed, false);
	    }
	    System.out.print(generated);
	} catch (FileNotFoundException e) {
	    System.out.println("Please input the file with the words you want to upload (with the type like file.txt)");
	    System.exit(1);
	}
    }
}
