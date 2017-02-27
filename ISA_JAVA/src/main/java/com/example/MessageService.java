package com.example;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

public interface MessageService {
	public Collection<SimpleMessage> findAll();
	public SimpleMessage create(SimpleMessage message);
	
	public SimpleMessage getMessageWithSomeId(Long id);
}
