package com.nikosera.common.builder;

import java.util.function.Supplier;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class ConditionalResponseBuilder<M> {

    private Boolean condition;
    private Supplier<M> left;
    private ConditionalResponseBuilder<M> responseBuilder;

    public ConditionalResponseBuilder() {
    }

    public ConditionalResponseBuilder<M> iif(boolean condition) {
        this.condition = condition;
        return this;
    }

    public <X extends Throwable> M orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (condition) {
            return left.get();
        } else {
            throw exceptionSupplier.get();
        }
    }

    public M orElse(Supplier<M> right) {
        if (condition) {
            return left.get();
        } else {
            return right.get();
        }
    }


    public ConditionalResponseBuilder<M> then(Supplier<M> left) {
        this.left = left;
        return this;
    }

    public static <M> ConditionalResponseBuilder<M> builder(Class<M> type) {
        return new ConditionalResponseBuilder<M>();
    }

    public static ConditionalResponseBuilder<Object> builder() {
        return new ConditionalResponseBuilder<>();
    }

    public static void main(String[] args) {

    }
}

