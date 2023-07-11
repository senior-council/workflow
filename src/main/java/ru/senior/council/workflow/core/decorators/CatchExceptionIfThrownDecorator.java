package ru.senior.council.workflow.core.decorators;

import ru.senior.council.workflow.core.steps.AbstractStep;
import ru.senior.council.workflow.core.operations.Operation;
import ru.senior.council.workflow.core.steps.StepResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatchExceptionIfThrownDecorator<O extends Operation> extends StepDecorator<O> {

    public CatchExceptionIfThrownDecorator(AbstractStep<O> decorator) {
        super(decorator);
    }

    @Override
    public StepResult<O> apply(O o) {
        try {
            return step.apply(o);
        } catch (Exception ex) {
            log.error("Step '{}' was failed with error message: {}", step.stepName(), ex.getMessage());
            return StepResult.failed(o, step.stepName());
        }
    }
}