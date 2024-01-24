package application;

import design.ContractService;
import design.PaypalService;
import entities.Contract;
import entities.Installment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter the contract details: ");
        System.out.print("Number: ");
        Integer number = scan.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        scan.nextLine();
        LocalDate date = LocalDate.parse(scan.nextLine(), fmt);
        System.out.print("Contract value: ");
        Double totalValue = scan.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter the number of installments: ");
        int n = scan.nextInt();

        ContractService service = new ContractService(new PaypalService());
        service.processContract(contract, n);

        System.out.println("Installments: ");
        for (Installment installment : contract.getInstallmentList()) {
            System.out.println(installment);
        }

        scan.close();
    }
}
