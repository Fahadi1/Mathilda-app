package fr.eql.al35.iservice;

import fr.eql.al35.entity.Command;

public interface KafkaIService {

	public String sendNewCommandToKafka(Command command);
}
