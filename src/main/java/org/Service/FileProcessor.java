package main.java.org.Service;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 * This class is is to Load/save the files. it encapsulates the load and save files.
 + *
 + * @author Maysam Mokarian
 + * @version 1.0
 + * @since 2017-02-08
 */
abstract class FileProcessor {

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


     void showItemNames(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file:listOfFiles){
            System.out.println(file.toString().split("/")[file.toString().split("/").length-1]);
        }
    }

    public String returnItemNames(String path) {
        String fileNames="<html>",fileName;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file:listOfFiles){
            fileName=file.toString().split("\\\\")[file.toString().split("\\\\").length-1];
            fileNames += fileName+"<br>";
        }
        fileNames += "</html>";
        return fileNames;
    }



}
