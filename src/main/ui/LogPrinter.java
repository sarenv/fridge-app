package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Prints the event log once the JFrame closes

public class LogPrinter extends WindowAdapter {
    @Override
    // EFFECTS: Prints the logs in EventLog.
    public void windowClosing(WindowEvent e) {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }
}
