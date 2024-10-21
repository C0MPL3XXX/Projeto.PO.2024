package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() {
    for(Vaccine v: _receiver.getVaccines()){
      _display.add(v.toString());
    }
    _display.display();
  }
}
