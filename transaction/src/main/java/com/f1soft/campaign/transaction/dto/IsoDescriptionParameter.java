package com.f1soft.campaign.transaction.dto;


import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.transaction.util.StringHelper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsoDescriptionParameter extends ModelBase {

    private final String traceId;
    private final String initiator;
    private final String serviceName;
    private final String serviceCode;
    private final String paymentAttribute;
    private final String remarks;
    private final String originatorUniqueCode;
    private final String consumerId;
    private final String fromAccount;
    private final String toAccount;
    private final String tellerUserId;
    private final String requestChannel;
    private final String terminalId;
    private final String referenceNumber;
    private final String traceNumber;
    private final String reversalId;
    private final String promoCode;

    private IsoDescriptionParameter(IsoDescriptionParameterBuilder builder) {
        this.traceId = StringHelper.convertToEmptyIfNull(builder.traceId);
        this.serviceName = StringHelper.convertToEmptyIfNull(builder.serviceName);
        this.serviceCode = StringHelper.convertToEmptyIfNull(builder.serviceCode);
        this.initiator = StringHelper.convertToEmptyIfNull(builder.initiator);
        this.paymentAttribute = StringHelper.convertToEmptyIfNull(builder.paymentAttribute);
        this.remarks = StringHelper.convertToEmptyIfNull(builder.remarks);
        this.originatorUniqueCode = StringHelper.convertToEmptyIfNull(builder.originatorUniqueCode);
        this.consumerId = StringHelper.convertToEmptyIfNull(builder.consumerId);
        this.fromAccount = StringHelper.convertToEmptyIfNull(builder.fromAccount);
        this.toAccount = StringHelper.convertToEmptyIfNull(builder.toAccount);
        this.tellerUserId = StringHelper.convertToEmptyIfNull(builder.tellerUserId);
        this.requestChannel = StringHelper.convertToEmptyIfNull(builder.requestChannel);
        this.terminalId = StringHelper.convertToEmptyIfNull(builder.terminalId);
        this.referenceNumber = StringHelper.convertToEmptyIfNull(builder.referenceNumber);
        this.traceNumber = StringHelper.convertToEmptyIfNull(builder.traceNumber);
        this.reversalId = StringHelper.convertToEmptyIfNull(builder.reversalId);
        this.promoCode = StringHelper.convertToEmptyIfNull(builder.promoCode);
    }


    public static class IsoDescriptionParameterBuilder extends ModelBase {

        private String traceId;
        private String serviceName;
        private String serviceCode;
        private String initiator;
        private String paymentAttribute;
        private String remarks;
        private String originatorUniqueCode;
        private String consumerId;
        private String fromAccount;
        private String toAccount;
        private String tellerUserId;
        private String requestChannel;
        private String terminalId;
        private String referenceNumber;
        private String traceNumber;
        private String reversalId;
        private String promoCode;

        public IsoDescriptionParameterBuilder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public IsoDescriptionParameterBuilder fromAccount(String fromAccount) {
            this.fromAccount = fromAccount;
            return this;
        }

        public IsoDescriptionParameterBuilder toAccount(String toAccount) {
            this.toAccount = toAccount;
            return this;
        }

        public IsoDescriptionParameterBuilder serviceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public IsoDescriptionParameterBuilder serviceCode(String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }

        public IsoDescriptionParameterBuilder initiator(String initiator) {
            this.initiator = initiator;
            return this;
        }

        public IsoDescriptionParameterBuilder paymentAttribute(String paymentAttribute) {
            this.paymentAttribute = paymentAttribute;
            return this;
        }

        public IsoDescriptionParameterBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public IsoDescriptionParameterBuilder originatorUniqueCode(String originatorUniqueCode) {
            this.originatorUniqueCode = originatorUniqueCode;
            return this;
        }

        public IsoDescriptionParameterBuilder consumerId(String consumerId) {
            this.consumerId = consumerId;
            return this;
        }

        public IsoDescriptionParameterBuilder tellerUserId(String tellerUserId) {
            this.tellerUserId = tellerUserId;
            return this;
        }

        public IsoDescriptionParameterBuilder requestChannel(String requestChannel) {
            this.requestChannel = requestChannel;
            return this;
        }

        public IsoDescriptionParameterBuilder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public IsoDescriptionParameterBuilder referenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
            return this;
        }

        public IsoDescriptionParameterBuilder traceNumber(String traceNumber) {
            this.traceNumber = traceNumber;
            return this;
        }

        public IsoDescriptionParameterBuilder reversalId(String reversalId) {
            this.reversalId = reversalId;
            return this;
        }

        public IsoDescriptionParameterBuilder promoCode(String promoCode) {
            this.promoCode = promoCode;
            return this;
        }

        public IsoDescriptionParameter build() {
            return new IsoDescriptionParameter(this);
        }
    }
}
