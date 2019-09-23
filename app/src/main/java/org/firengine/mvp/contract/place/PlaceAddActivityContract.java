package org.firengine.mvp.contract.place;


import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;



public interface PlaceAddActivityContract {
    interface Presenter extends BasePresenter {
        void onAddButtonClicked(String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6, String toString7, String toString8, String toString9);
    }

    interface View extends BaseView {
        void showMessage();
    }
}
