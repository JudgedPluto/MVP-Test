package org.firengine.mvp.contract.place;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;


public interface PlaceListFragmentContract {
    interface Presenter extends BasePresenter{

        void onActivityCreated(String filterColumn, String filterValue);
    }

    interface View extends BaseView{

    }
}
