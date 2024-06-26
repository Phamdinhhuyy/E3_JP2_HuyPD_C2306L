package Repository;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Global.Format;
import IGeneric.IGeneric;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BookingRepo implements IGeneric<Booking> {
    public static List<Booking> bookings;
    public static List<Room> rooms;
    public static List<Customer> customers;
    public static RoomRepo roomRepo;
    public static CustomerRepo customerRepo ;
    public BookingRepo(){;}

    public Booking inputBooking() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bookingId = 0;
        Room roomToInsert = null;
        Customer customerToInsert = null;
        String roomId, customerId, inputRoomType;
        List<Room> roomsToInsert;
        String checkinDateTime = "", checkoutDateTime = "";
        try{
            System.out.print("Enter Room Type: ");
            inputRoomType = br.readLine();
            roomsToInsert = roomRepo.getRoomByType(inputRoomType);
            roomsToInsert.forEach(System.out::println);
            System.out.println("Enter Booking ID: ");
            bookingId = Integer.parseInt(br.readLine());
            System.out.println("Enter Room ID: ");
            roomId = br.readLine();
            roomToInsert = roomRepo.findById(roomId).get();
            System.out.println("Enter Customer ID: ");
            customerId = br.readLine();
            customerToInsert = customerRepo.findById(customerId).get();
            System.out.println("Enter Check In DateTime");
            checkinDateTime = br.readLine();
            System.out.println("Enter Check Out DateTime");
            checkoutDateTime = br.readLine();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Booking(bookingId, roomToInsert, customerToInsert, Format.formatDate(checkinDateTime), Format.formatDate(checkoutDateTime));
    }

    @Override
    public Booking add() {
        Booking booking = inputBooking();
        bookings.add(booking);
        return booking;
    }

    @Override
    public Booking delete(Booking booking) {
        return null;
    }

    @Override
    public Optional<Booking> findById(String id) {
        return null;
    }

    /*public List<Booking> getBookings(String input){
        return bookings.stream()
                .filter(b -> b.getCustomer().getCusName().contains(input)
                || b.getCustomer().getCusPhone().equals(input)
                || b.getRoom().getId().equals(input))
                .toList();
    }*/

    public List<Map.Entry<Customer,List<Booking>>> getBookingByCustomerName(String customerName){
        return bookings.stream()
                .collect(Collectors.groupingBy(Booking::getCustomer))
                .entrySet().stream()
                .filter(bm -> bm.getKey().getCusName().contains(customerName))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Customer, List<Booking>>> getBookingByCustomerPhone(String customerPhone){
        return bookings.stream()
                .collect(Collectors.groupingBy(Booking::getCustomer))
                .entrySet().stream()
                .filter(bm -> bm.getKey().getCusPhone().equals(customerPhone))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Room, List<Booking>>> getBookingByRoomId(String roomId){
        return bookings.stream()
                .collect(Collectors.groupingBy(Booking::getRoom))
                .entrySet().stream()
                .filter(bm -> bm.getKey().getId().equals(roomId))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<RoomType, DoubleSummaryStatistics>> RevenueByRoomType() {
        return bookings.stream()
                .collect(Collectors.groupingBy(booking -> booking.getRoom().getRoomType(),
                        Collectors.summarizingDouble(Booking::getPrice)))
                .entrySet().stream().toList();
    }

    public Optional<Map.Entry<RoomType, DoubleSummaryStatistics>> roomMaxRevenue2023(int year) {
        return bookings.stream()
                .filter(b -> b.getCheck_out_datetime().getYear() == year)
                .collect(Collectors.groupingBy(booking -> booking.getRoom().getRoomType()
                ,Collectors.summarizingDouble(Booking::getPrice)))
                .entrySet().stream()
                .collect(Collectors.maxBy(Comparator.comparing(b -> b.getValue().getSum())));
    }














}
