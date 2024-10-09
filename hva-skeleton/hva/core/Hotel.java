package hva.core;

import hva.core.exception.*;
import java.io.*;

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // FIXME define attributes
    private Season season;

    // FIXME define contructor(s)
    public Hotel() {
    }

    // FIXME define more methods
    public void addAnimal(String idA, String name, String idHab, String idSpe) {

    }

    /**
     * Read text input file and create corresponding domain entities.
     *
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException if there is an IO erro while processing the text file
   *
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
        //FIXME implement method
    }
}
