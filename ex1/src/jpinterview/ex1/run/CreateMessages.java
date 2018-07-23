package jpinterview.ex1.run;

import jpinterview.ex1.MessageFormat;
import jpinterview.ex1.messagemodel.Message;

import java.util.Random;

public class CreateMessages {
    public static void main(String[] args) {
        Random rng = new Random();
        for (int i = 0; i < 50; i++) {
            Message m = Demonstrate.randomMessage(rng);
            System.out.println(MessageFormat.toString(m));
        }
    }
}
