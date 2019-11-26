package com.stackroute.horoscopeService.service;

import com.stackroute.horoscopeService.domain.HoroscopeDTO;
import com.stackroute.horoscopeService.domain.Neo4jdata;
import com.stackroute.horoscopeService.domain.PayLoad;
import com.stackroute.horoscopeService.domain.ReceivedHoroscopeObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class RabbitMQListener {
    @Autowired
    RabbitMQSender sender;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    @RabbitListener(queues = "${horoscope.queue.name}")
    public void receiveMsg(ReceivedHoroscopeObject horoscopeObject){
        logger.info("Received Msg from Upstream");
        Neo4jdata obj = new Neo4jdata();
        obj.setEmail(horoscopeObject.getEmail());
        PayLoad payLoad = new PayLoad();
        payLoad.setHasHoroscope(true);
        HoroscopeDTO horoscopeDTO = new HoroscopeDTO();
        horoscopeDTO.setTimeofbirth(horoscopeObject.getHoroscopedata().getTimeOfBirth());
        horoscopeDTO.setPlaceofbirth(horoscopeObject.getHoroscopedata().getPlaceOfBirth());
        horoscopeDTO.setManglik(horoscopeObject.getHoroscopedata().getManglik());
        payLoad.setHoroscopeDTO(horoscopeDTO);
        obj.setPayloadDTO(payLoad);
        sender.send(obj);
    }
}
