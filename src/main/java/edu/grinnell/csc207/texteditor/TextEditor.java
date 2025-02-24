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
     * @param args command-line arguments.
     */
    public static void main(String[] args) throws IOException {
        
        if(args.length == 0){
            throw new IndexOutOfBoundsException("Please try again with a file in mind.");
            
        }
        
        Screen screen = new DefaultTerminalFactory().createScreen();
        
        screen.startScreen();
        
        Path path = Paths.get(args[0]);
        System.out.format("Loading %s...\n", path);
        
        KeyStroke stroke = screen.readInput();
        GapBuffer buf;
        
        if(Files.isRegularFile(path) && Files.exists(path)){
            buf = new GapBuffer(Files.readString(path));
        } else{
            buf = new GapBuffer("");
        }
        
        while (!stroke.getKeyType().equals(KeyType.Escape)) {
            stroke = screen.readInput();
            if(stroke.getKeyType().equals(KeyType.Character)){
                buf.insert(stroke.getCharacter());
            } else if(stroke.getKeyType().equals(KeyType.ArrowRight)){
                buf.moveRight();
            } else if(stroke.getKeyType().equals(KeyType.ArrowLeft)){
                buf.moveLeft();
            } else if(stroke.getKeyType().equals(KeyType.Backspace)){
                buf.delete();
            }
            drawBuffer(buf, screen);
        }
        String s = new String(buf.gapBuffer);
        Files.writeString(path, s);
        screen.stopScreen();
    }
    
    public static void drawBuffer (GapBuffer buf, Screen screen) throws IOException{
        int col = 0;
        if(buf.gapBuffer.length > 0){
            for(int i = 0; i < buf.gapBuffer.length; i++){
                if(i % (col * 60 + 60) == 0 && i != 0){
                    col++;
                }
                screen.setCharacter(i - (col * 60), col, new TextCharacter(buf.gapBuffer[i]));  
            }
        }
        TerminalPosition pos = new TerminalPosition(buf.getCursorPosition() - (col * 60), col);
        screen.setCursorPosition(pos);
        screen.refresh();
    }
    
}
