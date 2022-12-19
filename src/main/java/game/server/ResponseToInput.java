package game.server;

import game.data.MouseEventData;
import game.protocol.TCP;

import java.awt.event.KeyEvent;

public class ResponseToInput {
    public static class Keyboard {
        // Invoked on specific input UI elements (Text Field, etc.)
        public static void Typed(TCP.Client client, int key, Engine engine) {
            switch (key) {
                case KeyEvent.VK_E -> {
                    System.out.printf("%s - Interact!\n", client.identify());
                }
                default -> {
                    //  DEBUGGING PURPOSES
                    //System.out.printf("%s - %s - not implemented, BUT request received!\n", client.identify(), key);
                }
            }
        }

        public static void Pressed(TCP.Client client, int key, Engine engine) {
            switch (key) {
                case KeyEvent.VK_Q -> {
                    System.out.printf("%s - HOLDING!\n", client.identify());
                }
                case KeyEvent.VK_S -> {
                    ClientAttachment attachment = client.getAttachment();
                    if(attachment.amSleeping)
                        System.out.println("Going To Sleep!");
                    else
                        System.out.println("Going To Wake up!");
                    attachment.amSleeping = !attachment.amSleeping;
                }
                case KeyEvent.VK_D -> {
                    ClientAttachment attachment = client.getAttachment();
                    attachment.playerExample.rotation += 1/3.14;
                }
                case KeyEvent.VK_A -> {
                    ClientAttachment attachment = client.getAttachment();
                    attachment.playerExample.rotation -= 1/3.14;
                }
                default -> {
                    //  DEBUGGING PURPOSES
                    //System.out.printf("%s - %s - not implemented, BUT request received!\n", client.identify(), key);
                }
            }
        }

        public static void Released(TCP.Client client, int key, Engine engine) {
            switch (key) {
                case KeyEvent.VK_Q -> {
                    System.out.printf("%s - unHOLDING!\n", client.identify());
                }
                default -> {
                    //  DEBUGGING PURPOSES
                    //System.out.printf("%s - %s - not implemented, BUT request received!\n", client.identify(), key);
                }
            }
        }
    }
    public static class Mouse {
        public static void Pressed(TCP.Client client, MouseEventData mouse, Engine engine) {
            System.out.printf("%s - Pressed %s. %s\n", client.identify(), mouse.x, mouse.y);
        }
        public static void Released(TCP.Client client, MouseEventData mouse, Engine engine) {
            System.out.printf("%s - Released %s. %s\n", client.identify(), mouse.x, mouse.y);
        }
        public static void Clicked(TCP.Client client, MouseEventData mouse, Engine engine) {
            System.out.printf("%s - Clicked %s. %s\n", client.identify(), mouse.x, mouse.y);
        }
        public static void Entered(TCP.Client client, MouseEventData mouse, Engine engine) {
            System.out.printf("%s - Entered %s. %s\n", client.identify(), mouse.x, mouse.y);
        }

        public static void Exited(TCP.Client client, MouseEventData mouse, Engine engine) {
            System.out.printf("%s - Exited %s. %s\n", client.identify(), mouse.x, mouse.y);
        }
    }
}
