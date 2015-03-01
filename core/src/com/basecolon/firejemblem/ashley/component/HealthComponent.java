package com.basecolon.firejemblem.ashley.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent extends Component {
    private int current;
    private int max;

    public HealthComponent(int current, int max) {
        this.current = current;
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = Math.max(0, Math.min(current, this.max));
    }

    public void decreaseBy(int amount) {
        setCurrent(current - amount);
    }

    public void increaseBy(int amount) {
        setCurrent(current + amount);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
