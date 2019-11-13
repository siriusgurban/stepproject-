package step.service;

import step.SystemConsole;
import step.dao.DAOBooking;
import step.entity.Booking;
import step.entity.Flight;
import step.entity.Passenger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private final SystemConsole systemConsole;
    private final DAOBooking daoBooking;

    public BookingService() {
        this.systemConsole = new SystemConsole();
        this.daoBooking=new DAOBooking();
    }

    public void add(HashMap<Integer,Flight> booking) {
        int numOfTickets=0;
        Flight flight = null;
        List<Passenger> passengers = new ArrayList<>(0);

        for (Map.Entry<Integer,Flight> entry: booking.entrySet()){
            numOfTickets=entry.getKey();
            flight=entry.getValue();
        }
        for (int i = 0;i<numOfTickets;i++){
            systemConsole.printLn("Enter passengers name and surname with enter.");
            systemConsole.printLn("Start writing your name and surname ");
            String name = systemConsole.readLn();
            String surname = systemConsole.readLn();
            passengers.add(new Passenger(name,surname));
        }
        Booking booked = new Booking(flight,passengers, LocalDateTime.now(),passengers.get(0));
        daoBooking.put(booked);
    }

    public void delete(int booking_id) {
        daoBooking.delete(booking_id);
    }

    public void show(String name,String surname) {
        List<Booking> all = new DAOBooking().getAll();
        for (Booking booking : all){
            for (Passenger passenger: booking.getPassengers()){
                if (name.equalsIgnoreCase(passenger.getName()) && surname.equalsIgnoreCase(passenger.getSurname())){
                    systemConsole.printLn(booking.toString());
                }else{
                    systemConsole.printLn("There is no booking at your name");
                }
            }
        }
    }

    public void load() throws IOException {
        daoBooking.load();

    }

}
