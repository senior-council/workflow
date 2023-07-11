package ru.senior.council.workflow.core.decorators;

import ru.senior.council.workflow.core.steps.AbstractStep;
import ru.senior.council.workflow.core.operations.Operation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class StepDecorator<O extends Operation> extends AbstractStep<O> {
    protected AbstractStep<O> step;

    public StepDecorator(AbstractStep<O> step) {
        this.step = step;
        this.fallback(step.fallback());
        this.stepName(step.stepName());
    }
}
