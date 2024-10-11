package hva.core;

import hva.core.exception.*;
import java.io.*;

// FIXME import classes
/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 *
 */
public class HotelManager {

    /**
     * The current zoo hotel
     */ // Should we initialize this field?
    private Hotel _hotel = new Hotel();
    private String _filename = "";

    /**
     * Saves the serialized application's state into the file associated to the
     * current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be
     * created or opened.
     * @throws MissingFileAssociationException if the current network does not
     * have a file.
     * @throws IOException if there is some error while serializing the state of
     * the network to disk.
     *
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
        if (_filename == null || _filename.equals("")) {
            throw new MissingFileAssociationException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_hotel);
            _hotel.setChanged(false);
        }
    }

    /**
     * Saves the serialized application's state into the specified file. The
     * current network is associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be
     * created or opened.
     * @throws MissingFileAssociationException if the current network does not
     * have a file.
     * @throws IOException if there is some error while serializing the state of
     * the network to disk.
     *
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
        _filename = filename;
        save();
    }

    /**
     * @param filename name of the file containing the serialized application's
     * state to load.
     * @throws UnavailableFileException if the specified file does not exist or
     * there is an error while processing this file.
     *
     */
    public void load(String filename) throws UnavailableFileException {
        // FIXME implement serialization method
        _filename = filename;
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            _hotel = (Hotel) ois.readObject();
            _hotel.setChanged(false);
        } catch (IOException e) {
            throw new UnavailableFileException(filename);
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * Read text input file and initializes the current zoo hotel (which should
     * be empty) with the domain entitiesi representeed in the import file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException if some error happens during the processing
     * of the import file.
     *
     */
    public void importFile(String filename) throws ImportFileException {
        try {
            _hotel.importFile(filename);
        } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(filename, e);
        }
    }

    /**
     * Returns the zoo hotel managed by this instance.
     *
     * @return the current zoo hotel
     *
     */
    public final Hotel getHotel() {
        return _hotel;
    }

    /**
     * Change the files name
     *
     * @param filename
     */
    public void setFilename(String filename) {
        _filename = filename;
    }

    /**
     * @return filename
     */
    public String getFilename() {
        return _filename;
    }

    /**
     * @return has changed
     */
    public boolean changed() {
        return _hotel.hasChanged();
    }

    /**
     * Reset the Hotel
     */
    public void reset() {
        _hotel = new Hotel();
        _filename = null;
    }

}
