package main.java.org.Service;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

abstract class FileProcessor {

    Object loadFile(final String path) throws FileNotFoundException {
        XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(path)));
        Object result = d.readObject();
        d.close();
        return result;
    }

    void saveFile(final String path, Object obj) throws IOException {

        XMLEncoder e = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(path)));
        e.writeObject(obj);
        e.close();
    }
}
