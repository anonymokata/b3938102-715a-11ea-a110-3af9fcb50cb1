package com.github.jmgorton.wordsearch.model;

public class PuzzleElement {

    Integer value;

    Puzzle puzzle;
    PuzzleElement above, below, toRight, toLeft, aboveToRight, aboveToLeft, belowToRight, belowToLeft;

    public PuzzleElement(final Integer val) {
        // assign this element's value
        this.value = val;

        // assign neighbors
        this.value = null;
        this.above = null;
        this.below = null;
        this.toRight = null;
        this.toLeft = null;
        this.aboveToRight = null;
        this.aboveToLeft = null;
        this.belowToRight = null;
        this.belowToLeft = null;

    }

    public PuzzleElement(final Integer val, final PuzzleElement toLeft) {
        this(val);

        if (toLeft != null) {

            this.toLeft = toLeft;

            // no risk of NPE 
            // e.g. even if toLeft.above is null, this.aboveToLeft is equal to toLeft.above (both null)
            this.aboveToLeft = toLeft.above;
            this.belowToLeft = toLeft.below;
            this.above = toLeft.aboveToRight;
            this.below = toLeft.belowToRight;

            // if this != null and this.aboveToRight != null, this.above must also have a value
            // if the puzzle is a rectangle
            if (this.above != null) this.aboveToRight = this.above.toRight;
            if (this.below != null) this.belowToRight = this.below.toRight;

            // basic check to make sure that we arrive at the same toRight value two different ways.
            try {
                if (this.aboveToRight.below != this.belowToRight.above) throw new Exception("Error parsing puzzle ...");
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    // bury this. probably just that this is a top-, bottom-, or right-edge element

                    // if both errors would have been thrown, this still shouldn't execute ... refactor later
                    if (this.aboveToRight != null) this.toRight = this.aboveToRight.below;
                    else if (this.belowToRight != null) this.toRight = this.belowToRight.above;
                } else {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                    e.printStackTrace(System.err);
                    return;
                }
            } finally {
                updateNeighbors();
            }

            // // TODO test this ... might need to move into finally block -- will it get executed upon catching an error?
            // if (this.aboveToRight != null) this.toRight = this.aboveToRight.below;
            // else if (this.belowToRight != null) this.toRight = this.belowToRight.above;

            // updateNeighbors();

        }

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