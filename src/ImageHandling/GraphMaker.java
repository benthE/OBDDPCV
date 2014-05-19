package ImageHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.*;

/**
 * Created by Vincent on 14/05/2014.
 */
public class GraphMaker {

    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        String fileToRead;

        System.out.println("Which file do you want to read ?");
        fileToRead = s.nextLine();
        BufferedReader CSVFile = new BufferedReader(new FileReader(fileToRead));

        MapImage GAGraph = new MapImage(100, 100, 0, 0, 100, 100);

        String dataRow = CSVFile.readLine(); // Read first line.

        while (dataRow != null){
            String[] dataArray = dataRow.split(",");
            double i = 10;
            for (String item:dataArray) {
                System.out.print(item + "\t");
                GAGraph.drawEvent(10+i, 10+i, Color.blue, 10);
                i++;
            }
            System.out.println(); // Print the data line.
            dataRow = CSVFile.readLine(); // Read next line of data.
        }

        GAGraph.saveToFile("Graph.png");

        // Close the file once all data has been read.
        CSVFile.close();

        // End the printout with a blank line.
        System.out.println();

    } //main()
}
