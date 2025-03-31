package main.UserInterface.addExercise;

import main.Controller.UserController;
import main.FitnessCourse.Exercise;
import main.UserInformation.UserStorage;

import java.io.*;
import java.util.Iterator;

public class LogCSVReaderWriter {
    private static String fileName;

    public LogCSVReaderWriter(String file) {
        fileName = file;
    }

    public static void readCSV(){
        try {
            System.out.println("Reading file " + fileName);
            BufferedReader br = new BufferedReader( new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                String[] row = line.split(",");
                UserController.enterExercise(row[0], row[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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


    public static void addExercise(String name, String description) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            br.write(name + "," + description + '\n');

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
