package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.forms.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
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

    _receiver.reset();
  }
}