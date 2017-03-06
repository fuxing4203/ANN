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

    static Scanner in;
    static boolean firstRound = true;
    static int numIN;
    static int numOUT;
    static int numLayer;
    static int numNeuron;
    static int maxSSE;
    static ANN ann;

    public static void main(String args[]) throws FileNotFoundException {
        System.out.println("Please choose between the following options:");
        System.out.println("\t1. Create a new ANN");
        System.out.println("\t2. Read in a config file");

        in = new Scanner(System.in);

        int option = oneOrTwo();

        // Create new ANN
        if (option == 1) {

            askInAndOutNum();

            System.out.println("Please choose between the following modes: ");
            System.out.println("\t1. training");
            System.out.println("\t2. classification");

            int mode = oneOrTwo();

            // For training
            if (mode == 1) {
                train();
            }

            // For classification
            else if (mode == 2) {
                classification();
            }

            ann = new ANN(numIN, numOUT, numLayer, numNeuron, maxSSE);

            Scanner fScanner = readInputFile();

            int[][] input2D = processInputFile(fScanner);

            // For training
            if (mode == 1) {
                instance.train_ANN(input2D);

                int save;
                String configFilename;

                do {
                    System.out.println(
                            "Would you like to save the network configuration to a configuration file? ");
                    System.out.print("Enter 1 for yes or 2 for no: ");

                    if (in.hasNextInt()) {
                        save = in.nextInt();

                        // Save to config file
                        if (save == 1) {
                            System.out.print(
                                    "Enter the desired name for the configuration file: ");
                            configFilename = in.next();

                            ArrayList<Perceptron> perceptronList = instance.getPerceptronsList();
                            ArrayList<ArrayList<Double>> weights2D = null;

                            for (Perceptron perceptron : perceptronList) {
                                ArrayList weigths = perceptron.getWeights();
                                weights2D.add(weigths);
                            }

                            File configFile = new File("config.properties");

                            break;
                        }
                        // Don't save to config file
                        else if (save == 2) {
                            break;
                        }
                        System.out.println("Invalid input, please try again!");
                    }
                } while (true);
            }
            // For classification
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

    private static int oneOrTwo() {
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

        return option;
    }

    private static void askInAndOutNum() {

        do {
            try {
                System.out.print("Please enter the number of inputs: ");
                numIN = in.nextInt();

                System.out.print("Please enter the number of outputs: ");
                numOUT = in.nextInt();

                System.out.print("Please enter the number of layers: ");
                numLayer = in.nextInt();

                System.out.print(
                        "Please enter the number of nerons in a hidden layer: ");
                numNeuron = in.nextInt();

                break;
            } catch (Exception e) {
                System.out.println("Wrong format, please try again!");
            }

        } while (true);
    }

    private static void train() {
        System.out.print("Please enter the filename of the training data file: ");

        Scanner fScanner = readInputFile();

        int[][] input2D = processInputFile(fScanner);

        ArrayList<Layer> layers = ann.train_ANN(input2D);

        for (Layer layer : layers) {
            ArrayList<Perceptron> perceptrons = layer.getNodes();

            for (Perceptron perceptron : perceptrons) {
                ArrayList<Double> weights = perceptron.getWeights();
            }
        }

    }

    private static void classification() {
        System.out.print("Please enter the filename of the input data file: ");

        Scanner fScanner = readInputFile();

        int[][] input2D = processInputFile(fScanner);
    }

    private static Scanner readInputFile() {
        String filename;
        Scanner fScanner;

        do {
            filename = in.next();

            try {
                File f = new File(filename);
                fScanner = new Scanner(f);
                break;

            } catch (FileNotFoundException e) {
                System.out.println("File not found, please try again!");
                System.out.print("Filename: ");
            }
        } while (true);

        return fScanner;
    }

    private static int[][] processInputFile(Scanner fScanner) {
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
        return input2D;
    }

    private static void processConfigFile(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner configIn = new Scanner(f);

        String dataStr = configIn.nextLine();
        String[] dataArray = dataStr.split(",");

        numIN = Integer.parseInt(dataArray[0]);
        numOUT = Integer.parseInt(dataArray[1]);
        numLayer = Integer.parseInt(dataArray[2]);
        numNeuron = Integer.parseInt(dataArray[3]);
        maxSSE = Integer.parseInt(dataArray[4]);

    }
}
