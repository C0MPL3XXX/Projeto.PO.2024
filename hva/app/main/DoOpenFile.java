package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    if (_receiver.changed()) {
      Prompt.saveBeforeExit();
      boolean save = Form.confirm("Save pending changes? ");

      if (save) {
        new DoSaveFile(_receiver).execute();
      } else {
        return;
      }
    }

    try {
      _receiver.load(Form.requestString(Prompt.openFile()));
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
    
  }
}
