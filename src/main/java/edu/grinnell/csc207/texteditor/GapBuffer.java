package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    
    public int size;
    public char[] gapBuffer;
    public int pos;
    
    private static final int INITIAL_SIZE = 10;
    
    public GapBuffer(){
        this.size = 0;
        this.pos = 0;
        this.gapBuffer = new char[INITIAL_SIZE];
        for(int i = 0; i < INITIAL_SIZE; i++){
            this.gapBuffer[i] = ' ';
        }
    }
    
    private void ensureCapacity() {
        if (this.size == this.gapBuffer.length - 1) {
            char[] result = Arrays.copyOf(this.gapBuffer, this.gapBuffer.length * 2);
            for(int i = this.gapBuffer.length; i < this.gapBuffer.length * 2; i++){
                result[i] = ' ';
            }
            if(this.pos != this.size){
                int length = result.length - 1;
                for(int i = length; i > (length - (this.size - this.pos)); i--){
                    result[i] = this.gapBuffer[this.gapBuffer.length - (result.length - i)];
                    result[this.size + 1 - (result.length - i)] = ' ';
                }
            }
            this.gapBuffer = result;
        }
    }
    
    public void insert(char ch) {
        ensureCapacity();
        if(this.pos == this.size){
            this.gapBuffer[this.size] = ch;
        }
        else{
            this.gapBuffer[this.pos] = ch;
        }
        this.size++;
        this.pos++;
    }

    public void delete() {
        if(this.pos > 0){
            this.gapBuffer[this.pos - 1] = ' '; 
            this.pos--;
            this.size--;
        }
    }

    public int getCursorPosition() {
        return this.pos;
    }

    public void moveLeft() {
        if(this.pos > 0){
            int i = this.pos;
            if(this.size > this.pos){
                while(this.gapBuffer[i] == ' ' && i < this.gapBuffer.length - 1){
                    i++;
                }
                this.gapBuffer[i - 1] = this.gapBuffer[this.pos - 1];
            }else{
                this.gapBuffer[this.gapBuffer.length - 1] = this.gapBuffer[this.pos - 1];
            }
            this.gapBuffer[this.pos - 1] = ' ';
            this.pos--;
        }
    }

    public void moveRight() {
        if(this.pos < this.size){
            int i = this.pos;
            this.pos++;
            while(this.gapBuffer[i] == ' ' && i < this.gapBuffer.length - 1){
                i++;
            }
            
            this.gapBuffer[this.pos - 1] = this.gapBuffer[i];
            this.gapBuffer[i] = ' ';
        }
    }

    public int getSize() {
        return this.size;
    }

    public char getChar(int i) {
        return this.gapBuffer[i];
    }

    public String toString() {
        String s = new String(this.gapBuffer);
        return s;
    }
}
