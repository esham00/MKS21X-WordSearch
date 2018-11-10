import java.util.*;
import java.io.*;
public class WordSearch {
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd, wordsAdded;
    public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
	data = new char[rows][cols];
	Scanner s = new Scanner(fileName);
	while(s.hasNext()) {
	    wordsToAdd.add(s.next());
	}
	randgen = new Random(randSeed);
	seed = randSeed;
    }
    public WordSearch(int rows, int cols, String fileName)throws FileNotFoundException {
	data = new char[rows][cols];
	Scanner s = new Scanner(new File(fileName));
	while(s.hasNext()) {
	    wordsToAdd.add(s.next());
	}
    }
    
    public WordSearch(int rows, int cols) {
	data = new char[rows][cols];
	for(int i = 0; i < data.length; i++) {
	    for (int j = 0; j < data[i].length; j++) {
	        data[i][j] = '_';
	    }
	}
    }
    private void clear() {
	data = new char[1][1];
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
    // public boolean addWordHorizontal(String word, int row, int col) {
    // 	int j = 0;
    // 	if (word.length() + col > data[row].length) {
    // 	    return false;
    // 	}
    // 	for (int i = col; i < word.length()+col; i++) {
    // 	    if (data[row][i] != word.charAt(j)) {
    // 		if (data[row][i] != '_') {
    // 		    return false;
    // 		}
    // 	    }
    // 	    j++;
    // 	}
    // 	j=0;
    // 	for(int i = col; i < word.length()+col; i++) {	   
    // 	    data[row][i] = word.charAt(j);
    // 	    j++;
    // 	}
    // 	return true;
    // }
    // public boolean addWordVertical(String word, int row, int col) {
    // 	int j = 0;
    // 	if (word.length() + row > data.length) {
    // 	    return false;
    // 	}
    // 	for (int i = row; i < word.length()+row; i++) {
    // 	    if (data[i][col] != word.charAt(j)) {
    // 		if (data[i][col] != '_') {
    // 		    return false;
    // 		}
    // 	    }
    // 	    j++;
    // 	}
    // 	j=0;
    // 	for(int i = row; i < word.length()+row; i++) {	   
    // 	    data[i][col] = word.charAt(j);
    // 	    j++;
    // 	}
    // 	return true;
    // }
    // public boolean addWordDiagonal(String word, int row, int col) {
    // 	int j = 0;
    // 	if (word.length() + row > data.length ||  word.length() + col > data[row].length) {
    // 	    return false;
    // 	}
    // 	for (int x = row; x < word.length()+row; x++) {
    // 	    if (data[x][col] != word.charAt(j)) {
    // 		if (data[x][col] != '_') {
    // 		    return false;
    // 			}
    // 	    }
    // 	    j++;
    // 	    col++;
    // 	}
    // 	j = 0;
    // 	col -= word.length();
    // 	for (int i = row; i < word.length()+row;i++) {
    // 	    data[i][col] = word.charAt(j);
    // 	    j++;
    // 	    col++;
    // 	}
    // 	return true;
    // }
    private boolean addWord(int r, int c, String word, int rowIncrement, int colIncrement) {
	if (rowIncrement == 0 && colIncrement == 0) {
	    return false;
	}
	if (word.length() + Math.abs(rowIncrement) > data.length || word.length() + Math.abs(colIncrement) > data[r].length) {
	    return false;
	}
	for (
}
