/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: Jingya Wu, Haoyu Xiong
  * Date: Mar 3, 2017
  * Time: 8:54:17 PM *
  * Project: csci205_hw
  * Package: Assignment01
  * File: ANN_Client
  * Description:
  *
  * ****************************************
 */

 /*
 * ANN: line 103, -actual output?
 *
 */
package hw02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Client side class from displaying the menu, interact with user, and process
 * input/output file information.
 *
 * @author haoyuxiong
 */
public class ANN_Client {

    static Scanner in;
    static boolean firstRound = true;
    static boolean inTrain = false;
    static int numIN;
    static int numOUT;
    static int numLayer;
    static int numNeuron;
    static double maxSSE;
    static int maxEpoch;
    static ANN ann;

    /**
     * Main program for interacting with user and get user specified choices.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException {

        in = new Scanner(System.in);

        System.out.println("Please choose between the following options:");
        System.out.println("\t1. Create a new ANN");
        System.out.println("\t2. Read in a config file");

        int mode = 0;

        int option = oneOrTwo();

        // Create new ANN
        if (option == 1) {

            askInAndOutNum();

            ann = new ANN(numIN, numOUT, numLayer, numNeuron, maxSSE, maxEpoch);
        }

        // Read in config file
        else {

            askConfigFile();

        }

        do {

            mode = askMode();

            // For training
            if (mode == 1) {
                inTrain = true;
                train();
            }

            // For classification
            else if (mode == 2) {
                if (!(inTrain || option == 2)) {
                    askConfigFile();
                }
                classify();
            }

            // Quit program
            else {
                System.out.println("Bye!");
                break;
            }

            firstRound = false;

        } while (true);
    }

    /**
     * Ask the user whether they want to train or classify, and return the
     * choice.
     *
     * @return int representing the mode chosen by user
     */
    private static int askMode() {

        int mode;

        System.out.println("Please choose between the following modes: ");
        System.out.println("\t1. training");
        System.out.println("\t2. classifying");

        if (!firstRound) {
            System.out.println("\t3. Quit");
            mode = oneTwoOrThree();
        }

        else {
            mode = oneOrTwo();
        }

        return mode;
    }

    /**
     * Test if the input is either 1 or 2, prompt error message and keep asking
     * until get the correct format input.
     *
     * @return int option of either 1 or 2
     */
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

    /**
     * Test if the input is either 1, 2, or 3, prompt error message and keep
     * asking until get the correct format input.
     *
     * @return int option of 1, 2, or 3
     */
    private static int oneTwoOrThree() {

        int option;

        do {
            System.out.print("Enter 1, 2, or 3: ");

            if (in.hasNextInt()) {
                option = in.nextInt();

                if (option == 1 || option == 2 || option == 3) {
                    break;
                }
            }
            System.out.println("Invalid input, please try again!");

        } while (true);

        return option;
    }

    /**
     * Ask the user for number of inputs, outputs, hidden layers, neurons per
     * hidden layer, and the maximum SSE.
     *
     */
    private static void askInAndOutNum() {

        do {
            try {
                System.out.print("Please enter the number of inputs: ");
                numIN = in.nextInt();

                System.out.print("Please enter the number of outputs: ");
                numOUT = in.nextInt();

                System.out.print("Please enter the number of hidden layers: ");
                numLayer = in.nextInt() + 2;

                System.out.print(
                        "Please enter the number of nerons in a hidden layer: ");
                numNeuron = in.nextInt();

                System.out.print("Please enter the maximum SSE: ");
                maxSSE = in.nextDouble();

                System.out.print("Please enter the maximum number of epoch: ");
                maxEpoch = in.nextInt();

                break;
            } catch (Exception e) {
                System.out.println("Wrong format, please try again!");
            }

        } while (true);
    }

    /**
     * Ask for the configuration file to take in, and call processConfigFile()
     * to process and set the properties and weights.
     */
    private static void askConfigFile() {
        do {
            System.out.print(
                    "Please enter the filename of the configuration file: ");
            String configFilename = in.next();

            try {
                processConfigFile(configFilename);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not found, try again!");
            }
        } while (true);
    }

    /**
     * The major function for train, calls the train_ANN method and other helper
     * functions.
     */
    private static void train() throws FileNotFoundException {

        System.out.print("Please enter the filename of the training data file: ");

        Scanner fScanner = readInputFile();

        double[][] input2D = processInputFile(fScanner);

        ArrayList<SUB_ANN> subANNList = ann.Train_ANN(input2D);

        System.out.println(
                "Would you like to save the network configuration to a configuration file? ");
        System.out.println("1. yes");
        System.out.println("2. no");

        int save = oneOrTwo();

        if (save == 1) {
            generateConfigFile(ann);
        }

    }

    /**
     * The major function for classify, calls the classify_ANN method and other
     * helper functions.
     */
    private static void classify() {

        System.out.print("Please enter the filename of the input data file: ");

        Scanner fScanner = readInputFile();

        double[][] input2D = processInputFile(fScanner);

        double[][] test2D = processTestFile(input2D);

        double[][] output2D = ann.Classify_ANN(test2D);

        System.out.print(
                "Please enter the filename that the output should be saved into (including file extension): ");
        String outputName = in.next();
        PrintWriter out;

        do {
            try {
                out = new PrintWriter(outputName);
                break;
            } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException");
            }
        } while (true);

        for (int i = 0; i < output2D.length; i++) {
            for (int j = 0; j < output2D[i].length; j++) {
                if (j != 0) {
                    System.out.print(", ");
                    out.print(", ");
                }
                System.out.printf("%.2f", output2D[i][j]);
                out.printf("%.2f", output2D[i][j]);
            }
            System.out.println();
            out.println();
        }
        out.close();
    }

    /**
     * Read input file and handles exceptions.
     *
     * @return Scanner Scanner from the input file.
     */
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

    /**
     * Process input file, and store the information in a 2D array.
     *
     * @param fScanner
     * @return double[][] 2D array containing the input data
     */
    private static double[][] processInputFile(Scanner fScanner) {

        System.out.println("Does input file contain a header? ");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");

        int header = oneOrTwo();

        if (header == 1) {
            fScanner.nextLine();
        }

        ArrayList<double[]> inputArray2D = new ArrayList<double[]>();

        while (fScanner.hasNextLine()) {
            String line = fScanner.nextLine();
            String[] parts = line.split(",");
            double[] ints = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                ints[i] = Double.parseDouble(parts[i]);
            }
            inputArray2D.add(ints);
        }

        double[][] input2D = new double[inputArray2D.size()][];

        for (int i = 0; i < inputArray2D.size(); i++) {
            input2D[i] = inputArray2D.get(i);
        }
        return input2D;
    }

    private static double[][] processTestFile(double[][] input2D) {

        if (input2D[0].length > numIN) {
            double[][] test2D = new double[input2D.length][];
            for (int i = 0; i < input2D.length; i++) {
                test2D[i] = Arrays.copyOfRange(input2D[i], 0, numIN);
            }
            return test2D;
        }
        else {
            return input2D;
        }
    }

    /**
     * Process configuration file and set the properties and weights.
     *
     * @param filename
     * @throws FileNotFoundException
     */
    private static void processConfigFile(String filename) throws FileNotFoundException {
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream configIn = new ObjectInputStream(f);
            ann = (ANN) configIn.readObject();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        /*
        File f = new File(filename);
        Scanner configIn = new Scanner(f);
        String dataStr = configIn.nextLine();
        String[] dataArray = dataStr.split(",");
        numIN = Integer.parseInt(dataArray[0]);
        numOUT = Integer.parseInt(dataArray[1]);
        numLayer = Integer.parseInt(dataArray[2]);
        numNeuron = Integer.parseInt(dataArray[3]);
        maxSSE = Double.parseDouble(dataArray[4]);
        ann = new ANN(numIN, numOUT, numLayer, numNeuron, maxSSE);
        for (SUB_ANN subANN : ann.getSubANNList()) {
        for (HiddenLayer hLayer : subANN.getHiddenLayerList()) {
        for (Perceptron perceptron : hLayer.getNodes()) {
        String line = configIn.nextLine();
        String[] lineArr = line.split(",");
        ArrayList<Double> weights = new ArrayList<Double>();
        double theta = Double.parseDouble(lineArr[0]);
        perceptron.setTheta(theta);
        for (int i = 1; i < lineArr.length; i++) {
        Double w = Double.parseDouble(lineArr[i]);
        weights.add(w);
        }
        perceptron.setWeights(weights);
        }
        }
        for (Perceptron perceptron : subANN.getOutputLayer().getNodes()) {
        String line = configIn.nextLine();
        String[] lineArr = line.split(",");
        ArrayList<Double> weights = new ArrayList<Double>();
        double theta = Double.parseDouble(lineArr[0]);
        perceptron.setTheta(theta);
        for (int i = 1; i < lineArr.length; i++) {
        Double w = Double.parseDouble(lineArr[i]);
        weights.add(w);
        }
        perceptron.setWeights(weights);
        }
        }

        File f = new File(filename);
        Scanner configIn = new Scanner(f);

        String dataStr = configIn.nextLine();
        String[] dataArray = dataStr.split(",");

        numIN = Integer.parseInt(dataArray[0]);
        numOUT = Integer.parseInt(dataArray[1]);
        numLayer = Integer.parseInt(dataArray[2]);
        numNeuron = Integer.parseInt(dataArray[3]);
        maxSSE = Double.parseDouble(dataArray[4]);

        ann = new ANN(numIN, numOUT, numLayer, numNeuron, maxSSE);

        for (SUB_ANN subANN : ann.getSubANNList()) {

            for (HiddenLayer hLayer : subANN.getHiddenLayerList()) {

                for (Perceptron perceptron : hLayer.getNodes()) {

                    String line = configIn.nextLine();
                    String[] lineArr = line.split(",");
                    ArrayList<Double> weights = new ArrayList<Double>();

                    double theta = Double.parseDouble(lineArr[0]);
                    perceptron.setTheta(theta);

                    for (int i = 1; i < lineArr.length; i++) {
                        Double w = Double.parseDouble(lineArr[i]);
                        weights.add(w);
                    }
                    perceptron.setWeights(weights);
                }
            }

            for (Perceptron perceptron : subANN.getOutputLayer().getNodes()) {

                String line = configIn.nextLine();
                String[] lineArr = line.split(",");
                ArrayList<Double> weights = new ArrayList<Double>();

                double theta = Double.parseDouble(lineArr[0]);
                perceptron.setTheta(theta);

                for (int i = 1; i < lineArr.length; i++) {
                    Double w = Double.parseDouble(lineArr[i]);
                    weights.add(w);
                }
                perceptron.setWeights(weights);
            }
        }
         */
    }

    /**
     * Generate configuration file from trained ANN.
     *
     * @param subANNList
     * @throws FileNotFoundException
     */
    private static void generateConfigFile(ANN ann) {

        System.out.print("Enter the desired name for the configuration file: ");
        String configFilename = in.next();

        try {
            FileOutputStream f = new FileOutputStream(configFilename);
            ObjectOutputStream configOut = new ObjectOutputStream(f);

            configOut.writeObject(ann);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        /*
        PrintWriter out = new PrintWriter(configFilename);
        out.printf("%d,%d,%d,%d,%f\n", numIN, numOUT, numLayer, numNeuron,
        maxSSE);
        for (SUB_ANN subANN : subANNList) {
        ArrayList<HiddenLayer> layers = subANN.getHiddenLayerList();
        for (Layer layer : layers) {
        ArrayList<Perceptron> perceptrons = layer.getNodes();
        for (Perceptron perceptron : perceptrons) {
        ArrayList<Double> weights = perceptron.getWeights();
        out.printf("%f,", perceptron.getTheta());
        for (int i = 0; i < weights.size() - 1; i++) {
        out.printf("%f,", weights.get(i));
        }
        out.printf("%f\n", weights.get(weights.size() - 1));
        }
        }
        OutputLayer oLayer = subANN.getOutputLayer();
        ArrayList<Perceptron> perceptrons = oLayer.getNodes();
        for (Perceptron perceptron : perceptrons) {
        ArrayList<Double> weights = perceptron.getWeights();
        out.printf("%f,", perceptron.getTheta());
        for (int i = 0; i < weights.size() - 1; i++) {
        out.printf("%f,", weights.get(i));
        }
        out.printf("%f\n", weights.get(weights.size() - 1));
        }
        }
        out.close();

        PrintWriter out = new PrintWriter(configFilename);
        out.printf("%d,%d,%d,%d,%f\n", numIN, numOUT, numLayer, numNeuron,
                   maxSSE);

        for (SUB_ANN subANN : subANNList) {
            ArrayList<HiddenLayer> layers = subANN.getHiddenLayerList();

            for (Layer layer : layers) {
                ArrayList<Perceptron> perceptrons = layer.getNodes();

                for (Perceptron perceptron : perceptrons) {
                    ArrayList<Double> weights = perceptron.getWeights();

                    out.printf("%f,", perceptron.getTheta());

                    for (int i = 0; i < weights.size() - 1; i++) {
                        out.printf("%f,", weights.get(i));
                    }

                    out.printf("%f\n", weights.get(weights.size() - 1));
                }
            }

            OutputLayer oLayer = subANN.getOutputLayer();
            ArrayList<Perceptron> perceptrons = oLayer.getNodes();

            for (Perceptron perceptron : perceptrons) {
                ArrayList<Double> weights = perceptron.getWeights();

                out.printf("%f,", perceptron.getTheta());

                for (int i = 0; i < weights.size() - 1; i++) {
                    out.printf("%f,", weights.get(i));
                }

                out.printf("%f\n", weights.get(weights.size() - 1));
            }
        }

        out.close();
         */
    }
}
