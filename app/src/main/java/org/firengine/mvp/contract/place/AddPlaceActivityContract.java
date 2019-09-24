package org.firengine.mvp.contract.place;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface AddPlaceActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String userId);
        void onAddButtonClicked(String placeName, String placePrice, String placeAvailable, String placeAddress, String placePhone, String placeFeatures);
    }

    interface View extends BaseView {
        void updateTextView(String landlordName);
        void finishActivity();
    }
}
