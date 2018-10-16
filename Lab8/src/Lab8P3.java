import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Lab8P3 {


	public static void main(String[] args) {
		File outputFile = new File("output");
		word_count("alice30.txt", "output");
	}

	private static HashMap<String, Integer> hashmap = new HashMap<>();

	public static void word_count(String inputFile, String outputFile) {
		File f = new File(inputFile);
		try {
		BufferedReader b = new BufferedReader(new FileReader(f));
		try {
			String line;
			while((line = b.readLine()) != null) {
				String[] split = line.split("\\s+");
				for(String s : split) {
					if(!getHashmap().containsKey(s)) {
						getHashmap().put(s,  1);
					}else {
						getHashmap().put(s, getHashmap().get(s) + 1);
					}
				}
			}
			PrintWriter pw = new PrintWriter(outputFile);

			hashmap.forEach((k,v) -> pw.println(("key: "+ k + " \nvalue: "+ v + " \n")));
			pw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}


	public static HashMap<String, Integer> getHashmap() {
		return hashmap;
	}

	public void setHashmap(HashMap<String, Integer> hashmap) {
		this.hashmap = hashmap;
	}
}


