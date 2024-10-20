package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    if (!_receiver.containsAnimal(animalId)) {
      throw new UnknownAnimalKeyException(animalId);
    }

    _display.add(_receiver.calculateAnimalSatisfaction(animalId));
    _display.display();
  }
}
