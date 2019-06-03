package com.fsabino.banking_business.repository.data;

import static com.fsabino.banking_business.repository.data.OperationData.Type.TRANSFER;

public class TransferData extends OperationData {

    private String cbuAnotherAccount;

    public String getCbuAnotherAccount() {
        return cbuAnotherAccount;
    }

    private TransferData(String cbu,
                         double amount,
                         Type type,
                         String cbuAnotherAccount) {
        super(cbu, amount, type);
        this.cbuAnotherAccount = cbuAnotherAccount;
    }

    public static TransferBuilder builder() {
        return new TransferBuilder();
    }

    public static class TransferBuilder {
        private String cbuAnotherAccount;
        private String cbu;
        private double amount;

        public TransferBuilder cbuAnotherAccount(String cbuAnotherAccount) {
            this.cbuAnotherAccount = cbuAnotherAccount;
            return this;
        }

        public TransferBuilder cbu(String cbu) {
            this.cbu = cbu;
            return this;
        }

        public TransferBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public OperationData build() {
            return new TransferData(this.cbu,
                    this.amount,
                    TRANSFER,
                    this.cbuAnotherAccount);
        }
    }
}
