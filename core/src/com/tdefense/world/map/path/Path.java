package com.tdefense.world.map.path;

import java.util.ArrayList;
import java.util.Arrays;

public class Path {
    private ArrayList<Step> steps;
    private int currentStep;

    public Path() {
        steps = new ArrayList<Step>();
        currentStep = 0;
    }

    public Path(Step... steps) {
        this.steps = new ArrayList<Step>();
        for (Step x : steps) {
            this.steps.addAll(Arrays.asList(steps));
        }
        currentStep = 0;
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public Step getStep() {
        return steps.get(currentStep++);
    }

    public void resetPath() {
        currentStep = 0;
    }
}
