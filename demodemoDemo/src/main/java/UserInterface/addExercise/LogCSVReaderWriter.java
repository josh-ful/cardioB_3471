package UserInterface.addExercise;

import Controller.UserController;
import FitnessCourse.Exercise;
import UserInformation.UserStorage;

import java.io.*;
import java.util.Iterator;

//TODO these shouldn't be static??
/**
 * this class contains functionality to read and to write from a
 * CSV file and use the information to include in our classes
 */
// RESOLVED no they should because otherwise they wouldn't be accessible
// it breaks if its not static

public class LogCSVReaderWriter {
    private static String fileName;
    /**
     * Constructs LogCSVReaderWriter object
     *
     * @param file to read in and to write to
     */
    public LogCSVReaderWriter(String file) {
        fileName = file;
    }

    public static void setFileName(String fileN) {
        LogCSVReaderWriter.fileName = fileN;
    }
    /**
     * reads CSV and updates exercise list
     *
     */
    public static void readCSV(){
        try {
            System.out.println("Reading file " + fileName);
            BufferedReader br = new BufferedReader( new FileReader(fileName));
            String line;
            UserController.clearExercises();

            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                String[] row = line.split(", ");
                UserController.enterExercise(row[0], row[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * writes to csv what is in the exercise list
     *
     */
    public static void writeCSV(){
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(fileName))) {
            Iterator<Exercise> iterator = UserStorage.getExercises().iterator();
            Exercise lastExercise;
            while(iterator.hasNext()){
                lastExercise = iterator.next();
                if( lastExercise != null){
                    br.write(lastExercise.getName() + ","
                            + lastExercise.getDescription() + "\n");
                    System.out.println(lastExercise.getName() + "," + lastExercise.getDescription());
                }
            }
            br.flush();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * adds exercise to file
     *
     * @param name of exercise
     * @param description of exercise
     */
    public static void addExercise(String name, String description) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            br.write(name + "," + description + '\n');
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
    /**
     * converts set of exercises to 2D matrix for conversion to table
     *
     * @return matrix of exercises by name and description
     */
    public static String[][] setToMatrix(){
        int i= 0;
        String [][] matrix = new String[UserStorage.getExercises().size()][2];
        for(Exercise e : UserStorage.getExercises()){
            matrix[i][0] = e.getName();
            //System.out.println(matrix[i][0]);
            matrix[i][1] = e.getDescription();
            // System.out.println(matrix[i][1]);
            i++;
        }
        return matrix;
    }
}
