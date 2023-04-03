package com.demo.service;

import com.demo.model.EmployeeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class KafkaService  {

	@Value("${topic.small.amount}")
	private String smallTopic ;

	@Value("${topic.big.amount}")
	private String bigTopic ;

	@Autowired
	FeesService feesService;

	@Autowired
	CSVFileService csvFileService;

	@Autowired
	private KafkaTemplate<String, EmployeeModel> kafkaTemplate;

	public void streamDataFile(List<EmployeeModel> emps ) {
		for (EmployeeModel emp : emps){
			if (emp.getAmount() < 1000) {
				feesService.calcBellowFees(emp);
				sendMessage(smallTopic, emp);
			}
			else {
				feesService.calcBigFees(emp);
				sendMessage(bigTopic, emp);
			}
		}
	}

	private void sendMessage(String topicName , EmployeeModel employeeModel) {
		kafkaTemplate.send(topicName, employeeModel);
	}

	@KafkaListener(topics = "smallTopic" ,containerFactory = "kafkaListener")
	public void listenSmallTopic(EmployeeModel employeeModel) throws Exception {
		log.info("Received" + employeeModel + "from kafka smallTopic");
		csvFileService.generateFile("small-amount-employees.csv", employeeModel);
	}

	@KafkaListener(topics = "bigTopic" ,containerFactory = "kafkaListener")
	public void listenBigTopic(EmployeeModel employeeModel) throws Exception {
		log.info("Received" + employeeModel + "from kafka bigTopic");
		csvFileService.generateFile("big-amount-employees.csv", employeeModel);
	}
}
