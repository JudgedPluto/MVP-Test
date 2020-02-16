package org.firengine.mvp.contract.review;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.List;
import java.util.Map;

public interface ReviewListFragmentContract {

    interface Presenter extends BasePresenter {
        void onActivityCreated();
    }

    interface  View extends BaseView {
        void updateAdapter(List<Map<String, Object>> data);
    }
}
