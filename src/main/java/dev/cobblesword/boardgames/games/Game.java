package dev.cobblesword.boardgames.games;

public abstract class Game
{
    private long startTimestamp;

    public void start()
    {
        this.startTimestamp = System.currentTimeMillis();

        onStart();
    }

    public void tick()
    {
        onTick();

        if(hasWinner())
        {
            this.onEnd("Winner");
        }

        long timepassed = System.currentTimeMillis() - startTimestamp;
        if(timepassed > 120_000L)
        {
            this.onEnd("Timeout");
        }
    }

    protected abstract void onStart();

    protected abstract void onTick();

    protected abstract boolean hasWinner();

    protected abstract void onEnd(String reason);
}
