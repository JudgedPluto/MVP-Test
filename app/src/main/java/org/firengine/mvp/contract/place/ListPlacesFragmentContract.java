package org.firengine.mvp.contract.place;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.List;
import java.util.Map;

public interface ListPlacesFragmentContract {
    interface Presenter extends BasePresenter {
        void onFragmentCreated(String userId);
        void onListItemClicked(String id);
    }

    interface View extends BaseView {
        void setupAdapter();
        void updateAdapter(List<Map<String, Object>> data);
        void startPlaceInfoActivity(String id);
    }
}
