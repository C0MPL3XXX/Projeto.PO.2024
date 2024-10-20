package hva.app.animal;


import hva.app.exception.DuplicateAnimalKeyException;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    if (_receiver.containsAnimal(animalId)) {
      throw new DuplicateAnimalKeyException(animalId);
    }

    String animalName = Form.requestString(Prompt.animalName());

    String speciesId = Form.requestString(Prompt.speciesKey());
    if (!_receiver.containsAnimal(speciesId)) {
      String speciesName = Form.requestString(Prompt.speciesName());
      _receiver.registerSpecies(speciesId, speciesName);
    }
    
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

    _receiver.registerAnimal(animalId, animalName, habitatId, speciesId);
  }
}
