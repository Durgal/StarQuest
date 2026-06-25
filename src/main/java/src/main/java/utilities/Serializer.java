package src.main.java.utilities;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Serializer {

    // Single shared Kryo instance for simplicity. Kryo is not thread-safe, so synchronize if using across threads.
    private static final Kryo kryo = new Kryo();

    static {
        kryo.setReferences(true); // enable handling of circular references
        kryo.setRegistrationRequired(false); // optional, allows dynamic class registration
    }

    /**
     * Serialize an object to a byte array
     */
    public static byte[] toBytes(Object obj) {
        try (Output output = new Output(4096, -1)) { // initial buffer 4KB, no max
            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        }
    }

    /**
     * Deserialize an object from a byte array
     */
    public static <T> T fromBytes(byte[] bytes, Class<T> clazz) {
        try (Input input = new Input(bytes)) {
            Object obj = kryo.readClassAndObject(input);
            return clazz.cast(obj);
        }
    }

    /**
     * Save an object to a file (binary)
     */
    public static void saveToFile(Object obj, String path) throws IOException {
        try (Output output = new Output(new FileOutputStream(path))) {
            kryo.writeClassAndObject(output, obj);
        }
    }

    /**
     * Load an object from a file (binary)
     */
    public static <T> T loadFromFile(String path, Class<T> clazz) throws IOException {
        try (Input input = new Input(new FileInputStream(path))) {
            Object obj = kryo.readClassAndObject(input);
            return clazz.cast(obj);
        }
    }
}
