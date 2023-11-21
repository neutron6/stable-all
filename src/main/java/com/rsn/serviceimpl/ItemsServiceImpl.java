package com.rsn.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsn.exception.ItemNotFoundException;
import com.rsn.model.EmployeeBankData;
import com.rsn.model.Items;
import com.rsn.repository.EmployeeBankDataRepo;
import com.rsn.repository.ItemsRepo;
import com.rsn.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsRepo itemsRepo;

	@Autowired
	private EmployeeBankDataRepo employeeBankDataRepo;

	@Override
	public List<Items> createItems(List<Items> items) {
		return itemsRepo.saveAll(items);
	}

	@Override
	public Items buyItems(String itemName, String quantity, Integer bankId) {

		Optional<EmployeeBankData> employee = employeeBankDataRepo.findById(bankId);
		EmployeeBankData employeeBankData = employee.get();

		String moneyX = employeeBankData.getBankBalance();
		Integer payment = Integer.parseInt(moneyX); 

		Optional<Items> iOptional = itemsRepo.findByItemName(itemName);
		Items items = iOptional.get();
		Integer price = Integer.parseInt(items.getItemPrice());
		Integer quantityX = Integer.parseInt(quantity);
		if (iOptional.isPresent()) {
			Integer buy = payment - (price * quantityX);
			String bankBalance = buy.toString();
			employeeBankData.setBankBalance(bankBalance);
			employeeBankDataRepo.save(employeeBankData);
			return itemsRepo.save(items);
		}
		return null;
	}

	public String getItem(String itemName) throws ItemNotFoundException {
		Optional<Items> iteOptional = itemsRepo.findByItemName(itemName);
		if (iteOptional.isPresent()) {
			return iteOptional.get().getItemName();
		} else {
			throw new ItemNotFoundException();
		}
	}

	public String getItemPrice(String itemName) throws ItemNotFoundException {
		Optional<Items> iteOptional = itemsRepo.findByItemName(itemName);
		if (iteOptional.isPresent()) {
			return iteOptional.get().getItemPrice();
		} else {
			throw new ItemNotFoundException();
		}
	}

}
