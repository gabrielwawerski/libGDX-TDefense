package com.tdefense.world.map.path;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Path {
    private ArrayList<Step> steps;
    private int currentStep;
    private int lastStep;
    private Vector2 startPosition;

    public Path() {
        steps = new ArrayList<Step>();
        startPosition = new Vector2();
        currentStep = 0;
    }

    public Vector2 getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(float x, float y) {
        startPosition.x = x;
        startPosition.y = y;
    }

    public Step getNextStep() {
        if (currentStep > steps.size() - 1)
            return null;
        return steps.get(currentStep++);
    }

    public Step getNextStepForBoolean() {
        if (currentStep > steps.size() - 1)
            return null;
        return steps.get(currentStep);
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public void resetPath() {
        currentStep = 0;
    }

    public void stepAcquired() {
        currentStep++;
    }
}
