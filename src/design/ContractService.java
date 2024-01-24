package design;

import entities.Contract;
import entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {
        Double installmentValue = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);
            Double simpleInterest = onlinePaymentService.interest(installmentValue, i);
            Double paymentTax = onlinePaymentService.paymentFee(installmentValue + simpleInterest);
            Double installmentFinalValue = installmentValue + simpleInterest + paymentTax;
            contract.getInstallmentList().add(new Installment(dueDate, installmentFinalValue));
        }
    }
}
