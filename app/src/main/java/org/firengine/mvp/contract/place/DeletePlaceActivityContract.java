package org.firengine.mvp.contract.place;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface DeletePlaceActivityContract {
    interface Presenter extends BasePresenter{
        void onActivityCreated(String id);
        void onDeleteButtonClicked();
    }

    interface View extends BaseView{
        void updateTextView(String placeName);
        void finishActivity();
//        void updateView(String success);
    }

}
