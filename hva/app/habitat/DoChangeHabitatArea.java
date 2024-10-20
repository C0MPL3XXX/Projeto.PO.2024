package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    if (!_receiver.containsHabitat(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }

    int habitatArea = Form.requestInteger(Prompt.habitatArea());

    _receiver.setHabitatArea(habitatId, habitatArea);
  }
}
