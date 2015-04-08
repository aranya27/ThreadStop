package ThreadStop;

class ThreadStopTest {
	public static void main(String[] args)
	{
		Thread1 t = new Thread1();
		t.start();
		int index = 0;
		while(true)
		{
			if(index++ == 500)
			{
				t.stopStop();
				//终端t的线程，此时就会抛出异常
				t.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName());
		}
		System.out.println("main exit");
	}
}
class Thread1 extends Thread
{
	private boolean isStop = false;
	public synchronized void run()
	{
		while(!isStop)
		{
			try {
				wait();
				//进入到对象的等待队列需要有notify方法去唤醒
			} catch (InterruptedException e) {
				e.printStackTrace();
				if(isStop)
					return;
			}
			System.out.println(Thread.currentThread().getName());
		}
	}
	public void stopStop()
	{
		isStop = true;
	}
}
