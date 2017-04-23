package net.zdendukmonarezio.pathfinder.presentation;

import nucleus.presenter.RxPresenter;
import rx.Observable;

public class Presenter<E> extends RxPresenter<E> {
    /*
     *  fixes RxPresenter view is null bug
     *  > call viewIfExists() instead of view()
     */
    public Observable<E> viewIfExists() {
        return view().filter(view -> view != null).first();
    }
}