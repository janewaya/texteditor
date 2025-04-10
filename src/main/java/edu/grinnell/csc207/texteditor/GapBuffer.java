package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    public int size;
    public char[] gapBuffer;
    public int pos;
    public int startBuffer;
    public int endBuffer;

    private static final int INITIAL_SIZE = 10;

    /**
     * Creates a GapBuffer object.
     *
     * @param start Contains the original String to be edited.
     */
    public GapBuffer(String start) {
        this.size = start.length();
        this.pos = start.length();
        this.startBuffer = start.length();
        this.gapBuffer = new char[start.length() + INITIAL_SIZE];
        this.gapBuffer = Arrays.copyOf(start.toCharArray(), start.length() + INITIAL_SIZE);
        for (int i = start.length(); i < start.length() + INITIAL_SIZE; i++) {
            this.gapBuffer[i] = ' ';
        }
        this.endBuffer = this.gapBuffer.length - 1;
    }
    /**
     * Creates a GapBuffer object.
     *
     */
    public GapBuffer() {
        this.size = 0;
        this.pos = 0;
        this.startBuffer = 0;
        this.gapBuffer = new char[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++) {
            this.gapBuffer[i] = ' ';
        }
        this.endBuffer = this.gapBuffer.length - 1;
    }

    /**
     * Makes sure the array is large enough to contain the new letters.
     *
     */
    private void ensureCapacity() {
        if (this.size == this.gapBuffer.length - 1) {
            char[] result = Arrays.copyOf(this.gapBuffer, this.gapBuffer.length * 2);
            for (int i = this.gapBuffer.length; i < this.gapBuffer.length * 2; i++) {
                result[i] = ' ';
            }
            if (this.pos != this.size) {
                int length = result.length - 1;
                for (int i = length; i > (length - (this.size - this.pos)); i--) {
                    result[i] = this.gapBuffer[this.gapBuffer.length - (result.length - i)];
                    result[this.size + 1 - (result.length - i)] = ' ';
                }
            }
            this.endBuffer = result.length - (this.size - this.pos) - 1;
            this.gapBuffer = result;
        }
    }

    /**
     * Updates the drawing of the Buffer on screen
     *
     * @param ch the character being added to the buffer
     */
    public void insert(char ch) {
        ensureCapacity();
        if (this.pos == this.size) {
            this.gapBuffer[this.size] = ch;
        } else {
            this.gapBuffer[this.pos] = ch;
        }
        this.startBuffer++;
        this.size++;
        this.pos++;
    }

    /**
     * Deletes a character from the buffer.
     *
     */
    public void delete() {
        if (this.pos > 0) {
            this.gapBuffer[this.pos - 1] = ' ';
            this.pos--;
            this.size--;
            this.startBuffer--;
        }
    }

    /**
     * Returns the position of the Cursor
     *
     * @return int the position of the cursor.
     */
    public int getCursorPosition() {
        return this.pos;
    }

    /**
     * Moves the cursor left.
     *
     */
    public void moveLeft() {
        if (this.pos > 0) {
            int i = this.pos;
            if (this.size > this.pos) {
                i = this.endBuffer;
                this.gapBuffer[i] = this.gapBuffer[this.pos - 1];
            } else {
                this.gapBuffer[this.gapBuffer.length - 1] = this.gapBuffer[this.pos - 1];
            }
            this.gapBuffer[this.pos - 1] = ' ';
            this.pos--;
            this.startBuffer--;
            this.endBuffer--;
        }
    }

    /**
     * Moves the cursor Right.
     *
     */
    public void moveRight() {
        if (this.pos < this.size) {
            int i = this.endBuffer + 1;
            this.gapBuffer[this.pos] = this.gapBuffer[i];
            this.gapBuffer[i] = ' ';
            this.pos++;
            this.startBuffer++;
            this.endBuffer++;
        }
    }

    /**
     * Returns the size of the buffer.
     *
     * @return int returns the current size of the buffer
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns an int at the current spot in the buffer.
     *
     * @param i the index that the character we wish to return is at.
     * @return char returns a character at a certain spot within the buffer.
     */
    public char getChar(int i) {
        return this.gapBuffer[i];
    }

    /**
     * Returns the buffer as a string.
     *
     * @return String returns the buffer as a string.
     */
    public String toString() {
        char[] temp = Arrays.copyOf(this.gapBuffer, this.size);
        int j = 0;
        for (int i = this.endBuffer + 1; i < this.gapBuffer.length; i++) {
            temp[this.startBuffer + j] = this.gapBuffer[i];
            j++;
        }
        String s = new String(temp);
        return s;
    }

}
