package com.adeniltonarcanjo.pipcpay.services;


import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;


    public void sendNotification(User user, String message) throws Exception {

        String email= user.getEmail();
        NotificationDTO notificationRequest= new NotificationDTO(email,message);
//        ResponseEntity<String> nostificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
//
//        if (!(nostificationResponse.getStatusCode() == HttpStatus.OK)) {
//            throw new Exception(" Serviço de notificação esta fora do ar");
//
//        }
        System.out.println("notificação enviada pada o usuario");
    }



}
