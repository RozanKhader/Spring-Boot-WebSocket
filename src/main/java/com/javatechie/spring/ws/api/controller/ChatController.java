package com.javatechie.spring.ws.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.spring.ws.api.model.Command;
import com.javatechie.spring.ws.api.model.Task;
import com.javatechie.spring.ws.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.javatechie.spring.ws.api.model.ChatMessage;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
	@Autowired
	TaskRepository taskRepository;

	@MessageMapping("/hello")
	@SendTo("/topic/public")
	public String register(@Payload String chatMessage, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
		//headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		String sessionId = headerAccessor.getSessionId().toString();
		System.out.println(sessionId);
		//headerAccessor.setSessionId(sessionId);
		System.out.println(new ObjectMapper().writeValueAsString(getSync()));
		return chatMessage;
	}
	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		String sessionId = SimpAttributesContextHolder.currentAttributes().getSessionId();
		System.out.println(sessionId);
	}
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	public String getSync() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;

		Task task = new Task();
		List<String> taskList = new ArrayList<String>();
		List<String> errorTaskList = new ArrayList<String>();
		//long limit=  companyCredentialRepository.getCompanyCredentialByCompanyIDAndBranchId(TenantContext.getCurrentTenant(),TenantContext.getCurrentBranch()) ;
		//System.out.println(limit);
		// System.out.println(TenantContext.getCurrentTenant());
		// System.out.println(TenantContext.getCurrentDevice());
		// System.out.println(TenantContext.getCurrentBranch());
		List<Task> tasks = taskRepository.getUserId(23438, 2, 0, 1, 0, 500);
		long unixTime = System.currentTimeMillis() / 1000L;
		//System.out.println(tasks.size());
		if (!tasks.isEmpty()) {
			for (int i = 0; i <= tasks.size() - 1; i++) {
				Command cmd = new Command(tasks.get(i).getAk(), tasks.get(i).getMessageType(), tasks.get(i).getData(), unixTime);
				taskList.add(mapper.writeValueAsString(cmd));
				taskRepository.updateStatus("1", unixTime, tasks.get(i).getAk(), 2, 23438, 1);

			}
			return taskList.toString();
		} else return "";
	}

}
