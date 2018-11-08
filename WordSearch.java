public class WordSearch {
    private char[][]data;
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
	    for (int j = 0; j < data[i].length; j++) {
		x += data[i][j] + " ";
	    }
	    x += "\n";
	}
	return x;
    }
    public boolean addWordHorizontal(String word, int row, int col) {
	int j = 0;
	if (word.length() + col > data[row].length) {
	    return false;
	}
	for (int i = col; i < word.length()+col; i++) {
	    if (data[row][i] != word.charAt(j)) {
		if (data[row][i] != '_') {
		    return false;
		}
	    }
	    j++;
	}
	j=0;
	for(int i = col; i < word.length()+col; i++) {	   
	    data[row][i] = word.charAt(j);
	    j++;
	}
	return true;
    }
    public boolean addWordVertical(String word, int row, int col) {
	int j = 0;
	if (word.length() + row > data.length) {
	    return false;
	}
	for (int i = row; i < word.length()+row; i++) {
	    if (data[i][col] != word.charAt(j)) {
		if (data[i][col] != '_') {
		    return false;
		}
	    }
	    j++;
	}
	j=0;
	for(int i = row; i < word.length()+row; i++) {	   
	    data[i][col] = word.charAt(j);
	    j++;
	}
	return true;
    }
}

