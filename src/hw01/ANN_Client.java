/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 3, 2017
  * Time: 8:54:17 PM *
  * Project: csci205_hw
  * Package: Assignment01
  * File: ANN_Client
  * Description:
  *
  * ****************************************
 */
package hw01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author haoyuxiong
 */
public class ANN_Client {

    public static void main(String args[]) throws FileNotFoundException {
        System.out.println("Please choose between the following options:");
        System.out.println("\t1. Create a new ANN");
        System.out.println("\t2. Read in a config file");

        Scanner in = new Scanner(System.in);

        int option;

        do {
            System.out.print("Enter 1 or 2: ");

            if (in.hasNextInt()) {
                option = in.nextInt();

                if (option == 1 || option == 2) {
                    break;
                }
            }
            System.out.println("Invalid input, please try again!");

        } while (true);

        if (option == 1) {

            int numIN, numOUT;

            do {
                System.out.print(
                        "Please enter the number of inputs and outputs (e.g. 2 1): ");

                if (in.hasNextInt()) {
                    numIN = in.nextInt();

                    if (in.hasNextInt()) {
                        numOUT = in.nextInt();
                        break;
                    }
                }
                System.out.println("Wrong format, please try again!");

            } while (true);

            int mode;
            String prompt;

            do {
                System.out.print(
                        "Please choose the mode (enter 1 for training and 2 for classification: ");

                if (in.hasNextInt()) {
                    mode = in.nextInt();

                    if (mode == 1) {
                        prompt = "Please enter the filename of the training data file: ";
                        break;
                    }
                    else if (mode == 2) {
                        prompt = "Please enter the filename of the input data file: ";
                        break;
                    }
                }
                System.out.println("Invalid input, please try again!");

            } while (true);

            ANN instance = new ANN(numIN, numOUT, 2);
            String filename;
            Scanner fScanner;

            do {
                System.out.print(prompt);
                filename = in.next();

                try {
                    File f = new File(filename);
                    fScanner = new Scanner(f);
                    break;

                } catch (FileNotFoundException e) {
                    System.out.println("File not found, please try again!");
                }
            } while (true);

            ArrayList<int[]> inputArray2D = new ArrayList<int[]>();

            while (fScanner.hasNextLine()) {
                String line = fScanner.nextLine();
                String[] parts = line.split(",");
                int[] ints = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    ints[i] = Integer.parseInt(parts[i]);
                }
                inputArray2D.add(ints);
            }

            int[][] input2D = new int[inputArray2D.size()][];

            for (int i = 0; i < inputArray2D.size(); i++) {
                input2D[i] = inputArray2D.get(i);
            }

            if (mode == 1) {
                instance.train_ANN(input2D);
            }
            if (mode == 2) {
                int[][] output2D;
                output2D = instance.classify_ANN(input2D);

                System.out.print(
                        "Please enter the filename that the output should be saved into (including file extension): ");
                String outputName = in.next();
                PrintWriter out = new PrintWriter(outputName);

                for (int i = 0; i < output2D.length; i++) {
                    for (int j = 0; i < output2D[i].length; j++) {
                        if (j != 0) {
                            System.out.print(", ");
                            out.print(", ");
                        }
                        System.out.printf("%d", output2D[i][j]);
                        out.printf("%d", output2D[i][j]);
                    }
                    System.out.println();
                    out.println();
                }
                out.close();
            }
        }
    }
}
