package com.zch.baselib.widget.wheel;

final class OnItemSelectedRunnable implements Runnable {
    final MyWheelView loopView;

    OnItemSelectedRunnable(MyWheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
