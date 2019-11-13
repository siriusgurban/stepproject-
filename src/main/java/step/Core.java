package step;

import step.controller.BookingController;
import step.controller.MainController;
import step.controller.TimetableController;
import step.io.Command;
import step.io.Parser;

public class Core {

  private final Console console;
  private final Menu menu;
  private final Database database;
  private final Parser parser;
  private final BookingController bookingController;
  private final TimetableController timetableController;
  private final MainController mainController;

  public Core(Console console, Database database) {
    this.console = console;
    this.database = database;
    this.menu = new Menu();
    this.parser = new Parser();
    this.timetableController = new TimetableController();
    this.bookingController = new BookingController();
    this.mainController = new MainController();
  }

  public void run() {
    if (!database.isExisted()) {
      database.createInitialData();
    }
    boolean cont = true;
    console.printLn(menu.show());
    while (cont) {

      String line = console.readLn();
      Command user_input = parser.parse(line);
      switch (user_input) {
        case TIMETABLE_SHOW:
          timetableController.show();
          break;
        case BOOKING_ADD:
          bookingController.add();
          break;
        case BOOKING_REMOVE:
          bookingController.remove();
          break;
        case BOOKING_SHOW:
          bookingController.show();
          break;
        case EXIT:
          cont = false;
          break;
        case HELP:
        default:
          console.printLn(mainController.help());
          break;
      }
    }
  }
}
