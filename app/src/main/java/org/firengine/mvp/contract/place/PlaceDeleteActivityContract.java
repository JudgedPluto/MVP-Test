package org.firengine.mvp.contract.place;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface PlaceDeleteActivityContract {
    interface Presenter extends BasePresenter{

    }

    interface View extends BaseView{

        void updateView(String success);
    }

}
