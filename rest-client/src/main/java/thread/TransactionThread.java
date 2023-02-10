package thread;

/**
 * @Author Nitesh Poudel
 */
public class TransactionThread {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                TransactionTask.transaction(390);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                TransactionTask.transaction(389);
            }
        }).start();
    }
}
