package thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class BookingThread {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                BookingTask.book("7500", "9813600185");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BookingTask.book("6500", "9849151419");
            }
        }).start();
    }


}
