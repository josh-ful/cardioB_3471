package UserInterface.addExercise;

import FitnessCourse.Exercise;
import UserInformation.CurrentUser;
import UserInformation.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * this class represents a ExerciseLogHelperCSV object
 * containing information about ExerciseLogHelperCSV
 */
public class ExerciseLogHelperCSV extends ExerciseLogHelper {
    protected static String fileName;
    private static final Logger logger = Logger.getLogger(ExerciseLogHelperCSV.class.getName());

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
            for (Exercise e : CurrentUser.getExercises()) {
                br.write(e.getName() + "," + e.getDescription() + '\n');
            }
            br.flush();
        }
        catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File not found");
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * reads CSV and updates exercise list
     *
     * @return ArrayList<Exercise> list of exercises to add to
     */
    public static ArrayList<Exercise> readCSV(){
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {

            // System.out.println("Reading file " + fileName);
            BufferedReader br = new BufferedReader( new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                exercises.add(new Exercise(row[0], row[1], 4));
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
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
            Iterator<Exercise> iterator = CurrentUser.getExercises().iterator();
            Exercise lastExercise;
            while(iterator.hasNext()){
                lastExercise = iterator.next();
                if( lastExercise != null){
                    br.write(lastExercise.getName() + ","
                            + lastExercise.getDescription() + "\n");
                }
            }
            br.flush();

        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
