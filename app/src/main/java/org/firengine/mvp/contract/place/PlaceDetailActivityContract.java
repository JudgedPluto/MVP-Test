package org.firengine.mvp.contract.place;



import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;


public interface PlaceDetailActivityContract {
    interface Presenter extends BasePresenter{
        void onActivityCreated();
    }

    interface View extends BaseView{

        void updateTextViews(Object b_name, Object b_address, Object b_specialities, Object b_rules, Object b_roomTypes, Object b_map, Object b_roomPrice, Object b_roomAvail, Object b_photos, Object b_phoneNum);

    }
}
