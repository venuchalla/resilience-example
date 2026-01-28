package com.example.resilience.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class PaymentService {




    @Transactional(propagation = Propagation.REQUIRED)
    public String paymentService(){

        debitAccount();
        creditAccount();
        auditLog();
        return "success";
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void debitAccount(){
     log.info("Debit Account : ");
    }



    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public  void creditAccount(){
       log.info("creditAccount : ");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
    public  void auditLog(){
        log.info("audit log ");
    }
}
