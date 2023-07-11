package ru.senior.council.workflow.core.schema;

import ru.senior.council.workflow.core.decorators.chains.DecoratorChain;
import ru.senior.council.workflow.core.operations.Operation;
import ru.senior.council.workflow.core.resilience.Retry;
import ru.senior.council.workflow.core.steps.AbstractStep;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SchemaBuilder<O extends Operation> {
    private final List<AbstractStep<O>> steps = new ArrayList<>();

    private Retry retry;
    private DecoratorChain<O> decoratorChain;

    public static <O extends Operation> SchemaBuilder<O> builder() {
        return new SchemaBuilder<O>();
    }

    public SchemaBuilder<O> withDecoratorChain(DecoratorChain<? extends O> decoratorChain) {
        this.decoratorChain = (DecoratorChain<O>) decoratorChain;
        return this;
    }

    public SchemaBuilder<O> step(AbstractStep<? extends O> step) {
        steps.add((AbstractStep<O>) step);
        return this;
    }

    public SchemaBuilder<O> step(AbstractStep<? extends O> step, String stepName) {
        step.stepName(stepName);
        steps.add((AbstractStep<O>) step);
        return this;
    }

    public SchemaBuilder<O> withRetry(Retry retry) {
        this.retry = retry;
        return this;
    }

    public Schema<O> build() {
        return new Schema<>(retry, steps, decoratorChain);
    }
}