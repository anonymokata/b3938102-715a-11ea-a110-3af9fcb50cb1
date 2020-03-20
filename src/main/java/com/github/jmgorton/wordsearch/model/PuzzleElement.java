package com.github.jmgorton.wordsearch.model;

public class PuzzleElement {

    Integer value;

    // TODO would coordinates help?

    Puzzle puzzle;

    PuzzleElement above;
    PuzzleElement below;
    PuzzleElement toRight;
    PuzzleElement toLeft;
    PuzzleElement aboveToRight;
    PuzzleElement aboveToLeft;
    PuzzleElement belowToRight;
    PuzzleElement belowToLeft;

    public PuzzleElement(final Integer val) {
        // assign this element's value
        this.value = val;

        // assign neighbors -- these null assignments aren't necessary
        // unless there's some case where we could get a "value may not have been initialized" warning somewhere
        this.value = null;
        this.above = null;
        this.below = null;
        this.toRight = null;
        this.toLeft = null;
        this.aboveToRight = null;
        this.aboveToLeft = null;
        this.belowToRight = null;
        this.belowToLeft = null;

        // update neighbors to recognize this element -- but this element currently has no neighbors
        // updateNeighbors();
    }

    public PuzzleElement(final Integer val, final PuzzleElement toLeft) {
        this.value = val;

        if (toLeft != null) {

            this.toLeft = toLeft;

            // these if blocks aren't strictly necessarily, could just assign directly. no risk of NPE
            // e.g. even if toLeft.above is null, this.aboveToLeft is equal to toLeft.above (both null)
            if (toLeft.above != null) this.aboveToLeft = toLeft.above; else this.aboveToLeft = null;
            if (toLeft.below != null) this.belowToLeft = toLeft.below; else this.belowToLeft = null;
            if (toLeft.aboveToRight != null) this.above = toLeft.aboveToRight; else this.above = null;
            if (toLeft.belowToRight != null) this.below = toLeft.belowToRight; else this.below = null;

            // if this != null and this.aboveToRight != null, this.above must also have a value
            if (this.above != null) this.aboveToRight = this.above.toRight; else this.aboveToRight = null;
            if (this.below != null) this.belowToRight = this.below.toRight; else this.belowToRight = null;

            // basic check to make sure that we arrive at the same toRight value two different ways.
            try {
                if (this.aboveToRight.below != this.belowToRight.above) throw new Exception("Error parsing puzzle ...");
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    // bury this. probably just that this is a top-, bottom-, or right-edge element
                } else {
                    e.printStackTrace();
                }
            } finally {

            }

            // TODO test this ... might need to move into finally block or restructure
            if (this.aboveToRight != null) this.toRight = this.aboveToRight.below;
            else if (this.belowToRight != null) this.toRight = this.belowToRight.above;
            else this.toRight = null;

        } else {

            this.above = null;
            this.below = null;
            this.toRight = null;
            this.toLeft = null;
            this.aboveToRight = null;
            this.aboveToLeft = null;
            this.belowToRight = null;
            this.belowToLeft = null;
            
        }

        updateNeighbors();
    }

    public PuzzleElement(final Integer val, final PuzzleElement above, final PuzzleElement below,
            final PuzzleElement toRight, final PuzzleElement toLeft, final PuzzleElement aboveToRight,
            final PuzzleElement aboveToLeft, final PuzzleElement belowToRight, final PuzzleElement belowToLeft) {
        
        // assign all values
        this.value = val;

        this.above = above;
        this.below = below;
        this.toRight = toRight;
        this.toLeft = toLeft;
        this.aboveToRight = aboveToRight;
        this.aboveToLeft = aboveToLeft;
        this.belowToRight = belowToRight;
        this.belowToLeft = belowToLeft;

        updateNeighbors();
    }

    public void updateNeighbors() {
        updateAbove();
        updateBelow();
        updateToLeft();
        updateToRight();
        
        updateAboveToLeft();
        updateAboveToRight();
        updateBelowToLeft();
        updateBelowToRight();
    }

    private void updateAbove() {
        if (this.above != null) {
            this.above.below = this;
        }
    }

    private void updateBelow() {
        if (this.below != null) {
            this.below.above = this;
        }
    }

    private void updateToLeft() {
        if (this.toLeft != null) {
            this.toLeft.toRight = this;
        }
    }

    private void updateToRight() {
        if (this.toRight != null) {
            this.toRight.toLeft = this;
        }
    }

    private void updateAboveToLeft() {
        if (this.aboveToLeft != null) {
            this.aboveToLeft.belowToRight = this;
        }
    }

    private void updateBelowToLeft() {
        if (this.belowToLeft != null) {
            this.belowToLeft.aboveToRight = this;
        }
    }

    private void updateAboveToRight() {
        if (this.aboveToRight != null) {
            this.aboveToRight.belowToLeft = this;
        }
    }

    private void updateBelowToRight() {
        if (this.belowToRight != null) {
            this.belowToRight.aboveToLeft = this;
        }
    }

}