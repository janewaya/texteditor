package edu.grinnell.csc207.texteditor;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GapBufferTests {
    @Test
    public void addingAbc() {
      GapBuffer g = new GapBuffer();
      g.insert('a');
      g.insert('b');
      g.insert('c');
      assertEquals("abc       ", g.toString());
    }
    @Test
    public void deleteLetters() {
      GapBuffer g = new GapBuffer();
      g.insert('H');
      g.insert('e');
      g.insert('l');
      g.insert('l');
      g.insert('o');
      g.insert('!');
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.delete();
      g.moveRight();
      g.moveRight();
      g.delete();
      assertEquals("Hel      !", g.toString());
    }
    
    @Test
    public void insertStartAndMiddle() {
      GapBuffer g = new GapBuffer();
      g.insert('H');
      g.insert('e');
      g.insert('l');
      g.insert('l');
      g.insert('o');
      g.insert('!');
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.insert('a');
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.insert('h');
      g.moveRight();
      g.delete();

      assertEquals("h   elalo!", g.toString());
    }
    
    @Test
    public void edgeCases() {
      GapBuffer g = new GapBuffer();
      g.insert('a');
      g.insert('b');
      g.insert('c');
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.moveLeft();
      g.delete();
      g.moveRight();
      g.moveRight();
      g.moveRight();
      g.moveRight();
      g.moveRight();
      g.moveRight();
      g.delete();
      g.insert('d');

      assertEquals("abd       ", g.toString());
    }
    
    @Test
    public void veryLong() {
      GapBuffer g = new GapBuffer();
      g.insert('W');
      g.insert('e');
      g.insert('l');
      g.insert('l');
      g.insert(',');
      g.insert(' ');
      g.insert('I');
      g.insert('\'');
      g.insert('m');
      g.insert(' ');
      g.insert('n');
      g.insert('o');
      g.insert('t');
      g.insert(' ');
      g.insert('a');
      g.insert(' ');
      g.insert('z');
      g.insert('o');
      g.insert('m');
      g.insert('b');
      g.insert('i');
      g.insert('e');
      g.insert(' ');
      g.insert('b');
      g.insert('u');
      g.insert('t');
      g.insert(' ');
      g.insert('t');
      g.insert('o');
      g.insert('d');
      g.insert('a');
      g.insert('y');
      g.insert(' ');
      g.insert('I');
      g.insert(' ');
      g.insert('f');
      g.insert('e');
      g.insert('e');
      g.insert('l');
      g.insert(' ');
      g.insert('l');
      g.insert('i');
      g.insert('k');
      g.insert('e');
      g.insert(' ');
      g.insert('o');
      g.insert('n');
      g.insert(' ');
      g.insert('.');
      g.moveLeft();
      g.moveLeft();
      g.insert('e');
      g.insert('e');
      g.delete();

      assertEquals("Well, I'm not a zombie but today I feel like one                               .", g.toString());
    }
    
    @Test
    public void longInMiddle() {
      GapBuffer g = new GapBuffer();
      g.insert('W');
      g.insert('e');
      g.insert('e');
      g.insert('p');
      g.insert(',');
      g.insert(' ');
      g.insert('l');
      g.insert('i');
      g.moveLeft();
      g.moveLeft();
      g.insert('l');
      g.insert('i');
      g.insert('t');
      g.insert('t');
      g.insert('l');
      g.insert('e');
      g.insert(' ');

      assertEquals("Weep, little      li", g.toString());
    }
    
        @Property
    public boolean stopAtZero(
            @ForAll @IntRange(min = 1, max = 1000) int sz) {
        GapBuffer g = new GapBuffer();
        for (int i = 0; i < sz; i++) {
            g.moveLeft();
        }
        return g.getCursorPosition() == 0;
    }
    
    @Property
    public boolean stopAtLength(
            @ForAll @IntRange(min = 1, max = 1000) int sz) {
       GapBuffer g = new GapBuffer();
        g.insert('a');
        g.insert('b');
        g.insert('c');
        for (int i = 0; i < sz; i++) {
            g.moveRight();
        }
        return g.getCursorPosition() == 3;
    }
    
}
