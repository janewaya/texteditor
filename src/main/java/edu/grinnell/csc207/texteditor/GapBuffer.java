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
    
    public GapBuffer(String start){
        this.size = start.length();
        this.pos = start.length();
        this.startBuffer = start.length() + 1;
        this.gapBuffer = new char[start.length() + INITIAL_SIZE];
        this.gapBuffer = Arrays.copyOf(start.toCharArray(), start.length() + INITIAL_SIZE);
        for(int i = start.length(); i < start.length() + INITIAL_SIZE; i++){
            this.gapBuffer[i] = ' ';
        }
        this.endBuffer = this.gapBuffer.length - 1;
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
            this.endBuffer = result.length - (this.size - this.pos) - 1;
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
        this.startBuffer++;
        this.size++;
        this.pos++;
    }

    public void delete() {
        if(this.pos > 0){
            this.gapBuffer[this.pos - 1] = ' '; 
            this.pos--;
            this.size--;
            this.startBuffer--;
        }
    }

    public int getCursorPosition() {
        return this.pos;
    }

    public void moveLeftOg() {
        if(this.pos > 0){
            this.startBuffer--;
            this.endBuffer--;

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
    
        public void moveLeft() {
        if(this.pos > 0){
            this.startBuffer--;
            this.endBuffer--;
            
            this.gapBuffer[this.endBuffer + 1] = this.gapBuffer[this.startBuffer - 1];
            this.gapBuffer[this.startBuffer - 1] = ' ';
            this.pos--;
        }
    }

    public void moveRightOg() {
        if(this.pos < this.size){
            this.startBuffer++;
            this.endBuffer++;
            
            int i = this.pos;
            this.pos++;
            while(this.gapBuffer[i] == ' ' && i < this.gapBuffer.length - 1){
                i++;
            }
            
            this.gapBuffer[this.pos - 1] = this.gapBuffer[i];
            this.gapBuffer[i] = ' ';
        }
    }
    
        public void moveRight() {
        if(this.pos < this.size){
            this.pos++;
            this.endBuffer++;
            this.gapBuffer[this.pos - 1] = this.gapBuffer[this.endBuffer];
            this.gapBuffer[this.endBuffer] = ' ';
            this.startBuffer++;
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
