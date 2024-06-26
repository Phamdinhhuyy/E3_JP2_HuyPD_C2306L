import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Repository.BookingRepo;
import Repository.CustomerRepo;
import Repository.RoomRepo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice, input;
        int year;
        boolean flag = false;
        RoomType roomType;
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("RS001", RoomType.SI,8));
        rooms.add(new Room("RS002", RoomType.SI,8));
        rooms.add(new Room("RS003", RoomType.SI,8));
        rooms.add(new Room("RD001", RoomType.DO,12));
        rooms.add(new Room("RD002", RoomType.DO,12));
        rooms.add(new Room("RQ002", RoomType.QE,35));
        rooms.add(new Room("RT001", RoomType.TR,12.5));
        rooms.add(new Room("RT002", RoomType.TR,12.5));
        rooms.add(new Room("RQ001", RoomType.QU,20.5));
        rooms.add(new Room("RQ002", RoomType.QU,20.5));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("001", "Mr.Linus Tovaldo", "84125325346457"));
        customers.add(new Customer("002", "Mr.Bill", "91124235346467"));
        customers.add(new Customer("003", "Mr.Turing", "911423534646"));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking(1, rooms.get(5), customers.get(0), LocalDateTime.of(2023,3,15, 9,30,15),LocalDateTime.of(2023,3,16,12,30,45)));
        bookings.add(new Booking(2, rooms.get(2), customers.get(1), LocalDateTime.of(2023,6,9 ,19 ,30 ,25),LocalDateTime.of(2023,6,10,11,25,15)));
        bookings.add(new Booking(3, rooms.get(0), customers.get(2), LocalDateTime.of(2023, 3, 11, 10, 10, 5),LocalDateTime.of(2023,3,13,11,5,10)));
        bookings.add(new Booking(4, rooms.get(3), customers.get(1), LocalDateTime.of(2023, 11, 11, 11 ,11, 15),LocalDateTime.of(2023,11,13,11,15,15)));
        bookings.add(new Booking(5, rooms.get(1), customers.get(2), LocalDateTime.of(2023, 10, 25, 9, 20,25),LocalDateTime.of(2023,10,26,12,25,30)));
        bookings.add(new Booking(6, rooms.get(4), customers.get(2), LocalDateTime.of(2023,8,18, 18, 25, 35),LocalDateTime.of(2023,8,19,11,35,20)));
        bookings.add(new Booking(7, rooms.get(9), customers.get(2), LocalDateTime.of(2023,9,18, 18, 25, 35),LocalDateTime.of(2023,10,19,11,35,20)));

        BookingRepo bookingRepo = new BookingRepo();
        RoomRepo roomRepo = new RoomRepo();
        RoomRepo.rooms = rooms;
        CustomerRepo customerRepo = new CustomerRepo();
        CustomerRepo.customers = customers;

        BookingRepo.bookings =  bookings;
        BookingRepo.rooms = rooms ;
        BookingRepo.customers = customers;
        BookingRepo.roomRepo = roomRepo;
        BookingRepo.customerRepo = customerRepo;

        do{
            try{
                System.out.println("1. Booking Room");
                System.out.println("2. Insert Information");
                System.out.println("3. Revenue statistics by room");
                System.out.println("4. Revenue statistics by year 2023");
                System.out.println("5. Exit");
                System.out.print("You choice: ");
                choice = br.readLine();
                switch (choice){
                    case "1":
                        System.out.println("1. Booking Room");
                        System.out.println(bookingRepo.inputBooking());
                        break;
                    case "2":
                        System.out.println("2. Insert Information");
                        System.out.print("Input Data: ");
                        input = br.readLine();
                        if(!bookingRepo.getBookingByCustomerName(input).isEmpty()){
                            bookingRepo.getBookingByCustomerName(input).forEach(System.out::println);
                        }else if(!bookingRepo.getBookingByCustomerPhone(input).isEmpty()){
                            bookingRepo.getBookingByCustomerPhone(input).forEach(System.out::println);
                        }else if(!bookingRepo.getBookingByRoomId(input).isEmpty()){
                            bookingRepo.getBookingByRoomId(input).forEach(System.out::println);
                        }else {
                            System.out.println("Invalid input");
                        }
                        break;
                    case "3":
                        System.out.println("3. Revenue statistics by room");
                           bookingRepo.RevenueByRoomType().forEach(System.out::println);
                        break;
                    case "4":
                        System.out.println("4. Revenue statistics by year 2023");
                        System.out.print("Input Year: ");
                        year = Integer.parseInt(br.readLine());
                        System.out.println(bookingRepo.roomMaxRevenue2023(year));
                        break;
                    case "5":
                        System.out.println("Exit..........");
                        flag = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (!flag);






    }
}