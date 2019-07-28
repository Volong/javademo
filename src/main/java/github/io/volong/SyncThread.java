package github.io.volong;

class SyncThread implements Runnable {
   private static int count;

   public SyncThread() {
      count = 0;
   }

   public  void run() {
      synchronized(this) {
         for (int i = 0; i < 5; i++) {
            // count = count + 1;
            System.out.println(Thread.currentThread().getName() + ":" + (count++));
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }
   }

   public int getCount() {
      return count;
   }
}
