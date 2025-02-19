
package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class SimpleStringBufferTests {
    
    @Test
    public void addingAbc() {
      SimpleStringBuffer t = new SimpleStringBuffer();
      t.insert('a');
      t.insert('b');
      t.insert('c');
      assertEquals("abc", t.toString());
    }
    @Test
    public void deleteLetters() {
      SimpleStringBuffer t = new SimpleStringBuffer();
      t.insert('H');
      t.insert('e');
      t.insert('l');
      t.insert('l');
      t.insert('o');
      t.insert('!');
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.delete();
      t.moveRight();
      t.moveRight();
      t.delete();
      t.moveRight();
      t.delete();

      assertEquals("Hel", t.toString());
    }
    
    @Test
    public void insertStartAndMiddle() {
      SimpleStringBuffer t = new SimpleStringBuffer();
      t.insert('H');
      t.insert('e');
      t.insert('l');
      t.insert('l');
      t.insert('o');
      t.insert('!');
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.insert('a');
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.insert('h');
      t.moveRight();
      t.delete();

      assertEquals("helalo!", t.toString());
    }
    
    @Test
    public void edgeCases() {
      SimpleStringBuffer t = new SimpleStringBuffer();
      t.insert('a');
      t.insert('b');
      t.insert('c');
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.moveLeft();
      t.delete();
      t.moveRight();
      t.moveRight();
      t.moveRight();
      t.moveRight();
      t.moveRight();
      t.moveRight();
      t.delete();
      t.insert('d');

      assertEquals("abd", t.toString());
    }
    
    @Property
    public boolean stopAtZero(
            @ForAll @IntRange(min = 1, max = 1000) int sz) {
        SimpleStringBuffer t = new SimpleStringBuffer();
        for (int i = 0; i < sz; i++) {
            t.moveLeft();
        }
        return t.getCursorPosition() == 0;
    }
    
    @Property
    public boolean stopAtLength(
            @ForAll @IntRange(min = 1, max = 1000) int sz) {
        SimpleStringBuffer t = new SimpleStringBuffer();
        t.insert('a');
        t.insert('b');
        t.insert('c');
        for (int i = 0; i < sz; i++) {
            t.moveRight();
        }
        return t.getCursorPosition() == 3;
    }
    
}
