package com.example.resilience.services;

import com.example.resilience.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FraudDetectionService {

    private final KieContainer kieContainer;

    public FraudDetectionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Transaction evaluate(Transaction transaction) {
        log.info("trasaction : " + transaction);
        try {
            KieSession kieSession = kieContainer.newKieSession("ksession-rules");
            kieSession.insert(transaction);
            kieSession.fireAllRules();

        } catch (RuntimeException e) {
            log.info("Runtime exception :{} ", e.getMessage());
        } finally {
            kieContainer.dispose();
        }

        return transaction;
    }
}
