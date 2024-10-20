package hva.app.search;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    //FIXME implement command
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    if(!_receiver.containsHabitat(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }
    _display.addAll(_receiver.getAnimalsInHabitat(habitatId));
    _display.display();
  }
}
