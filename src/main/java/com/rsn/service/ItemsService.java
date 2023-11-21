package com.rsn.service;

import java.util.List;

import com.rsn.model.Items;

public interface ItemsService {

	List<Items> createItems(List<Items> items);

	Items buyItems(String itemsName, String quantity, Integer bankId);
}
