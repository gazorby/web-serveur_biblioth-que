package library;

import exception.NotAvailableException;
import server.DELAYS;
import state.Available;
import state.State;

public class Book implements Document {

    private int num;
    private State state;
    private Subscriber sub;

    public Book(int num) {
        this.num = num;
        state = new Available();
    }

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public void reserv(Subscriber sub) throws NotAvailableException {
        if (this.state.getClass().equals(Available.class)) {
            this.state.startTimer();
            this.sub = sub;
            this.state.getTimer().schedule(new EndReservation(this, this.state.getTimer()), DELAYS.RES_DELAY.getValue());
        }
        state.reserv(this);
    }

    @Override
    public void borrow(Subscriber sub) throws NotAvailableException {
            state.borrow(this, sub);
    }

    @Override
    public void back(Subscriber sub) throws NotAvailableException {
        state.back(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public Subscriber getSub() {
        return sub;
    }

    public Subscriber getSubscriber() {
        return this.sub;
    }
}
