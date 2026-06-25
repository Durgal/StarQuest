/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Christopher
 */
public class Output {

    private static PrintStream originalOut;
    private static ByteArrayOutputStream baos;
    private static PrintStream captureStream;
    private static boolean capturing = false;

    // Start capturing stdout
    public static void start() {
        if (!capturing) {
            originalOut = System.out;
            baos = new ByteArrayOutputStream();
            captureStream = new PrintStream(baos);
            System.setOut(captureStream);
            capturing = true;
        }
    }

    // Stop capturing and return captured output
    public static String stop() {
        if (capturing) {
            System.setOut(originalOut);
            capturing = false;
        }
        return baos != null ? baos.toString().replace("\r\n", "\n") : "";
    }

    // Optional: clear captured output if you want to reuse
    public static void clear() {
        if (baos != null) {
            baos.reset();
        }
    }
}
