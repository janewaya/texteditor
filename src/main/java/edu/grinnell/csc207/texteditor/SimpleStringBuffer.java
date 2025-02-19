package edu.grinnell.csc207.texteditor;

import java.lang.String;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    
    int pos;
    String buffer;
    
    public SimpleStringBuffer(){
        this.pos = 0;
        this.buffer = "";
    }
    
    public void insert(char ch) {
        char[] chrs = {ch};
        String addition = new String (chrs);
        if(this.pos == this.buffer.length()){
            this.buffer = this.buffer.concat(addition);
        }else if(this.pos == 0){
            this.buffer = addition.concat(this.buffer);
        }else{
            String start = new String (this.buffer.substring(0, this.pos));
            String end = new String (this.buffer.substring(this.pos, this.buffer.length()));
            this.buffer = start.concat(addition);
            this.buffer = this.buffer.concat(end);
        }
        this.pos++;
    }

    public void delete() {
        if(this.pos != 0 && this.pos == this.buffer.length()){
            this.buffer = this.buffer.substring(0, this.buffer.length() - 1);
            this.pos--;
        }
        else if (this.pos != 0 && this.buffer.length() != 0 && this.pos < this.buffer.length()){
            String start = new String (this.buffer.substring(0, this.pos - 1));
            String end = new String (this.buffer.substring(this.pos, this.buffer.length()));
            this.buffer = start.concat(end);
            this.pos--;
        }
    }

    public int getCursorPosition() {
        return this.pos;
    }

    public void moveLeft() {
        if(this.pos > 0){
            this.pos--;
        }
    }

    public void moveRight() {
        if(this.pos < this.buffer.length()){
            this.pos++;
        }
    }

    public int getSize() {
        return this.buffer.length();
    }

    public char getChar(int i) {
        return this.buffer.charAt(i);
    }

    @Override
    public String toString() {
        return this.buffer;
    }
}
