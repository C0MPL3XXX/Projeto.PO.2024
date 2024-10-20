package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
  }

  @Override
  protected final void execute() {
    int seasonIndex = _receiver.getHotel().advanceSeason();

    _display.add(seasonIndex);
    _display.display();
  }
}
