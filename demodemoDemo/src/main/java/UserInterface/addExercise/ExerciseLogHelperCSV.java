package UserInterface.addExercise;

import Controller.UserController;
import FitnessCourse.Exercise;
import UserInformation.UserStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ExerciseLogHelperCSV extends ExerciseLogHelper {
    protected static String fileName;

    public ExerciseLogHelperCSV() {
        super();
    }

    /**
     * Constructs LogCSVReaderWriter object
     *
     * @param file to read in and to write to
     */
    public ExerciseLogHelperCSV(String file) {
        fileName = file;
    }

    public static void setFileName(String file) {
        fileName = file;
    }

    /**
     * updates file according to UserStorage
     *
     *
     */
    public static void update() {
        try {

            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            for (Exercise e : UserStorage.getExercises()) {
                br.write(e.getName() + "," + e.getDescription() + '\n');
            }
            br.flush();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
    /**
     * reads CSV and updates exercise list
     *
     */
    public static ArrayList<Exercise> readCSV(){
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {

            // System.out.println("Reading file " + fileName);
            BufferedReader br = new BufferedReader( new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                String[] row = line.split(",");
                System.out.println(row[0]+","+row[1]);
                exercises.add(new Exercise(row[0], row[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exercises;
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


}
