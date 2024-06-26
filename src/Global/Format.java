package Global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Format {
    public static LocalDateTime formatDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

}
