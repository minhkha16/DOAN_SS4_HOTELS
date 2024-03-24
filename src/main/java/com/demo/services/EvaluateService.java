package com.demo.services;

import java.util.List;


import com.demo.entities.Evaluate;
import com.demo.entities.Hotel;

public interface EvaluateService {
	public List<Evaluate> findAllByIdDesc(int status, int idAcc);
	
	public List<Evaluate> listAll();
	public Evaluate findById(int id);
	
	// Thái
	public List<Evaluate> getAllEvaluateByIdHotel(int id_hotel);
	public boolean save(Evaluate evaluate);
	public int countEvaluateByIdAccount(int id_account);
}
