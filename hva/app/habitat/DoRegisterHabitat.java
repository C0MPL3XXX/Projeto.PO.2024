package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    if (_receiver.containsHabitat(habitatId)) {
      throw new DuplicateHabitatKeyException(habitatId);
    }

    String habitatName = Form.requestString(Prompt.habitatName());
    int habitatArea = Form.requestInteger(Prompt.habitatArea());

    _receiver.registerHabitat(habitatId, habitatName, habitatArea);
  }
}
