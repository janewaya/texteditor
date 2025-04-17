package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * The main entry point for the TextEditor application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            throw new IndexOutOfBoundsException("Please try again with a file in mind.");

        }

        Screen screen = new DefaultTerminalFactory().createScreen();

        screen.startScreen();

        Path path = Paths.get(args[0]);

        GapBuffer buf;

        if (Files.isRegularFile(path) && Files.exists(path)) {
            buf = new GapBuffer(Files.readString(path));
        } else {
            buf = new GapBuffer("");
        }
        drawBuffer(buf, screen);
        KeyStroke stroke = screen.readInput();

        while (!stroke.getKeyType().equals(KeyType.Escape)) {
            stroke = screen.readInput();
            if (stroke.getKeyType().equals(KeyType.Character)) {
                buf.insert(stroke.getCharacter());
            } else if (stroke.getKeyType().equals(KeyType.ArrowRight)) {
                buf.moveRight();
            } else if (stroke.getKeyType().equals(KeyType.ArrowLeft)) {
                buf.moveLeft();
            } else if (stroke.getKeyType().equals(KeyType.Backspace)) {
                buf.delete();
            }
            drawBuffer(buf, screen);
        }
        String s = new String(buf.gapBuffer);
        String sub1 = s.substring(0, buf.startBuffer);
        String sub2 = s.substring(buf.endBuffer + 1, buf.gapBuffer.length);
        String sDone = sub1.concat(sub2);
        Files.writeString(path, sDone);
        screen.stopScreen();
    }

    /**
     * Updates the drawing of the Buffer on screen
     *
     * @param buf the GapBuffer object containing the buffer
     * @param screen the object being used to print the buffer object.
     */
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        int col = 0;

        char[] s = buf.toString().toCharArray();
        if (s.length > 0) {
            for (int i = 0; i < s.length; i++) {
                if (i % (col * 60 + 60) == 0 && i != 0) {
                    col++;
                }
                screen.setCharacter(i - (col * 60), col, new TextCharacter(s[i]));
            }
            for (int j = s.length; j < buf.gapBuffer.length; j++) {
                screen.setCharacter(j - (col * 60), col, new TextCharacter(' '));
            }
        }
        TerminalPosition pos = new TerminalPosition(buf.getCursorPosition() - (col * 60), col);
        screen.setCursorPosition(pos);
        screen.refresh();
    }

}
