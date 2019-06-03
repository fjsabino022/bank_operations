package com.fsabino.banking_business.repository.data;

import static com.fsabino.banking_business.repository.data.OperationData.Type.DEPOSIT;

public class DepositData extends OperationData {

    private DepositData(String cbu, double amount, Type type) {
        super(cbu, amount, type);
    }

    public static DepositBuilder builder() {
        return new DepositBuilder();
    }

    public static class DepositBuilder {
        private String cbu;
        private double amount;

        public DepositBuilder cbu(String cbu) {
            this.cbu = cbu;
            return this;
        }

        public DepositBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public OperationData build() {
            return new DepositData(this.cbu, this.amount, DEPOSIT);
        }
    }
}
