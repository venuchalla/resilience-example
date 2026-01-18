package com.example.resilience.services;

import com.example.resilience.dto.Transaction;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionService {

    private final KieContainer kieContainer;

    public FraudDetectionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Transaction evaluate(Transaction transaction) {

        try (KieSession kieSession = kieContainer.newKieSession()) {
                kieSession.insert(transaction);
                kieSession.fireAllRules();

        }

        return transaction;
    }
}
