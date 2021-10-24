package ru.dataart.academy.java;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int inputItem;
        int counter = 0;
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath))){
            while(zin.getNextEntry()!=null){
                while((inputItem=zin.read())!=-1){
                    char inputChar = (char) inputItem;
                    if (inputChar == character) {
                        counter++;
                    }
                }
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return counter;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        int maxLength = 0;
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath))){
            while(zin.getNextEntry()!=null){
                Scanner file = new Scanner(zin);
                while (file.hasNext()) {
                    String line = file.nextLine();
                    String[] words = line.split("[\\h]");
                    for (String word : words) {
                        if (word.length() > maxLength) {
                            maxLength = word.length();
                        }
                    }
                }
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return maxLength;
    }
}
