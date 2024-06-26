package Repository;

import Entity.Room;
import Entity.RoomType;
import IGeneric.IGeneric;

import java.util.*;
import java.util.stream.Collectors;

public class RoomRepo implements IGeneric <Room> {
   public  static List<Room> rooms;
   public RoomRepo(){;}
    @Override
    public Room add() {
        return null;
    }

    @Override
    public Room delete(Room room) {
        return null;
    }

    @Override
    public Optional<Room> findById(String id) {
        return rooms.stream()
                .filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Room> getRoomByType(String type) {
       Set<String> ids = new HashSet<>();
        return rooms.stream()
                .filter(r -> r.getRoomType().getType().equals(type))
                .filter(r -> ids.add(r.getId()))
                .collect(Collectors.toList());
    }




}
