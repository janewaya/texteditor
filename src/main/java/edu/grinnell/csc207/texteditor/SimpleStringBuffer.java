package edu.grinnell.csc207.texteditor;


/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    
    int pos;
    String buffer;
    
/**
 * Creates a Simple String buffer.
 */
    public SimpleStringBuffer() {
        this.pos = 0;
        this.buffer = "";
    }

    /**
* Inserts a character into the string.
*
* @param ch The character to insert
*/
    public void insert(char ch) {
        char[] chrs = {ch};
        String addition = new String(chrs);
        if (this.pos == this.buffer.length()) {
            this.buffer = this.buffer.concat(addition);
        } else if (this.pos == 0) {
            this.buffer = addition.concat(this.buffer);
        } else {
            String start = new String(this.buffer.substring(0, this.pos));
            String end = new String(this.buffer.substring(this.pos, this.buffer.length()));
            this.buffer = start.concat(addition);
            this.buffer = this.buffer.concat(end);
        }
        this.pos++;
    }
    
    /**
* Deletes a character from the string.
*
*/
    public void delete() {
        if (this.pos != 0 && this.pos == this.buffer.length()) {
            this.buffer = this.buffer.substring(0, this.buffer.length() - 1);
            this.pos--;
        } else if(this.pos != 0 && this.buffer.length() != 0 && this.pos < this.buffer.length()) {
            String start = new String(this.buffer.substring(0, this.pos - 1));
            String end = new String(this.buffer.substring(this.pos, this.buffer.length()));
            this.buffer = start.concat(end);
            this.pos--;
        }
    }

/**
* Inserts a character into the string.
*
* @return int The current position of the cursor.
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
            this.pos--;
        }
    }

    /**
* Moves the cursor right.
*
*/
    public void moveRight() {
        if (this.pos < this.buffer.length()) {
            this.pos++;
        }
    }

    /**
* Returns the size of the buffer.
*
* @return int the size of the buffer.
*/
    public int getSize() {
        return this.buffer.length();
    }
    
    /**
* Returns a character at a certain spot within the buffer.
*
* @param i the spot one wants the character to return at.
* @return char a char within the buffer.
*/
    public char getChar(int i) {
        return this.buffer.charAt(i);
    }

    
    /**
* Returns the buffer as a String.
*
*/    
    @Override
    public String toString() {
        return this.buffer;
    }
}
