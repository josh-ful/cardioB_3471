package main.UserInterface.addExercise;

import main.Controller.UserController;
import main.FitnessCourse.Exercise;
import main.UserInformation.UserStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.Vector;

public class LogCSVReaderWriter {
    private static String fileName;
    public LogCSVReaderWriter(String file) {
        fileName = file;
    }
    public static void readCSV(){
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(LogCSVReaderWriter.class.getClassLoader().getResourceAsStream("testCreateExercise.csv")))) {
            String line;
            while((line = br.readLine()) != null){
                // System.out.println(line);
                String[] row = line.split(",");
                UserController.enterExercise(row[0],row[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeCSV(){
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(fileName))) {
            Iterator<Exercise> iterator = UserStorage.getExercises().iterator();
            Exercise lastExercise = null;
            while(iterator.hasNext()){
                lastExercise = iterator.next();
                if( lastExercise != null){
                    br.write(lastExercise.getName() + "," + lastExercise.getDescription() + "\n");
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

    public static JScrollPane logTable(){
        String[] columnNames = {"Name", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(setToMatrix(), columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));

        return scrollPane;
    }

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
