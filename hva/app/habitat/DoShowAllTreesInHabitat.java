package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    if (!_receiver.containsHabitat(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }

    _display.addAll(_receiver.getTreesInHabitat(habitatId));
    _display.display();
  }
}
