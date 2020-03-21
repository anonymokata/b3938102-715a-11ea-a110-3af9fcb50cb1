package com.github.jmgorton.wordsearch.model;

public class PuzzleElement {

    public Character value;

    public Coord location = null;

    public Puzzle puzzle;
    public PuzzleElement above, below, toRight, toLeft, aboveToRight, aboveToLeft, belowToRight, belowToLeft;

    public PuzzleElement(final Character val) {
        // assign this element's value
        this.value = val;

        // assign neighbors
        this.above = null;
        this.below = null;
        this.toRight = null;
        this.toLeft = null;
        this.aboveToRight = null;
        this.aboveToLeft = null;
        this.belowToRight = null;
        this.belowToLeft = null;

    }

    /**
     * could optimize this constructor using the knowledge that we will scan a puzzle from
     * left to right and top to bottom
     * @param val
     * @param toLeft
     * @throws Exception
     */
    public PuzzleElement(final Character val, final PuzzleElement elementToLeft) throws Exception {
        this(val);

        if (elementToLeft != null) {

            this.toLeft = elementToLeft;

            // no risk of NPE 
            // e.g. even if toLeft.above is null, this.aboveToLeft is equal to toLeft.above (both null)
            this.aboveToLeft = elementToLeft.above;
            this.belowToLeft = elementToLeft.below;
            this.above = elementToLeft.aboveToRight;
            this.below = elementToLeft.belowToRight;

            // if this != null and this.aboveToRight != null, this.above must also have a value
            // if the puzzle is a rectangle
            if (this.above != null) this.aboveToRight = this.above.toRight;
            if (this.below != null) this.belowToRight = this.below.toRight;

            // basic check to make sure that we arrive at the same toRight value two different ways.
            if (this.aboveToRight != null) {
                if (this.belowToRight != null) {
                    if (this.aboveToRight.below != this.belowToRight.above) throw new Exception("Error parsing puzzle ..."
                    + " Conflicting values for right neighbor: " 
                    + (this.aboveToRight.below == null ? "null" : this.aboveToRight.below.value) + ", " 
                    + (this.belowToRight.above == null ? "null" : this.belowToRight.above.value));
                } else {
                    this.toRight = this.aboveToRight.below;
                }
            } else if (this.belowToRight != null) {
                this.toRight = this.belowToRight.above;
            } else {
                // element aboveToRight and belowToRight are both null... so leave element toRight as null
            }

            updateNeighbors();

        }

    }

    /**
     * could optimize this constructor using the knowledge that we will scan a puzzle from
     * left to right and top to bottom
     * @param val letter value of this element in the puzzle
     * @param toLeft element to exist to the left of this one
     * @param above element to exist above this one
     * @throws Exception a potential inconsistency has been discovered
     */
    public PuzzleElement(final Character val, final PuzzleElement elementToLeft, final PuzzleElement elementAbove) throws Exception {
        this(val, elementToLeft);

        if (this.above == null) {
            if (elementAbove != null) {
                // handle row above this one
                this.above = elementAbove;
                this.aboveToLeft = elementAbove.toLeft;
                this.aboveToRight = elementAbove.toRight;

                // FIXME assumes both argument elements are vertices of a connected graph
                // if elements are members of two separate graphs, even if the insertion would "fit" ...
                // the verification throws an error and breaks it -- could probably just add a non-null check
                if (elementAbove.belowToLeft != this.toLeft) {
                    throw new Exception("Error parsing puzzle ... Conflicting values for left neighbor: "
                    + (this.toLeft == null ? "null" : this.toLeft.value) + ", " 
                    + (elementAbove.belowToLeft == null ? "null" : elementAbove.belowToLeft.value));
                }
                if (elementAbove.belowToRight != this.toRight) {
                    throw new Exception("Error parsing puzzle ... Conflicting values for right neighbor: "
                    + (this.toRight == null ? "null" : this.toRight.value) + ", " 
                    + (elementAbove.belowToRight == null ? "null" : elementAbove.belowToRight.value));
                }

                updateNeighbors();

            }
        } else if (this.above != elementAbove) {
            throw new Exception("Error parsing puzzle ... Conflicting values for above neighbor: "
            + this.above.value + ", " 
            + (elementAbove == null ? "null" : elementAbove.value) + " (potential invalid argument)");
        } else {
            // in the case that this.above is already set to the same element that was passed in ... 
            // i think we don't need to do anything in this case
        }

        // TODO investigate ... for some reason this was causing neighbors that should have
        // stayed null to be instantiated ... i think
        // updateNeighbors();
    }

    public PuzzleElement(final Character val, final PuzzleElement above, final PuzzleElement aboveToRight,
            final PuzzleElement toRight, final PuzzleElement belowToRight, final PuzzleElement below,
            final PuzzleElement belowToLeft, final PuzzleElement toLeft, final PuzzleElement aboveToLeft) {
        
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

    public void setCoords(Coord loc) {
        this.location = loc;
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