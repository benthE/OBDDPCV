package CSVGen;

import GeneticAlg.DCSTest;

import java.io.*;

public class CSVGenerator {
    private static String file_name = DCSTest.filename.substring(0,23)+".csv";

    public static void main(String args[]) {
        try {
            FileInputStream ifstream = new FileInputStream(DCSTest.filename);

            FileWriter ofstream = new FileWriter(file_name);
            BufferedWriter out = new BufferedWriter(ofstream);

            DataInputStream in = new DataInputStream(ifstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                String line = "";
                String[] words = strLine.split("    ");
                for (int i = 0; i < words.length; i++) {
                    if (i == 0) {
                        line = words[i];
                    } else {
                        line += " , " + words[i];
                    }
                }
                line += "\n";
                out.write(line);
            }
            System.out.println("File created successfully.");
            out.close();
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}