package com.fsabino.banking_business.repository.data;

import static com.fsabino.banking_business.repository.data.OperationData.Type.TAKEOUT;

public class TakeOutData extends OperationData {

    private TakeOutData(String cbu,
                        double amount,
                        Type type) {
        super(cbu, amount, type);
    }

    public static TakeOutBuilder builder() {
        return new TakeOutBuilder();
    }

    public static class TakeOutBuilder {
        private String cbu;
        private double amount;

        public TakeOutBuilder cbu(String cbu) {
            this.cbu = cbu;
            return this;
        }

        public TakeOutBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public OperationData build() {
            return new TakeOutData(this.cbu, this.amount, TAKEOUT);
        }
    }
}
