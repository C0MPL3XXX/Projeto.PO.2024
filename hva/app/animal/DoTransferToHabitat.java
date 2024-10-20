package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    if (!_receiver.containsAnimal(animalId)) {
      throw new UnknownAnimalKeyException(animalId);
    }

    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    if (!_receiver.containsHabitat(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }

    _receiver.transferAnimal(animalId, habitatId);
  }
}
