package view;

class CLI
{
    private final Runnable readerFileRunnable;

    public CLI(ReaderFileRunnable readerFileRunnable)
    {
        this.readerFileRunnable = readerFileRunnable;
    }

    public void start()
    {
        Thread thread = new Thread(readerFileRunnable);

        thread.start();
    }
}
