package ru.senior.council.workflow.core.steps;

public record StepResult<O>(O o, String stepName, StepState state) {

    public static <O> StepResult<O> ok(O o, String stepName) {
        return new StepResult<>(o, stepName, StepState.OK);
    }

    public static <O> StepResult<O> failed(O o, String stepName) {
        return new StepResult<>(o, stepName, StepState.FAILED);
    }

    public boolean isOk() {
        return this.state == StepState.OK;
    }
    public boolean isFailed() {
        return this.state == StepState.FAILED;
    }
}
