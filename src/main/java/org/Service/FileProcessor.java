package main.java.org.Service;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * This class is is to Load/save the files. it encapsulates the load and save files.
 *
 * @author Maysam Mokarian
 * @version 1.0
 * @since 2017-02-08
 */
abstract class FileProcessor {

    /**
     * A method for loading a file
     *
     * @param path the path of the file to load
     * @return an object read from a file
     */
    static Object loadFile(final String path)  {
        XMLDecoder d = null;
        try {
            d = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            System.err.println("Could not load the file");
            return null;
            //e.printStackTrace();
        }
        Object result = d.readObject();
        d.close();
        return result;
    }

    /**
     * A method for saving an object
     *
     * @param path the path of the object to be saved
     * @param obj the object to be saved
     */
    void saveFile(final String path, Object obj)  {

        XMLEncoder e = null;
        try {
            e = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(path)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            System.err.println("Could not save the file");
        }
        e.writeObject(obj);
        e.close();
    }

    /**
     * A method for showing the objects saved in a folder
     *
     * @param path the path of the objects to be shown
     */
     void showItemNames(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file:listOfFiles){
            System.out.println(file.toString().split("/")[file.toString().split("/").length-1]);
        }
    }

    /**
     * This method is is to return  the file names. You may add prefix as well.
     *
     * @param path of the file
     * @param prefixName that can be added to the file name
     * @return filenames the filenames at the path given with the prefix
     */
    public ArrayList returnItemNames(String path, String prefixName) {
        ArrayList<String> fileArray = new ArrayList();;
    //    String fileNames="<html>",
        String fileName;
        int Counter=0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file:listOfFiles){
            Counter++;
            fileName=file.toString().split("\\\\")[file.toString().split("\\\\").length-1];
       //     fileNames += prefixName+Counter+" - "+fileName+"<br>";
            fileArray.add(fileName);
        }
     //   fileNames += "</html>";
        return fileArray;
    }




}
