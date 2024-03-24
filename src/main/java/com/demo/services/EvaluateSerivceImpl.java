package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Evaluate;
import com.demo.repository.EvalueteReponsitory;

@Service("evaluateService")
public class EvaluateSerivceImpl implements EvaluateService{
	@Autowired
	private EvalueteReponsitory evalueteReponsitory;

	@Override
	public List<Evaluate> findAllByIdDesc(int status, int idAcc) {
		return evalueteReponsitory.findAllByIdDesc(status, idAcc);
	}
	@Override
	// Thái
	@Async
	public List<Evaluate> getAllEvaluateByIdHotel(int id_hotel) {
		return evalueteReponsitory.getAllEvaluateByIdHotel(id_hotel);
	}
	@Override
	@Async
	public boolean save(Evaluate evaluate) {
		try {
			evalueteReponsitory.save(evaluate);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	@Override
	@Async
	public int countEvaluateByIdAccount(int id_account) {
		return evalueteReponsitory.countEvaluateByIdAccount(id_account);
	}
	@Override
	public List<Evaluate> listAll() {
		// TODO Auto-generated method stub
		return evalueteReponsitory.listAll();
	}
	@Override
	public Evaluate findById(int id) {
		
		return evalueteReponsitory.findById(id).get();
	}

	
}
