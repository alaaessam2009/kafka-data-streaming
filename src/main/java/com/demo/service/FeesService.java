package com.demo.service;

import com.demo.model.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FeesService {

	public void calcBellowFees(EmployeeModel employeeModel) {
		employeeModel.setFees(employeeModel.getAmount() * 0.1);
	}

	public void calcBigFees(EmployeeModel employeeModel) {
		double amountDollar = employeeModel.getAmount() / 20 ;
		employeeModel.setAmount(amountDollar);
		employeeModel.setFees(amountDollar * 0.2);
	}
}
