package com.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.DatePrice;
import com.demo.entities.Hotel;
import com.demo.entities.Role;
import com.demo.entities.Room;
import com.demo.repository.AreaRepository;
import com.demo.repository.CategoryRepository;
import com.demo.repository.DatePriceReponsitory;
import com.demo.repository.HotelRepository;
import com.demo.repository.RoleRepository;
import com.demo.repository.RoomRepository;

@Service("roomService")
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private DatePriceReponsitory datePriceReponsitory;

	@Override
	public boolean delete(int id) {
		try {
			roomRepository.delete(roomRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean edit(Room room) {
		try {
			roomRepository.save(room);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Room findByIdRoom(int id) {
		// TODO Auto-generated method stub
		return roomRepository.findById(id).get();
	}
	
	@Override
	public List<Room> listById(int id) {
		// TODO Auto-generated method stub
		return roomRepository.listById(id);
	}

	@Override
	public List<Room> listAll() {
		// TODO Auto-generated method stub
		return roomRepository.findAllbyIdDesc();
	}

	@Override
	public List<Room> searchByPrice(int price1, int price2) {
		List<DatePrice> datePrices = datePriceReponsitory.searchByPrice(price1, price2);
		List<Room> rooms = new ArrayList<>();
		for (DatePrice datePrice : datePrices) {
			List<Room> ros = roomRepository.listById(datePrice.getRoom().getId());
			for (Room room : ros) {
				rooms.add(room);
			}
		}
		return rooms;
	}
	
	@Override
	public List<Room> searchByDate(int date1, int date2) {
		List<DatePrice> datePrices = datePriceReponsitory.searchBydate(date1, date2);
		List<Room> rooms = new ArrayList<>();
		for (DatePrice datePrice : datePrices) {
			List<Room> ros = roomRepository.listById(datePrice.getRoom().getId());
			for (Room room : ros) {
				rooms.add(room);
			}
		}
		return rooms;
	}
	
	//owner
	@Override
	public Iterable<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public Room find(int id) {
		return roomRepository.findById(id).get();
	}

	@Override
	public boolean save(Room room) {
		try {
			roomRepository.save(room);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Room room) {
		try {
			roomRepository.delete(room);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	@Override
	public Room created(Room room) {
		try {
			roomRepository.save(room);
			return room;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Room> takeByDate(Date from, Date to) {
		return roomRepository.takeByDate(from, to);
	}
	
	

	@Override
	public int sum_fixed() {
		try {
			return roomRepository.findByfixed_quantity();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}


	@Override
	public int sumDetails_fixed(int id) {
		try {
			return roomRepository.findById_fixedRoom(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}


	@Override
	public Room saveId(Room room) {
		try {
			return roomRepository.save(room);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Room find2(int idRoom) {
		return roomRepository.findByIdRoom2(idRoom);
	}
	
	@Override
	public List<Room> findByContent(String content) {
		return roomRepository.findByName(content);
	}

	@Override
	public List<Room> findAllByIdRoleAccount(int id) {
		return roomRepository.findAllByIdRoleAccount(id);
	}

	@Override
	public List<Room> findByIdRoomHotel(int id) {
		try {
			return roomRepository.findByIdDeltaiHotel(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@Async
	public List<Room> findRoomByIdHotel(int id_hotel) {
		return roomRepository.findRoomByIdHotel(id_hotel);
	}

	@Override
	@Async
	public int findMinPriceHotel(int id_room) {
		return roomRepository.findMinPriceHotel(id_room);
	}
	@Override
	@Async
	public int findMinPriceHotelByIdHotel(int id_hotel) {
	    List<Room> rooms = roomRepository.findRoomByIdHotel(id_hotel);
	    var wrapper = new Object(){ int min_price = 1000; };
	    if(rooms!=null){
	    	 rooms.forEach(room -> {
	 	        int now_price = roomRepository.findMinPriceHotel(room.getId());
	 	        if(wrapper.min_price>now_price) {
	 	        	wrapper.min_price = now_price;
	 	        }      
	 	    });
	    }
	    return wrapper.min_price;
	}

	@Override
	@Async
	public List<Room> getListRoomByIdHotel(int id_hotel) {
		return roomRepository.getListRoomByIdHotel(id_hotel);
	}

	@Override
	public int checkAvailableByDate(int id_room, Date from, Date to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countQuantityRoomByIdHotel(int id_hotel) {
		return roomRepository.countQuantityRoomByIdHotel(id_hotel);
	}

	@Override
	public Room findRoomByIdRoom(int id_room) {
		return roomRepository.findRoomByIdRoom(id_room);
	}

	@Override
	public Room findFirstRoom() {
		return roomRepository.findFirstRoom();
	}
	

}
