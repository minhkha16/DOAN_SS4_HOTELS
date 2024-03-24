package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Role;
import com.demo.repository.AreaRepository;
import com.demo.repository.CategoryRepository;
import com.demo.repository.HotelRepository;
import com.demo.repository.RoleRepository;

@Service("hotelService")
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	


	@Override
	public boolean delete(int id) {
		try {
			hotelRepository.delete(hotelRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean edit(Hotel hotel) {
		try {
			hotelRepository.save(hotel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Hotel findByIdHotel(int id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id).get();
	}

	@Override
	public List<Hotel> listAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAllbyIdDesc();
	}

	@Override
	public List<Hotel> search(String keyword) {
		// TODO Auto-generated method stub
		return hotelRepository.search(keyword);
	}

	
	//owner
	@Override
	public Iterable<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel find(int id) {
		return hotelRepository.findById(id).get();
	}

	@Override
	public boolean save(Hotel hotel) {
		try {
			hotelRepository.save(hotel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Hotel hotel) {
		try {
			hotelRepository.delete(hotel);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Hotel> findByName(String keyword) {
		return hotelRepository.findByName(keyword);
	}
	
	@Override
	public int countPaid(int id) {
		return hotelRepository.countPaid(id);
	}
	
	@Override
	public int countCancel(int id) {
		return hotelRepository.countCancel(id);
	}

	@Override
	public double sumTotalByHotel(int id) {
		try {
			return hotelRepository.findSumHotel(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Hotel> findAllByIdRoleAccount(int id, int status) {
		return hotelRepository.findAllAccount(id, status);
	}

	@Override
	public Long countHotel() {
		// TODO Auto-generated method stub
		return hotelRepository.countHotel();
	}
	
	// Thái
	@Override
	@Async
	public List<Hotel> findHotelByArea(int id_area) {
		return hotelRepository.findHotelByArea(id_area);
	}

	@Override
	@Async
	public double getAverageStarHotel(int id_hotel) {
		return hotelRepository.getAverageStarHotel(id_hotel);
	}

	@Override
	@Async
	public int getCountEvaluateHotel(int id_hotel) {
		return hotelRepository.getCountEvaluateHotel(id_hotel);
	}

	@Override
	@Async
	public List<Hotel> findHotelByNameAndIdArea(String name,int id_area) {
		return hotelRepository.findHotelByNameAndIdArea(name,id_area);
	}

	@Override
	@Async
	public Hotel findHotelByIdHotel(int id_hotel) {
		return hotelRepository.findHotelByIdHotel(id_hotel);
	}

	@Override
	@Async
	public List<Hotel> findAllHome() {
		return hotelRepository.findAllHome();
	}

}
