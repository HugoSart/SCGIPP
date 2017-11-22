package br.com.uol.pagseguro.domain;

import java.util.List;

import br.com.uol.pagseguro.enums.PaymentMethodType;

/**
 * Payment method configuration
 */
public class PaymentMethodConfig {

    /**
     * Payment method type
     */
    private PaymentMethodType group;

    /**
     * Payment method configs
     */
    private List<ParameterItem> configs;

    /**
     *
     * @return the payment method type
     */
    public PaymentMethodType getGroup() {
        return group;
    }

    /**
     * Set the payment method type
     *
     * @param group
     */
    public void setGroup(PaymentMethodType group) {
        this.group = group;
    }

    /**
     *
     * @return the payment method configs
     */
    public List<ParameterItem> getConfigs() {
        return configs;
    }

    /**
     * Set payment method configs
     *
     * @param configs
     */
    public void setConfigs(List<ParameterItem> configs) {
        this.configs = configs;
    }

}
