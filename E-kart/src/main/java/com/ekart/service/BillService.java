package com.ekart.service;

import java.time.LocalDate;
import java.util.List;

import com.ekart.exception.BillException;
import com.ekart.exception.OrderException;
import com.ekart.model.Bill;

public interface BillService {

	public Bill addBill(Bill bill, int orderId) throws BillException, OrderException;
	
	public Bill viewBill(int billId)throws BillException;
	
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate)throws BillException;
	
	public Bill updateBill(Bill bill)throws BillException;
	
	public Bill removeBill(int billId)throws BillException;
	
}
