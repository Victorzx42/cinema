package com.example.demo.services;

import com.example.demo.model.Message;
import com.example.demo.repository.RepositoryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesMessage {
    @Autowired
    private RepositoryMessage methodsCrud;

    public List<Message> getAll(){
        return methodsCrud.getAll();
    }

    public Optional<Message> getMessage(int messageId) {
        return methodsCrud.getMessage(messageId);
    }

    public Message save(Message message){
        if(message.getIdMessage()==null){
            return methodsCrud.save(message);
        }else{
            Optional<Message> e= methodsCrud.getMessage(message.getIdMessage());
            if(e.isEmpty()){
                return methodsCrud.save(message);
            }else{
                return message;
            }
        }
    }

    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> e= methodsCrud.getMessage(message.getIdMessage());
            if(!e.isEmpty()){
                if(message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                methodsCrud.save(e.get());
                return e.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            methodsCrud.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
