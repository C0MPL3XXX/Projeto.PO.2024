package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.uilib.forms.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    if (!_receiver.changed()) {
      return;
    }

    try {
      _receiver.save();
    } catch (MissingFileAssociationException e) {
      String fileName = Form.requestString(Prompt.newSaveAs());
		  _receiver.setFilename(fileName);
      execute();
	  } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
	  }
  }
}
